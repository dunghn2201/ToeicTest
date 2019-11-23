package com.dunghn.toeictest;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dunghn.toeictest.fragment.BooksFragment;
import com.dunghn.toeictest.fragment.ChangePasswordFragment;
import com.dunghn.toeictest.fragment.ScoreGeneralFragment;
import com.dunghn.toeictest.fragment.ToeicTestQuizFragment;
import com.dunghn.toeictest.fragment.VocalGramConverFragment;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    //    AccessToken accessToken;
    private View navHeader;
    TextView mtvFullNameNavHead, mtvPhoneNavHead;
    String fullnamenavhead, phonenavhead;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    View headerV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);

//        headerV = nvDrawer.getHeaderView(0);

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        // Setup toggle to display hamburger icon with nice animation
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);
        // Setup drawer view
        Intent in2 = getIntent();
        Bundle b = in2.getExtras();
        fullnamenavhead = b.getString("FULLNAMENAVHEAD");
        phonenavhead = b.getString("EMAILNAVHEAD");
        NavigationView navigationView = (NavigationView) findViewById(R.id.nvView);
//        navigationView.setNavigationItemSelectedListener(this);
        navHeader = navigationView.getHeaderView(0);

        mtvFullNameNavHead = (TextView) navHeader.findViewById(R.id.tvFullNameNavHead);
        mtvPhoneNavHead = (TextView) navHeader.findViewById(R.id.tvEmailNavHead);

        mtvFullNameNavHead.setText(fullnamenavhead);
        mtvPhoneNavHead.setText(phonenavhead);

        setupDrawerContent(nvDrawer);

//        accessToken = AccessToken.getCurrentAccessToken();
//        if (accessToken == null) {
//        } else {
//            loadUserProfile(accessToken);
//        }

    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;
        // Create a new fragment and specify the fragment to show based on nav item clicked
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                fragmentClass = ToeicTestQuizFragment.class;
                break;
            case R.id.nav_scoregeneral:
                fragmentClass = ScoreGeneralFragment.class;
                break;
            case R.id.nav_Books:
                fragmentClass = BooksFragment.class;
                break;
            case R.id.nav_Vocabulary:
                fragmentClass = VocalGramConverFragment.class;
                break;
            case R.id.nav_ChangePassword:
                fragmentClass = ChangePasswordFragment.class;
                break;

            case R.id.nav_LogOut:
                fragmentClass = ToeicTestQuizFragment.class;
//                LoginManager.getInstance().logOut();
                SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                //xoa tinh trang luu truoc do
                edit.clear();
                edit.commit();
                finish();
                break;
            default:
                fragmentClass = ToeicTestQuizFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "-1", Toast.LENGTH_SHORT).show();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }
}
