package app.prasetya.com.booknow;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import app.prasetya.com.booknow.Adapter.ViewPagerAdapter;
import app.prasetya.com.booknow.Fragment.BookingFragment;
import app.prasetya.com.booknow.Fragment.InfoFragment;

public class DetailActivity extends AppCompatActivity {
    private android.support.v7.widget.Toolbar mToolbar;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private ViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initToolbar();



        setupTabLayout();




    }

    private void initToolbar() {
        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("Book Now");
    }

    private void setupViewPager(ViewPager viewPager){



        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(InfoFragment.getInstance(),"INFO");
        adapter.addFragment(BookingFragment.getInstance(),"Booking");


        viewPager.setAdapter(adapter);
    }

    private void setupTabLayout(){
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }
}
