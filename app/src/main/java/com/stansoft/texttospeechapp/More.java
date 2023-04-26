package com.stansoft.texttospeechapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class More extends AppCompatActivity {
    private AdView mAdView;


    ListView listView;

    String[] fruitNames = {"Best of Elon Musk Quotes","Best of Winston Churchill Quotes","Learn Django", "Best of Elon Musk Quotes","Best of Winston Churchill Quotes","Learn Django"};
    int[] fruitImages = {R.drawable.eln, R.drawable.churchill,R.drawable.dj,R.drawable.eln, R.drawable.churchill,R.drawable.dj};
    String[] url = {"https://play.google.com/store/apps/details?id=com.stansoft.bestofelonmuskquotes",
            "https://play.google.com/store/apps/details?id=com.stansoft.bestofwinstonchurchillquotes",
            "https://play.google.com/store/apps/details?id=com.stansoft.learndjango",
            "https://play.google.com/store/apps/details?id=com.stansoft.bestofelonmuskquotes",
            "https://play.google.com/store/apps/details?id=com.stansoft.bestofwinstonchurchillquotes",
            "https://play.google.com/store/apps/details?id=com.stansoft.learndjango",


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);




        listView = findViewById(R.id.listview);

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(),fruitNames[i],Toast.LENGTH_LONG).show();

                String ur = url[i];
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(ur));
                startActivity(intent);

            }
        });




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu5_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.about) {
            Intent intent = new Intent(More.this,Main2Activity.class);
            startActivity(intent);
        }

        if (id == R.id.action_share) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "https://play.google.com/store/apps/details?id=com.stansoft.texttospeechapp";
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share App Using"));
        }
        if (id == R.id.action_home) {
            Intent intent = new Intent(More.this, MainActivity.class);
            startActivity(intent);
        }





        return super.onOptionsItemSelected(item);
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {

            return fruitImages.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.row2_data,null);
            //getting view in row_data



            TextView name = view1.findViewById(R.id.fruits);

            ImageView imag = view1.findViewById(R.id.image);

            name.setText(fruitNames[i]);
            imag.setImageResource(fruitImages[i]);
            return view1;



        }
    }
}
