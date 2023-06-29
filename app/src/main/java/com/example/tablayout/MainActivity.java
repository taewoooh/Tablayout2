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

        String[] arr =  {"전체", "서울특별시", "부산광역시", "대구광역시",
                "인천광역시", "광주광역시", "대전광역시", "울산광역시", "세종특별자치시"
                , "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주특별자치도"};

        int arrLength = arr.length;

        tabLayout=findViewById(R.id.tabs);
        viewPager=findViewById(R.id.view_pager);
        adapter=new FragmentAdapter(getSupportFragmentManager(),1);

        //FragmentAdapter에 컬렉션 담기
//        adapter.addFragment(new Frag1());
//        adapter.addFragment(new Frag2());
//        adapter.addFragment(new Frag3());

        for (int i = 0; i < arrLength; i++) {
            adapter.addFragment(new Frag1());

            Log.e("한번확인하기 - > ",""+arr[i]);
           // tabLayout.getTabAt(i).setText(arr[i]);
        }



        //ViewPager Fragment 연결
        viewPager.setAdapter(adapter);

        //ViewPager과 TabLayout 연결
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("전국");
        tabLayout.getTabAt(1).setText("서울특별시");
        tabLayout.getTabAt(2).setText("부산광역시");
        tabLayout.getTabAt(3).setText("대구광역시");

        tabLayout.getTabAt(4).setText("인천광역시");
        tabLayout.getTabAt(5).setText("광주광역시");
        tabLayout.getTabAt(6).setText("대전광역시");
        tabLayout.getTabAt(7).setText("울산광역시");

        tabLayout.getTabAt(8).setText("세종특별자치시");
        tabLayout.getTabAt(9).setText("경기도");
        tabLayout.getTabAt(10).setText("강원도");
        tabLayout.getTabAt(11).setText("충청북도");

        tabLayout.getTabAt(12).setText("충청남도");
        tabLayout.getTabAt(13).setText("전라북도");
        tabLayout.getTabAt(14).setText("전라남도");
        tabLayout.getTabAt(15).setText("경상북도");

        tabLayout.getTabAt(16).setText("경상남도");
        tabLayout.getTabAt(17).setText("제주특별자치도");

    }
}