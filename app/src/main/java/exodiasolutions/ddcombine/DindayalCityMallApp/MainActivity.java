package exodiasolutions.ddcombine.DindayalCityMallApp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import exodiasolutions.ddcombine.DindayalCityMallApp.myapplication.Dining;
import exodiasolutions.ddcombine.DindayalCityMallApp.myapplication.cinema;
import exodiasolutions.ddcombine.DindayalCityMallApp.myapplication.shops;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private LinearLayout ll_cinema;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (Build.VERSION.SDK_INT < 16) { //ye olde method
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else { // Jellybean and up, new hotness
            View decorView = getWindow().getDecorView();
            // Hide the status bar.
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            // Remember that you should never show the action bar if the
            // status bar is hidden, so hide that too if necessary.

        }

        TextView tv = (TextView) findViewById(R.id.tv_stores);
        ll_cinema = (LinearLayout) findViewById(R.id.cinema);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_about) {
            Intent i  = new Intent(MainActivity.this,AboutUs.class);
            startActivity(i);

        } else if (id == R.id.nav_features) {

        } else if (id == R.id.nav_business) {

        } else if (id == R.id.nav_stores) {
            Intent i = new Intent(this,shops.class);
            startActivity(i);
        } else if (id == R.id.nav_contact_us) {
            Intent i = new Intent(this,ContactMapsActivity.class);
            startActivity(i);
        }else if (id == R.id.nav_login) {
            Intent i = new Intent(this,Login.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showCinema(View v){
        Intent i = new Intent(this,cinema.class);
        startActivity(i);
    }
    public void showShops(View v){
        Intent i = new Intent(this,shops.class);
        startActivity(i);
    }
    public void showDining(View v){
        Intent i = new Intent(this,Dining.class);
        startActivity(i);
    }
    public void showEntertain(View v){
        Intent i = new Intent(this,Lounge.class);
        startActivity(i);
    }


}
