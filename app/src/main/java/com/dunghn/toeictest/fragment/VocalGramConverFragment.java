package com.dunghn.toeictest.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dunghn.toeictest.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class VocalGramConverFragment extends Fragment {
    ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter;
    public VocalGramConverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_vocabulary_grammar_conversation, container, false);

                mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager = (ViewPager) rootView.findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabs = (TabLayout) rootView.findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Tab 1"));
        tabs.addTab(tabs.newTab().setText("Tab 2"));
        tabs.addTab(tabs.newTab().setText("Tab 3"));

        //Link tabs with view pager
        tabs.setupWithViewPager(mViewPager);
        //getSupportActionBar().setTitle("Hello World");
        //toolbar.setTitle("Hello World");

//        //Adding menu icon to Toolbar
//        ActionBar supportActionBar = getSupportActionBar();
//        if (supportActionBar != null) {
//            supportActionBar.setTitle("");
//            //supportActionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
//            supportActionBar.setDisplayHomeAsUpEnabled(true);
//        }


        // Inflate the layout for this fragment
        return rootView;
    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if (position == 0)
                return (new VocalFragmentTab());
            else if (position == 1)
                return (new GramFragmentTab());
            else if (position == 2)
                return (new ConverFragmentTab());
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Vocabulary";
                case 1:
                    return "Grammer";
                case 2:
                    return "Conversation";
            }
            return null;
        }

    }

}
