package org.techtown.whattoeat;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;



public class Tournament extends AppCompatActivity {
    String[] foodarr = {
            "고로케", "고인돌갈비", "곱창", "까르보나라", "닭꼬치", "닭칼국수", "돈까스", "라멘",
            "베트남쌀국수", "보쌈", "부대찌개", "살치살구이", "삼겹살구이", "새우튀김", "순대볶음", "양꼬치",
            "오므라이스", "육회", "족발", "짜장면", "초밥","초코케이크", "치즈케익", "햄버거",
            "육계장", "짬뽕", "찜닭","닭갈비", "탕수육","치킨", "콘치즈",  "피자"};
    ArrayList<String> foodname = new ArrayList<>(Arrays.asList(foodarr));

    String[] resNames = new String[32];
    int[] randIndx = new int[32];


    HashMap<String, Integer> mlist = new HashMap<>();

    int gang = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament);

        final LinearLayout result = findViewById(R.id.result);
        final LinearLayout mainlayout = findViewById(R.id.mainlayout);
        final ImageButton imageButton1 = findViewById(R.id.imageButton1);
        final ImageButton imageButton2  = findViewById(R.id.imageButton2);


        final TextView tournament = findViewById(R.id.tournament);
        final TextView textView1 = findViewById(R.id.textView1);
        final TextView textView2 = findViewById(R.id.textView2);


        for(int i = 0; i < foodname.size(); i++){
            int resID;
            String resName = "f" + (i + 1);
            resNames[i] = resName;
            resID = getResources().getIdentifier(resNames[i],"drawable",getPackageName());
            mlist.put(foodname.get(i), resID);
        }


        Collections.shuffle(foodname);

        imageButton1.setBackgroundResource(mlist.get(foodname.get(0)));
        imageButton2.setBackgroundResource(mlist.get(foodname.get(1)));
        textView1.setText(foodname.get(0));
        textView2.setText(foodname.get(1));

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mlist.size() != 1){
                    mlist.remove(foodname.get(1));
                    foodname.remove(1);

                    Collections.shuffle(foodname);

                    imageButton1.setBackgroundResource(mlist.get(foodname.get(0)));
                    textView1.setText(foodname.get(0));
                    if(foodname.size() == 1){
                        Toast.makeText(getApplicationContext(), "당신의 선택은: "+ foodname.get(0), Toast.LENGTH_LONG).show();
                        mainlayout.setVisibility(View.INVISIBLE);
                        result.setBackgroundResource(mlist.get(foodname.get(0)));

                        Intent intent1 = new Intent();
                        intent1.setAction(Intent.ACTION_WEB_SEARCH);
                        intent1.putExtra(SearchManager.QUERY, foodname.get(0) + " 맛집");
                        startActivity(intent1);
                    }else{
                        imageButton2.setBackgroundResource(mlist.get(foodname.get(1)));
                        textView2.setText(foodname.get(1));

                        tournament.setText("32/" + gang++);

                    }


                }else{
                    Toast.makeText(getApplicationContext(), "no more image", Toast.LENGTH_LONG).show();
                }
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mlist.size() != 1){

                    mlist.remove(foodname.get(0));
                    foodname.remove(0);

                    Collections.shuffle(foodname);

                    imageButton1.setBackgroundResource(mlist.get(foodname.get(0)));
                    textView1.setText(foodname.get(0));
                    if(foodname.size() == 1) {
                        mainlayout.setVisibility(View.INVISIBLE);
                        result.setBackgroundResource(mlist.get(foodname.get(0)));
                        Toast.makeText(getApplicationContext(),"당신의 선택은: "+ foodname.get(0), Toast.LENGTH_LONG).show();
                        Intent intent1 = new Intent();
                        intent1.setAction(Intent.ACTION_WEB_SEARCH);
                        intent1.putExtra(SearchManager.QUERY, foodname.get(0) + " 맛집");
                        startActivity(intent1);
                    }else {
                        imageButton2.setBackgroundResource(mlist.get(foodname.get(1)));
                        textView2.setText(foodname.get(1));

                        tournament.setText("32/" + gang++);
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "no more image", Toast.LENGTH_LONG).show();

                }
            }
        });


/*
        for(int i = 0; i < 16; i++){
            int resID;
            resID = getResources().getIdentifier(resNames[randIndx[i]], "drawable", getPackageName());
            list1.add(resID);
            idx1.add(foodname[randIndx[i]]);
        }

        for(int i = 16; i < resNames.length; i++){
            int resID;
            resID = getResources().getIdentifier(resNames[randIndx[i]], "drawable", getPackageName());
            list2.add(resID);
            idx2.add(foodname[randIndx[i]]);
        }


        imageButton1.setBackgroundResource(list1.get(0));
        imageButton2.setBackgroundResource(list2.get(0));
        textView1.setText(idx1.get(0));
        textView2.setText(idx2.get(0));


        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list1.size() == 1){
                    Toast.makeText(getApplicationContext(), "no more image", Toast.LENGTH_LONG).show();
                }else{
                    list1.remove(list1.get(0));
                    imageButton1.setBackgroundResource(list1.get(0));
                    idx1.remove(0);
                    textView1.setText(idx1.get(0));

                }

            }
        });

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list2.size() == 1){
                    Toast.makeText(getApplicationContext(), "no more image", Toast.LENGTH_LONG).show();
                }else{
                    list2.remove(list2.get(0));
                    imageButton2.setBackgroundResource(list2.get(0));
                    idx2.remove(0);
                    textView2.setText(idx2.get(0));

                }

            }
        });
        */
    }

}
