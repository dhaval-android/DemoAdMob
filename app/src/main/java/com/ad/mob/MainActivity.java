package com.ad.mob;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    InterstitialAd mInterstitialAd;
    TextView mLblBannerDemo;
    private TextView mLblIntertialAdd;
    String locationProvider = LocationManager.NETWORK_PROVIDER;
    private TextView mLblNativeAd;
    private final String ADD_UNIT_INT = "ca-app-pub-8836006779696429/2540755998";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLblBannerDemo = (TextView) findViewById(R.id.mLblBannerDemo);
        mLblIntertialAdd = (TextView) findViewById(R.id.mLblIntertialAdd);
        mLblNativeAd = (TextView) findViewById(R.id.mLblNativeAd);
        mLblBannerDemo.setOnClickListener(this);
        mLblIntertialAdd.setOnClickListener(this);
        mLblNativeAd.setOnClickListener(this);


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(ADD_UNIT_INT);

     /*
        lblClcik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestNewInterstitial();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }
        });*/
       /* mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                // beginPlayingGame();
            }

            ;
        });*/


    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("76A4DF790CE6BBDA0C3D9703117ACA64")
                .build();
        mInterstitialAd.setAdListener(new AdListener() {
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
                /*mInterstitialAd.show();*/
                Log.d(TAG, "Ad Loaded");
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }
        });
        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mLblBannerDemo:
                /*startActivity(new Intent(MainActivity.this, BannerAd.class));*/
                callForBannerAdd();
                break;
            case R.id.mLblIntertialAdd:
                /*startActivity(new Intent(MainActivity.this, ActivityInterstitialAd.class));*/
                requestNewInterstitial();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                break;
            case R.id.mLblNativeAd:
                startActivity(new Intent(MainActivity.this, ActivityNativeAdd.class));
                break;
        }
    }

    private void callForBannerAdd() {
        final AdView mAdView = (AdView) findViewById(R.id.adView);
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        AdRequest adRequest = new AdRequest.Builder()
                .setGender(AdRequest.GENDER_MALE)
                .setLocation(locationManager.getLastKnownLocation(locationProvider))
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
