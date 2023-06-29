package com.example.tablayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] arr =  {"전국", "서울특별시", "부산광역시", "대구광역시",
                "인천광역시", "광주광역시", "대전광역시", "울산광역시", "세종특별자치시"
                , "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주특별자치도"};

        int arrLength = arr.length;

        tabLayout=findViewById(R.id.tabs);
        viewPager=findViewById(R.id.view_pager);
        adapter=new FragmentAdapter(getSupportFragmentManager(),1);

        for (int i = 0; i < arrLength; i++) {
            adapter.addFragment(new Frag1());

            Log.e("한번확인하기 - > ",""+arr[i]);

        }




        viewPager.setAdapter(adapter);


        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < arrLength; i++) {
            //adapter.addFragment(new Frag1());

            Log.e("한번확인하기 - > ",""+arr[i]);
             tabLayout.getTabAt(i).setText(arr[i]);
        }



    }
}