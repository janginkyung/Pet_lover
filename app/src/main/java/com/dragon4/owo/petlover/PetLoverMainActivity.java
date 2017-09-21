package com.dragon4.owo.petlover;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by InKyung on 2017. 6. 16..
 */

public class PetLoverMainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petlover_main);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.order_main_tabs);
        tabLayout.addTab(tabLayout.newTab().setText("사진"));
        tabLayout.addTab(tabLayout.newTab().setText("커뮤니티"));
        tabLayout.addTab(tabLayout.newTab().setText("봉사"));

        final SnsFragment snsFragment = new SnsFragment();
        final CommunityFragment communityFragment = new CommunityFragment();
        final VoluteerFragment voluteerFragment = new VoluteerFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, snsFragment).commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, snsFragment).commit();
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, communityFragment).commit();
                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, voluteerFragment).commit();
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




    }




}
