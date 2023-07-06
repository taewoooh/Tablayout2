package com.example.tablayout;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//외부에서 new Frag1 호출 시
public class Frag1 extends Fragment implements View.OnClickListener {


    private final String BASE_URL = "https://taewoooh88.cafe24.com/";
    Retrofit retrofit;
    RecyclerView daydatarecyclerview;
    ArrayList<Daydatalistitem> listViewItems;
    LinearLayoutManager linearLayoutManager;
    Daydataadapter recyclerViewAdapter;
    CardView b1;
    private int i = 0;
    TextView hightrade;
    TextView hightradeyear;
    TextView rowtrade;
    TextView rowtradeyear;

    TextView nowtrade;
    TextView nowtrade1;
    TextView nowtradeyear;
    Progressdialog progressDialog;
    int t;
    int tx;
    int plus;
    TextView title;
    TextView updatetime;
    Thread myThread;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.frag1,container,false);

        findview(v);
        Log.e("체크해1",""+getTitle());

        Daydata(v);

        return v;
    }

    public static Frag1 newInstance(String title) {
        Frag1 fragment = new Frag1();

        Bundle args = new Bundle();
        args.putCharSequence("title", title);
        fragment.setArguments(args);

        return fragment;
    }
    public void Numcounthandler(TextView textView1, int textview1cnt, TextView textView2, int textview2cnt) { //숫자 동적카운트 (textview1뷰에 동적으로 카운터하여 노출시킬 숫자 textview1cnt


        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // t = listViewItems.get(0).getHightrade();

                tx = 555;
                plus = 1;

                i = i + plus;

                if (tx >= i) {
                    hightrade.setText(String.valueOf(i) + " 건");
                    rowtrade.setText(String.valueOf(i) + " 건");
                } else {
                    textView1.setText(String.valueOf(textview1cnt) + "건");
                    textView2.setText(String.valueOf(textview2cnt) + " 건");

                    myThread.interrupt();

                }

            }
        };

         myThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        handler.sendMessage(handler.obtainMessage());
                        Thread.sleep(1);
                    } catch (Throwable t) {
                    }
                }
            }
        });
        myThread.setDaemon(true);
        myThread.start();

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("체크해2","-->");
    }
    public String getTitle() {
        Bundle args = getArguments();
        return (String) args.getCharSequence("title", "NO TITLE FOUND");
    }
    public void findview(View v) {
        progressDialog = new Progressdialog(getActivity());
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setCancelable(false);

        listViewItems = new ArrayList<>();
        title = (TextView) v.findViewById(R.id.title);
        b1 = (CardView) v.findViewById(R.id.b1);
        hightrade = (TextView) v.findViewById(R.id.hightrade);
        hightradeyear = (TextView) v.findViewById(R.id.hightradeyear);
        updatetime = (TextView) v.findViewById(R.id.updatetime);
        rowtrade = (TextView) v.findViewById(R.id.rowtrade);
        rowtradeyear = (TextView) v.findViewById(R.id.rowtradeyear);

        nowtrade = (TextView) v.findViewById(R.id.nowtrade);

        nowtradeyear = (TextView) v.findViewById(R.id.nowtradeyear);

        b1.setOnClickListener(this::onClick);

    }
    public void DaydataTongsin(String today) { // 서버 데이터를 가지고 온다 파라미터는 불러올 테이블 이름

       // progressDialog.show();
        init();
        Daydatagithup gitHub = retrofit.create(Daydatagithup.class);
        Call<List<Daydatalistitem>> call = gitHub.contributors(today);
        call.enqueue(new Callback<List<Daydatalistitem>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            // 성공시
            public void onResponse(Call<List<Daydatalistitem>> call, Response<List<Daydatalistitem>> result) {
                List<Daydatalistitem> contributors = result.body();
                // 받아온 리스트를 순회하면서

                Log.e("Test888", result.body().toString());

                for (Daydatalistitem contributor : contributors) {


                    String si = contributor.si;
                    int year = contributor.year;
                    int month = contributor.month;
                    int yearmonth = contributor.yearmonth;
                    int trade = contributor.trade;
                    String per = contributor.per;

                    int hightrade = contributor.hightrade;
                    int highyear = contributor.highyear;
                    int highmonth = contributor.highmonth;

                    int rowtarde = contributor.rowtrade;
                    int rowyear = contributor.rowyear;
                    int rowmonth = contributor.rowmonth;
                    String updatetime = contributor.updatetime;



                    Log.e("dhxodn88", "" + si + " / " + year + " / " + month + " / " + yearmonth + " ---> "
                            + trade + "(" + per + "%)" + " /  (최고거래건수 : " + highyear + "년 " + highmonth + "월 / " + hightrade + ")"
                            + " (최저거래건수 : " + rowyear + "년 " + rowmonth + "월 / " + rowtarde + " / 업데이트 시간 : " + updatetime + ")");


                    listViewItems.add(new Daydatalistitem(si, year, month, yearmonth, trade, per, hightrade, highyear, highmonth, rowtarde, rowyear, rowmonth, updatetime));
                    // Collections.sort(listViewItems);
                    recyclerViewAdapter.notifyDataSetChanged();

                }

                title.setText(listViewItems.get(0).si);

                hightrade.setText(String.valueOf(listViewItems.get(0).getHightrade()));
                rowtrade.setText(String.valueOf(listViewItems.get(0).getRowtrade()));
                nowtrade.setText(String.valueOf(listViewItems.get(0).getTrade()));


               // Numcounthandler(hightrade, listViewItems.get(0).getHightrade(), rowtrade, listViewItems.get(0).getRowtrade());

                updatetime.setText(listViewItems.get(0).getUpdatetime());
                // hightrade.setText(String.valueOf(listViewItems.get(0).getHightrade())+"건");
                hightradeyear.setText("(" + String.valueOf(listViewItems.get(0).getHighyear()) + "." + String.valueOf(listViewItems.get(0).getHighmonth()) + ")");

                //rowtrade.setText(String.valueOf(listViewItems.get(0).getRowtrade()) + " 건");
                rowtradeyear.setText("(" + String.valueOf(listViewItems.get(0).getRowyear()) + "." + String.valueOf(listViewItems.get(0).getRowmonth()) + ")");


                nowtradeyear.setText("(" + String.valueOf(listViewItems.get(0).getYear()) + "." + String.valueOf(listViewItems.get(0).getMonth()) + ")");
                //progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Daydatalistitem>> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "정보받아오기 실패 " + t, Toast.LENGTH_LONG)
//                        .show();

                Log.e("onFailure", "- > " + t);

            }
        });


    }



    public void Daydata(View v) { //
        //  View inflate1 = LayoutInflater.from(MainActivity.this).inflate(R.layout.daydatajungbo, null, false);

        // recyclerView = findViewById(R.id.recyclerView);
        daydatarecyclerview = v.findViewById(R.id.daydatarecyclerview);

        linearLayoutManager = new LinearLayoutManager(getContext());

        daydatarecyclerview.addItemDecoration(
                new DividerItemDecoration(getActivity(), linearLayoutManager.getOrientation()));
        daydatarecyclerview.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter = new Daydataadapter(getActivity(), listViewItems);
        daydatarecyclerview.setAdapter(recyclerViewAdapter);




        DaydataTongsin(getTitle());

    }
    public void init() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        // GSON 컨버터를 사용하는 REST 어댑터 생성
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b1:

                linearLayoutManager.scrollToPositionWithOffset(0, 0);
                break;
        }
    }
}