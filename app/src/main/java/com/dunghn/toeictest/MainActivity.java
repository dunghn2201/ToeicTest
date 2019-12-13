package com.dunghn.toeictest;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dunghn.toeictest.fragment.ChangePasswordFragment;
import com.dunghn.toeictest.fragment.IntroduceFragment;
import com.dunghn.toeictest.fragment.ScoreGeneralFragment;
import com.dunghn.toeictest.fragment.ToeicTestQuizFragment;
import com.dunghn.toeictest.fragment.VocalGramConverFragment;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private View navHeader;
    TextView mtvFullNameNavHead, mtvPhoneNavHead;
    String fullnamenavhead, phonenavhead;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("TOEIC TEST");
        // Find our drawer view
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        Intent in2 = getIntent();
        Bundle b = in2.getExtras();
        fullnamenavhead = b.getString("FULLNAMENAVHEAD");
        phonenavhead = b.getString("EMAILNAVHEAD");
        navigationView = (NavigationView) findViewById(R.id.nvView);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        navHeader = navigationView.getHeaderView(0);

        mtvFullNameNavHead = (TextView) navHeader.findViewById(R.id.tvFullNameNavHead);
        mtvPhoneNavHead = (TextView) navHeader.findViewById(R.id.tvEmailNavHead);

        mtvFullNameNavHead.setText(fullnamenavhead);
        mtvPhoneNavHead.setText(phonenavhead);

        Fragment fragment = new ToeicTestQuizFragment();
        toolbar.setTitle(R.string.lblToeicTest_fragment);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.flContent, fragment);
        ft.commit();

    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;

        switch (id) {
            case R.id.nav_home:
                fragment = new ToeicTestQuizFragment();
                break;
            case R.id.nav_scoregeneral:
                fragment = new ScoreGeneralFragment();
                break;
            case R.id.nav_Vocabulary:
                fragment = new VocalGramConverFragment();
                break;
            case R.id.nav_Introduction:
                fragment = new IntroduceFragment();
                break;
            case R.id.nav_FeedBack:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                String[] recipents = {"dunghnpd02792@fpt.edu.vn"};
                intent.setType("message/meokonz");
                intent.putExtra(Intent.EXTRA_EMAIL, recipents);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Toeictest Reviews");
                Intent chooser = Intent.createChooser(intent, "Send Feedback Via");
                startActivity(chooser);
                break;
            case R.id.nav_ChangePassword:
                fragment = new ChangePasswordFragment();
                break;
            case R.id.nav_LogOut:
                SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                //xoa tinh trang luu truoc do
                edit.clear();
                edit.commit();
                finish();
                break;
            default:
                fragment = new ToeicTestQuizFragment();
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flContent, fragment);
            ft.commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
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
}
