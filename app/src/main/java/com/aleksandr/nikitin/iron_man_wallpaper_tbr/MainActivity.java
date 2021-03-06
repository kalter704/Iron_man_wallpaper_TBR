package com.aleksandr.nikitin.iron_man_wallpaper_tbr;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.io.IOException;

public class MainActivity extends FragmentActivity {

    int countOfSwipedPages;
    int numberOfSwipedPages;
    boolean isShowFullscreenAds;
    boolean isDoNewRequestForInterstitial;

    InterstitialAd mInterstitialAd;

    Button btnSetWallPaper;
    ImageButton btnExit;

    ViewPager pager;
    PagerAdapter pagerAdapter;

    LinearLayout linImg;
    ImageView img;
    Animation animRotate;
    Animation animAlphaVilible;
    Animation animAlphaInvilible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countOfSwipedPages = 0;
        numberOfSwipedPages = Wallpapers.images.length - 1;
        isShowFullscreenAds = false;
        isDoNewRequestForInterstitial = false;

        linImg = (LinearLayout) findViewById(R.id.linImg);
        img = (ImageView) findViewById(R.id.imageView);
        animRotate = AnimationUtils.loadAnimation(this, R.anim.rotation_proccess);
        animAlphaVilible = AnimationUtils.loadAnimation(this, R.anim.alpha_vilible);
        animAlphaInvilible = AnimationUtils.loadAnimation(this, R.anim.alpha_invilible);

        animAlphaVilible.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                img.startAnimation(animRotate);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                linImg.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animAlphaInvilible.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                linImg.setVisibility(View.INVISIBLE);
                img.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //img.startAnimation(animation);

        /*


        SurfaceView v = (SurfaceView) findViewById(R.id.surfaceView);
        GifRun w = new GifRun();
        w.LoadGiff(v, this, R.drawable.proc123);
*/

/*
        ((Button) findViewById(R.id.btnFix)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                int dispayWidth = metrics.widthPixels;
                int dispayHeight = metrics.heightPixels;


                Toast.makeText(getApplicationContext(), String.valueOf(dispayWidth), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), String.valueOf(dispayHeight), Toast.LENGTH_SHORT).show();

                Toast.makeText(getApplicationContext(), String.valueOf(wallpaperManager.getDesiredMinimumWidth()), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), String.valueOf(wallpaperManager.getDesiredMinimumHeight()), Toast.LENGTH_SHORT).show();
*/

                /*
                img.startAnimation(animRotate);
                linImg.setVisibility(View.VISIBLE);
                */
/*
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    requestNewInterstitial();
                }
*/
                /*
                wallpaperManager.suggestDesiredDimensions(720, 1280);

                Context context = getApplicationContext();
                int resID = R.drawable.iron_man_1;

                Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                        getResources().getResourcePackageName(resID) + '/' +
                        getResources().getResourceTypeName(resID) + '/' +
                        getResources().getResourceEntryName(resID) );

                Intent intent = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    intent = wallpaperManager.getCropAndSetWallpaperIntent(uri);
                }
                startActivity(intent);
                */
                /*
                wallpaperManager.setWallpaperOffsetSteps(0, (float) 0.33);
                wallpaperManager.setWallpaperOffsets(getWindow().getDecorView().getRootView().getWindowToken(), (float) 0.8, (float) 0.8);
                */
/*
            }
        });
        */


        /*
        ((Button) findViewById(R.id.btnFluid1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                additionalWidth = (float) 0.1;
                //img.clearAnimation();
                linImg.startAnimation(animAlphaInvilible);
            }
        });

        ((Button) findViewById(R.id.btnFluid2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                additionalWidth = (float) 0.4;
            }
        });
        */

        btnSetWallPaper = (Button) findViewById(R.id.btnSetWallpaper);
        btnSetWallPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (new setWallpaperAsyncTask()).execute();
            }
        });
/*
        btnExit = (ImageButton) findViewById(R.id.btnExitFromMyApp);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
*/
        pager = (ViewPager) findViewById(R.id.viewPager);
        pagerAdapter = new MyFragmentPageAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
            }

            @Override
            public void onPageSelected(int i) {
                if (isShowFullscreenAds) {
                    isShowFullscreenAds = false;
                    countOfSwipedPages = 0;
                    mInterstitialAd.show();
                }
                if (countOfSwipedPages < numberOfSwipedPages) {
                    countOfSwipedPages++;
                } else {
                    countOfSwipedPages = 0;
                    requestNewInterstitial();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }

        });


        MobileAds.initialize(getApplicationContext(), getResources().getString(R.string.id_app_in_admob));

        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = getRequestForAds();
        adView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                isShowFullscreenAds = true;
            }
        });

    }

    private void requestNewInterstitial() {
        mInterstitialAd.loadAd(getRequestForAds());
    }

    private AdRequest getRequestForAds() {
        return new AdRequest.Builder().build();
        // EMULATOR
        /*
        return new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("3E0DC5B8245C21520131AB58878FDCE7")
                .build();
        */
        // Highscreen ICE 2
/*
        return new AdRequest.Builder()
                .addTestDevice("3E0DC5B8245C21520131AB58878FDCE7")
                .build();
*/
        // HUAWEI
        /*
        return new AdRequest.Builder()
                .addTestDevice("5A43B1E3FEA266FCDB1E781CF0903804")
                .build();
                */

        // ASUS
        /*
        return new AdRequest.Builder()
                .addTestDevice("3D7BF0D7FAA1EEBFFA72EA203BF60414")
                .build();
                */
    }

    void setWallpaperToBackground() {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int dispayWidth = metrics.widthPixels;
        int dispayHeight = metrics.heightPixels;

        Log.d("QWERTY", String.valueOf(dispayWidth));
        Log.d("QWERTY", String.valueOf(dispayHeight));

        wallpaperManager.suggestDesiredDimensions(dispayWidth, wallpaperManager.getDesiredMinimumHeight());
        wallpaperManager.setWallpaperOffsetSteps(1, 1);

        if (DisplayInfo.isCorrespondsToTheDensityResolution(dispayWidth, dispayHeight)) {
            try {
                wallpaperManager.setResource(Wallpapers.images[pager.getCurrentItem()]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), Wallpapers.images[pager.getCurrentItem()]);
            bitmap = Bitmap.createScaledBitmap(bitmap, dispayWidth, wallpaperManager.getDesiredMinimumHeight(), true);

            try {
                wallpaperManager.setBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }


    private class MyFragmentPageAdapter extends FragmentPagerAdapter {

        private int[] images = Wallpapers.images;
        private int imagesCount = images.length;

        public MyFragmentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return PageFragment.newInstance(images[i]);
        }

        @Override
        public int getCount() {
            return imagesCount;
        }

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            if (hasFocus) {
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }
    }

    private class setWallpaperAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //buttonSetEnabled(btnExit, false);
            buttonSetEnabled(btnSetWallPaper, false);
            linImg.startAnimation(animAlphaVilible);
        }

        @Override
        protected void onPostExecute(Void param) {
            super.onPostExecute(param);

            //buttonSetEnabled(btnExit, true);
            buttonSetEnabled(btnSetWallPaper, true);
            linImg.startAnimation(animAlphaInvilible);

            Context context = getApplicationContext();
            CharSequence text = getResources().getString(R.string.successful_set_wallpaper);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

        @Override
        protected Void doInBackground(Void... param) {
            setWallpaperToBackground();
            return null;
        }
    }

    void buttonSetEnabled(View view, boolean enabled) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (enabled == true) {
                view.setAlpha((float) 1.0);
            } else {
                view.setAlpha((float) 0.5);
            }
        }
        view.setEnabled(enabled);
        /*
        if(enabled == true) {
            view.setTextColor(getResources().getColor(R.color.colorWhite));
        } else {
            view.setTextColor(getResources().getColor(R.color.colorGrey));
        }
        */
    }

}
