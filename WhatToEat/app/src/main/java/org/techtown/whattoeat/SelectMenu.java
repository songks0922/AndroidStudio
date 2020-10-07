package org.techtown.whattoeat;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SelectMenu extends AppCompatActivity {

    Handler handler;

    Button button[] = new Button[9];
    Integer[] Rid_button = {
            R.id.b1, R.id.b2, R.id.b3, R.id.b4,
            R.id.b5, R.id.b6, R.id.b7, R.id.b8, R.id.b9
    };

    int cnt = 0;
    EditText editTextSearch;
    String[] korea = {
            "불고기", "오징어\n두루치기", "닭볶음", "쌈밥",
            "비빔밥","생선구이", "낙지볶음", "게장", "떡갈비"};
    String[] tang = {"김치찌개", "순두부\n찌개", "된장찌개", "부대찌개",
            "동태찌개", "청국장", "갈비탕", "추어탕", "삼계탕"};
    String[] china = {"짜장면", "짬뽕", "볶음밥", "탕수육", "마파두부",
            "양장피", "깐풍기", "유린기", "고추잡채"};
    String[] japan = {"초밥", "라멘", "낫또", "오니기리", "덮밥",
            "우동", "야키니쿠", "메밀소바", "돈카츠"};
    String[] western = {"토마토\n스파게티", "봉골레", "크림\n파스타", "피자",
            "함박\n스테이크", "리조또", "스테이크", "햄버거", "시저\n샐러드"};
    String[] haejang = {"북엇국", "콩나물\n국밥", "순대국", "뼈해장국",
            "우거지국", "선지\n해장국","올갱이국", "매운라면", "물냉면"};
    String[] convin = {"편의점\n도시락", "샌드위치", "토스트", "샐러드",
            "김밥", "떡볶이", "핫도그", "밥버거", "시리얼"};
    String[] etc = {"쌀국수", "팟타이", "카레", "찜닭",
            "수제비", "칼국수", "아구찜", "닭갈비", "월날쌈"};
    String[] defqult = {"한식", "탕/찌개", "중식", "일식", "돌아가기", "양식",
    "해장", "간편식", "기타"};
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_menu);

        editTextSearch = (EditText)findViewById(R.id.resion);


        for(int i = 0; i < button.length; i++){
            button[i] = (Button)findViewById(Rid_button[i]);
            final int INDEX;
            INDEX = i;
            button[INDEX].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cnt == 0){
                        switch (INDEX){
                            case 0:
                                setArrText(button, korea);
                                break;
                            case 1:
                                setArrText(button, tang);
                                break;
                            case 2:
                                setArrText(button, china);
                                break;
                            case 3:
                                setArrText(button, japan);
                                break;
                            case 4:
                                finish();
                                cnt = 0;
                                break;
                            case 5:
                                setArrText(button, western);
                                break;
                            case 6:
                                setArrText(button, haejang);
                                break;
                            case 7:
                                setArrText(button, convin);
                                break;
                            case 8:
                                setArrText(button, etc);
                                break;

                        }
                        cnt++;

                    }else{
                        dialog = new ProgressDialog(SelectMenu.this);
                        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        dialog.setMessage("검색화면으로 이동 중입니다.");
                        dialog.show();

                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_WEB_SEARCH);
                        intent.putExtra(SearchManager.QUERY, editTextSearch.getText().toString()
                                + " " + button[INDEX].getText().toString() + " 맛집");
                        startActivity(intent);
                        cnt = 0;
                        setArrText(button, defqult);

                    }


                }
            });
        }





    }

    public void setArrText(Button[] button, String[] arr){
        for(int i = 0; i < arr.length; i++){
            button[i].setText(arr[i]);
        }
    }
}
