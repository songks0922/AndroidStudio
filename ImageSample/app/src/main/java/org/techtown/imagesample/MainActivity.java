package org.techtown.imagesample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final int DYNAMIC_VIEW_ID  = 0x8000;
    int num = 1;
    Random rd = new Random();

    int[][] arr = new int[5][4];
    int[] temp = new int[10];
    int resIdx = 0;
    int chance = 15;
    int [] resIdArr = new int [20];
    int tempValue = 0, tempPosition;
    int position;

    Handler handler = new Handler();
    int idx = 0, Ibindex = 0,DynamicId;
    ImageButton tempImage, currButton;

    ImageButton[] Ibarr = new ImageButton[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String[] icons = {"t1", "t2","t3", "t4", "t5","t6","t7", "t8", "t9", "t10"};

        LinearLayout layout1 = (LinearLayout) findViewById(R.id.layout1);
        LinearLayout layout2 = (LinearLayout) findViewById(R.id.layout2);
        LinearLayout layout3 = (LinearLayout) findViewById(R.id.layout3);
        LinearLayout layout4 = (LinearLayout) findViewById(R.id.layout4);
        LinearLayout layout5 = (LinearLayout) findViewById(R.id.layout5);


        for (int i = 0; i < temp.length; i++) {
            temp[i] = rd.nextInt(10);
            for (int j = 0; j < i; j++) {
                if (temp[i] == temp[j]) {
                    i--;

                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = temp[idx++];
                if (idx >= 10) {
                    idx = 0;
                    temp = shuffle(temp);
                }
            }
        }

        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }

        addViewInLayout(layout1, icons, arr, 0);
        addViewInLayout(layout2, icons, arr, 1);
        addViewInLayout(layout3,icons, arr, 2);
        addViewInLayout(layout4,icons, arr, 3);
        addViewInLayout(layout5, icons, arr, 4);

        for(int i = 0; i < Ibarr.length; i ++){
            Ibarr[i].setBackgroundResource(getResources().getIdentifier("card","drawable",getPackageName()));
            Ibarr[i].setTag(i);
            Ibarr[i].setOnClickListener(this);
        }


    }
    @Override
    public void onClick(View v){

        ImageButton newButton = (ImageButton) v;

        if(chance > 0){
            for(ImageButton tempButton : Ibarr){
                if(tempButton == newButton){
                    position = (Integer)v.getTag();
                    tempButton.setBackgroundResource(resIdArr[position]);
                }
            }
            if(tempValue == 0){
                tempImage = newButton;
                tempValue++;
                tempPosition = position;
            }else if(tempValue == 1){
                currButton = newButton;
                if(resIdArr[position] == resIdArr[tempPosition]){
                    tempValue = 0;
                    Toast.makeText(getApplicationContext(), "맞추셨습니다!!! 기회+1", Toast.LENGTH_LONG).show();
                    chance++;
                }else{
                    Toast.makeText(this,"틀렸습니다..." + "남은기회: " + --chance, Toast.LENGTH_LONG).show();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setCard(currButton);
                            setCard(tempImage);
                        }
                    },1000);
                    tempValue = 0;

                }
            }
        }else{
            Toast.makeText(getApplicationContext(), "모든기회를 잃으셨습니다...", Toast.LENGTH_LONG).show();
        }


    }

    public void setCard(ImageButton ib){
        ib.setBackgroundResource(getResources().getIdentifier("card","drawable",getPackageName()));
    }

    public  void addViewInLayout(LinearLayout v, String[] icons, int arr[][], int cnt){

        for (int i = 0; i < 4; i++) {

            int resId = getResources().getIdentifier(icons[arr[cnt][i]], "drawable", getPackageName());
            resIdArr[resIdx++] = resId;

            ImageButton image = new ImageButton(this);
            image.setBackgroundResource(resId);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(150, 400);
            p.leftMargin = 6;

            p.weight = 1;
            image.setLayoutParams(p);
            image.setId(DYNAMIC_VIEW_ID + num++);

            Ibarr[Ibindex++] = image;
            v.addView(image);
        }
    }


    public static int[] shuffle(int[] arr){
        for(int x=0;x<arr.length;x++){
            int i = (int)(Math.random()*arr.length);
            int j = (int)(Math.random()*arr.length);

            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

        return arr;
    }



}
