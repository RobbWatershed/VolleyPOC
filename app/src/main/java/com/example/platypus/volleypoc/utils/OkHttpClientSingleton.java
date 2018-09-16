package com.example.platypus.volleypoc.utils;

import android.util.SparseArray;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Manages a single instance of OkHttpClient per timeout delay
 */
public class OkHttpClientSingleton {

    private static volatile SparseArray<OkHttpClient> instance = new SparseArray<>();

    private OkHttpClientSingleton() {}

    public static OkHttpClient getInstance(int timeoutMs)
    {
        if (null == OkHttpClientSingleton.instance.get(timeoutMs)) {
            synchronized(OkHttpClientSingleton.class) {
                if (null == OkHttpClientSingleton.instance.get(timeoutMs)) {

                    OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                    .connectTimeout(timeoutMs, TimeUnit.MILLISECONDS)
                    .readTimeout(timeoutMs, TimeUnit.MILLISECONDS)
                    .writeTimeout(timeoutMs, TimeUnit.MILLISECONDS);


                    OkHttpClientSingleton.instance.put(timeoutMs,clientBuilder.build());
                }
            }
        }
        return OkHttpClientSingleton.instance.get(timeoutMs);
    }
}
