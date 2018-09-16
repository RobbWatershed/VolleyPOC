package com.example.platypus.volleypoc.utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

import java.io.File;

import timber.log.Timber;

/**
 * Created by Robb_w on 2018/04
 * Manager class for image download queue (Volley)
 */
public class RequestQueueManager implements RequestQueue.RequestFinishedListener<Object> {
    private static RequestQueueManager mInstance;   // Instance of the singleton
    private static final int TIMEOUT_MS = 15000;

    private RequestQueue mRequestQueue;             // Volley download request queue
    private int nbRequests = 0;                     // Number of requests currently in the queue (for debug display)


    private RequestQueueManager(Context context, int nbThreads, boolean useOkHttp) {
        mRequestQueue = getRequestQueue(context, nbThreads, useOkHttp);
    }

    public static synchronized RequestQueueManager getInstance(Context context, int nbThreads, boolean useOkHttp) {
        if (mInstance == null) {
            mInstance = new RequestQueueManager(context, nbThreads, useOkHttp);
        }
        return mInstance;
    }

    /**
     * Returns the app's Volley request queue
     *
     * @param ctx App context
     * @return Hentoid Volley request queue
     */
    private RequestQueue getRequestQueue(Context ctx) { // This is the safest code, as it relies on standard Volley interface
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(ctx.getApplicationContext(), new VolleyOkHttp3Stack(TIMEOUT_MS));
            mRequestQueue.addRequestFinishedListener(this);
        }
        return mRequestQueue;
    }

    private RequestQueue getRequestQueue(Context context, int nbDlThreads, boolean useOkHttp) { // Freely inspired by inner workings of Volley.java and RequestQueue.java; to be watched closely as Volley evolves
        if (mRequestQueue == null) {
            BasicNetwork network;
            if (useOkHttp) {
                network = new BasicNetwork(new VolleyOkHttp3Stack(TIMEOUT_MS));
            } else {
                network = new BasicNetwork(new HurlStack());
            }

            File cacheDir = new File(context.getCacheDir(), "volley"); // NB : this is dirty, as this value is supposed to be private in Volley.java
            mRequestQueue = new RequestQueue(new DiskBasedCache(cacheDir), network, nbDlThreads);
            mRequestQueue.addRequestFinishedListener(this);
            mRequestQueue.start();
        }
        return mRequestQueue;
    }

    public <T> void addToQueue(String url) {
                    addToRequestQueue(new InputStreamVolleyRequest(Request.Method.GET, url,
                        parse -> {
                            if (parse != null) Timber.i("Request success : %s", url);
                        },
                        error -> {
                            String statusCode = (error.networkResponse != null) ? error.networkResponse.statusCode + "" : "N/A";
                            Timber.w("Download error - Image %s not retrieved (HTTP status code %s)", url, statusCode);
                            error.printStackTrace();
                        }));
    }

    /**
     * Add a request to the app's queue
     *
     * @param req Request to add to the queue
     * @param <T> Request content
     */
    private <T> void addToRequestQueue(Request<T> req) {
        mRequestQueue.add(req);
        nbRequests++;
        Timber.d("RequestQueue ::: request added - current total %s", nbRequests);
    }

    /**
     * Generic handler called when a request is completed
     *
     * @param request Completed request
     */
    public void onRequestFinished(Request request) {
        nbRequests--;
        Timber.d("RequestQueue ::: request removed - current total %s", nbRequests);
    }
}
