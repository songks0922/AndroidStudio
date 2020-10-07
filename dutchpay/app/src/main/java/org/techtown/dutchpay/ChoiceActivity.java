package org.techtown.dutchpay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ChoiceActivity extends AppCompatActivity {

    int click = 0;
    int people;
    public static final int DYNAMIC_ID = 1000;
    LinearLayout linearLayout1;
    ArrayList<Integer> price = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        linearLayout1 = findViewById(R.id.layout1);

        Intent intent = getIntent();
        HashMap<Integer, Integer> dutch = (HashMap<Integer, Integer>)
                intent.getSerializableExtra("dutch");
        people = intent.getExtras().getInt("people");
        final Button[] arr = new Button[people];

        for(Integer p : dutch.keySet()){
            price.add(dutch.get(p));
        }

        for(int i = 0; i < people; i++){
            Button button = new Button(getApplicationContext());
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            p.gravity = Gravity.CENTER;
            button.setLayoutParams(p);
            button.setId(DYNAMIC_ID + i);
            button.setText("뽑기");
            arr[i] = button;
            arr[i].setTag(i);
            linearLayout1.addView(button);
            button.setVisibility(View.INVISIBLE);
        }

        Collections.shuffle(price);



        for(int i = 0; i < arr.length; i++){
            final int INDEX = i;
            arr[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    arr[INDEX].setText(price.get(INDEX).toString());
                }
            });

        }

        Button rabar = new Button(getApplicationContext());
        LinearLayout.LayoutParams rp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        rp.gravity = Gravity.CENTER;
        rp.topMargin = 300;
        rabar.setLayoutParams(rp);
        rabar.setBackgroundResource(R.drawable.button);
        linearLayout1.addView(rabar);


        rabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim =
                        AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                v.startAnimation(anim);
                for(int i = 0; i < arr.length; i++){
                    int INDEX = (Integer)arr[i].getTag();
                    if(click == INDEX){
                        arr[i].setVisibility(View.VISIBLE);
                    }
                }
                click++;

            }
        });


    }
}
