package com.example.platypus.volleypoc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.platypus.volleypoc.utils.InputStreamVolleyRequest;
import com.example.platypus.volleypoc.utils.RequestQueueManager;
import com.example.platypus.volleypoc.utils.ScreenTree;
import com.google.android.gms.security.ProviderInstaller;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private List<String> urls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            ProviderInstaller.installIfNeeded(getApplicationContext());
        } catch (Exception e)
        {
            Timber.e(e, "ProviderInstaller exception");
        }

        Timber.plant(new Timber.DebugTree());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LinearLayout layout = findViewById(R.id.logView);
        ScrollView scroll = findViewById(R.id.scrollView);
        Timber.plant(new ScreenTree(this, layout, scroll));

        // HITOMI
        for (int i=1; i<60; i++) urls.add("https://ba.hitomi.la/galleries/1287389/"+compensateStringLength(i,2)+"_1_"+i+".jpg");

        // E HENTAI
        /*
        obsolete URLs
        urls.add("http://37.187.117.152:33333/h/7e21f6eeaa345002fe67c75cbc08610a5db8ea65-161868-1280-960-jpg/keystamp=1537102800-44af1feaef;fileindex=63621673;xres=1280/67406097_p0.jpg");
        urls.add("http://37.187.119.115:44000/h/3e6a25c521011afbda34806f5cabdf4768b7105c-57379-706-1000-jpg/keystamp=1537102800-a049862e01;fileindex=63621662;xres=org/0437701985b0da0b95a68d8851de483d.jpg");
        urls.add("http://5.39.94.115:8210/h/e05327a08697578f6d01ae2040e5b7f760e899a9-200123-850-600-jpg/keystamp=1537102800-bd3906f22b;fileindex=63621663;xres=org/089e6782526279daa1a0917a7dbd3e7b.jpg");
        urls.add("http://62.75.163.41:1337/h/f327887468a62e8c4263922ecd3fbb688e25d892-120451-1280-720-jpg/keystamp=1537102800-e0ce0a1b66;fileindex=61174263;xres=1280/09ff01291f689dc3554c901db62a24e2.jpg");
        urls.add("http://90.89.151.239:28340/h/80671af600453da8a7244288ccb3bfe82a490dd3-245504-1063-1500-jpg/keystamp=1537102800-7a49c5f7f5;fileindex=63621664;xres=2400/0f32ed79f4d1f1ae18bfb32a37e9ea39.jpg");
        urls.add("http://109.236.83.120/lid/62895363/510b98569d500c98c50d395552fe9e2e5705eb58-499564-2150-1593-jpg/0f206345234af6bb300728890ab67ecc1bcdcfe4-140068-1280-948-jpg/1280/gdj8f4y95gc/11863006b4ed149828649377cb1e54e1.jpg");
        urls.add("http://178.32.220.71:1080/h/caca59d4348c2dca31dc9083cfed401b14a51e66-181005-777-1087-jpg/keystamp=1537103100-e63f663d6f;fileindex=63621665;xres=2400/126103d4679952d16e2add6555cb41d9.jpg");
        urls.add("http://95.211.214.34/lid/63321261/ac3af0eb89ae5b44ae09818370a559fdc37d3478-2185270-1868-2047-jpg/5807c7a06033b36da82952b840bca24042a79d46-195303-1280-1403-jpg/1280/spuzeso95gc/162f1848194ed588dcac087f415bde73.jpg");
        urls.add("http://54.37.26.91:33040/h/c7190b397004a63b94a8d19357b09a09566c628e-259161-1280-1808-jpg/keystamp=1537103100-e30b8bfea1;fileindex=59269196;xres=1280/1b206c4f1acc87c1d33d04db8716e0c5.jpg");
        urls.add("http://94.100.22.212/lid/53821527/172f61c9c38e7fa775fba5004a4a2507e357f966-903868-800-1053-png/31e3576b8f15e52a0ff20dbb167356b6eb1acd58-88182-800-1053-jpg/2400/rsa4ybz95gc/1b59ad6c5049c09f0e81db9883801ee3.jpg");
        urls.add("http://46.105.126.228:55001/h/4c6f5e19c0fc89a3a54c15d9409453c9d8a01969-422509-1280-1807-jpg/keystamp=1537103100-cb079fcbe1;fileindex=63621666;xres=1280/1bf9b3beefc0809655e3ce193f018b47.jpg");
        urls.add("http://195.154.250.101:33333/h/f1926da84214b3b83b8f75d42df083fb201f9850-521067-850-1500-png/keystamp=1537103100-851629ad83;fileindex=63143615;xres=org/209772948dc673d7a055005840f6711d.png");
        urls.add("http://5.135.190.122:59929/h/103d9029fb26c9376da45a053097b32997ee6edb-176006-1280-1811-jpg/keystamp=1537103100-45ad079030;fileindex=63320095;xres=1280/21ea626219de1512d0a0aeb8985f32e1.jpg");
        urls.add("http://151.80.38.162:8210/h/657ad2a856320d41e1ed8ff3556705ab523c2943-104803-750-1125-jpg/keystamp=1537103100-cec96296d4;fileindex=51246636;xres=2400/26ccf33a25c3700b649801d51a5e0077.jpg");
        urls.add("http://195.154.250.101:33333/h/765da99286e1d43935740e0f4656fcfa1a69fbd3-78126-744-767-jpg/keystamp=1537103100-0c6a30e26a;fileindex=60708880;xres=2400/2992401674d34e2dbfe6907478dd665e.jpg");
        urls.add("http://54.37.26.91:33060/h/ffe622902aa7d01beecbe983493fdd111d73253d-147641-1280-863-jpg/keystamp=1537103100-5f9d461e35;fileindex=62923277;xres=1280/374660d8e1f7cc17990ec4e9ac7e8da7.jpg");
        urls.add("http://5.39.94.115:8210/h/d4df6c849c624f1eeb5439eabd9e21fc8bf6c83d-365713-1140-1397-jpg/keystamp=1537103100-54e01b142d;fileindex=63621667;xres=2400/3b895e99c6d30d021ec518800bb413f5.jpg");
        urls.add("http://54.37.26.91:17101/h/e7b0fed525be75870cd713bb6a5b5ba32b12c011-117863-742-929-jpg/keystamp=1537103100-4a614cb217;fileindex=47363507;xres=org/4502855999b0407847a031bdbaf7e3d4.jpg");
        urls.add("http://62.210.81.128:6969/h/9fbfece69059fa163ff615fd77fd6de1405382d6-123589-583-1003-jpg/keystamp=1537103100-67eb10332e;fileindex=63321282;xres=org/49a7e1c63c483da71dd7baa40c365dfa.jpg");
        urls.add("http://5.135.190.129:59929/h/16cf4f651630a8d80bae228944893139ab2c7d02-72494-905-1000-jpg/keystamp=1537103100-1d08c60c57;fileindex=55691422;xres=2400/4cc8ea75fc8cace03893313e34cf06d4.jpg");
        urls.add("http://5.135.190.129:59929/h/c80da45dda7b338f9360b9ccab9e0d11daf60d27-253254-1254-1771-jpg/keystamp=1537103400-8e5d6f0234;fileindex=63409660;xres=2400/518f5ed179385d5d2b9c205e0cbcc084.jpg");
*/

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int threads = Integer.parseInt(((EditText)findViewById(R.id.editTextThreads)).getText().toString());
                boolean useHentoidUserAgent = ((CheckBox)findViewById(R.id.checkBoxUserAgent)).isChecked();
                boolean useOkHttp = ((CheckBox)findViewById(R.id.checkBoxOkHttp)).isChecked();
                runVolley(threads, useHentoidUserAgent, useOkHttp);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void runVolley(int threads, boolean useHentoidUserAgent, boolean useOkHttp)
    {
        if (useHentoidUserAgent) InputStreamVolleyRequest.USER_AGENT = "Mozilla/5.0 (Linux; Android 4.0.4; Galaxy Nexus Build/IMM76K)"
                + " AppleWebKit/535.19 (KHTML, like Gecko)"
                + " Chrome/18.0.1025.166 Mobile Safari/535.19"
                + " Hentoid/v" + BuildConfig.VERSION_NAME;

        RequestQueueManager manager = RequestQueueManager.getInstance(this.getApplicationContext(), threads, useOkHttp);

        RequestQueueManager.totalPics = urls.size();

        for (String s : urls) manager.addToQueue(s);
    }

    public static String compensateStringLength(int value, int length) {
        String result = String.valueOf(value);

        if (result.length() > length) {
            result = result.substring(0, length);
        } else if (result.length() < length) {
            result = String.format("%1$" + length + "s", result).replace(' ', '0');
        }

        return result;
    }

}
