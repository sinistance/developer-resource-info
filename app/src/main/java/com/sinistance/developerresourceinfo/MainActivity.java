package com.sinistance.developerresourceinfo;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    ScrollView scrollView;
    TextView tvScreenSize, tvScreenSizeDp, tvScreenDensity, tvScreenDensityDp, tvScreenLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = (ScrollView) findViewById(R.id.MainLayout);
        tvScreenSize = (TextView) findViewById(R.id.screen_size);
        tvScreenSizeDp = (TextView) findViewById(R.id.screen_size_dp);
        tvScreenDensity = (TextView) findViewById(R.id.screen_density);
        tvScreenDensityDp = (TextView) findViewById(R.id.screen_density_dp);
        tvScreenLayout = (TextView) findViewById(R.id.layout_screen_size);

        Display display = getWindowManager().getDefaultDisplay();
        int width;
        int height;
        if (android.os.Build.VERSION.SDK_INT >= 13) {
            Point size = new Point();
            display.getSize(size);
            width = size.x;
            height = size.y;
        } else {
            width = display.getWidth();
            height = display.getHeight();
        }

        tvScreenSize.setText(String.valueOf(width) + " X " + String.valueOf(height));

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float xDpi = dm.xdpi;
        float yDpi = dm.ydpi;

        tvScreenSizeDp.setText(String.valueOf((int)xDpi) + " X " + String.valueOf((int)yDpi));

        String densityCategory = "";
        switch (dm.densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                densityCategory = "LDPI";
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                densityCategory = "MDPI";
                break;
            case DisplayMetrics.DENSITY_TV:
                densityCategory = "TVDPI";
                break;
            case DisplayMetrics.DENSITY_HIGH:
                densityCategory = "HDPI";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                densityCategory = "XHDPI";
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                densityCategory = "XXHDPI";
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                densityCategory = "XXXHDPI";
                break;
        }

        tvScreenDensity.setText(densityCategory);

        tvScreenDensityDp.setText(String.valueOf((int)(dm.density * 160f)));

        String screenLayout = "";
        if(scrollView.getTag() != null)
            screenLayout = (String) scrollView.getTag();

        if (screenLayout.equalsIgnoreCase("sw320dp"))
            tvScreenLayout.setText("SW320DP - HANDSET");
        else if (screenLayout.equalsIgnoreCase("sw600dp"))
            tvScreenLayout.setText("SW600DP - 7\" TABLET");
        else if (screenLayout.equalsIgnoreCase("sw720dp"))
            tvScreenLayout.setText("SW720DP - 10\" TABLET");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }
}
