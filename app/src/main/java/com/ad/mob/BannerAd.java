package com.ad.mob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class BannerAd extends AppCompatActivity {

  private final String TAG = "BannerAd"; /*
    private final String ADD_UNIT_BANNER = "ca-app-pub-8836006779696429/5773423995";*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_ad);

        final AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setGender(AdRequest.GENDER_FEMALE)
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {

                super.onAdClosed();
                Log.d(TAG, "Ad Closed");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                Log.d(TAG, "Ad Load Failed");
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
                Log.d(TAG, "Ad Left Application");
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                Log.d(TAG, "Ad Opened");
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mAdView.setVisibility(View.VISIBLE);
                Log.d(TAG, "Ad Loaded");
            }
        });
        mAdView.loadAd(adRequest);


    }
}
