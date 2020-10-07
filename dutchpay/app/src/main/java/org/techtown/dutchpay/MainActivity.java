package org.techtown.dutchpay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public static final int CHOICE_MENU = 101;

    int price, people, divprice1, divprice2;
    HashMap<Integer, Integer> dutch = new HashMap<>();
    int cnt = 0, cnt1 = 0;
    int count = 0, total = 0;
    int onetopay1, onetopay2;
    int remain1, remain2;

    EditText priceText, peopleText;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        priceText = findViewById(R.id.price);
        peopleText = findViewById(R.id.people);
        result = findViewById(R.id.result);

        Button choice = (Button)findViewById(R.id.choice);
        choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChoiceActivity.class);
                intent.putExtra("dutch", dutch);
                intent.putExtra("people", people);
                startActivityForResult(intent, CHOICE_MENU);
            }
        });

    }

    public void onClickButton(View v){

        price = Integer.parseInt(priceText.getText().toString());
        people = Integer.parseInt(peopleText.getText().toString());


        int totalprice = price;

        while(true){
            price -= 1000 * people;
            if(price < 0)
                break;
            cnt++;
        }

        divprice1 = cnt * (1000 * people);
        onetopay1 = divprice1 / people;
        remain1 = totalprice - divprice1;
        remain2 = remain1;

        for(int i = 1; i < people+1; i++){
            dutch.put(i, onetopay1);
        }

        if(remain1 > 0){
            for(int i = 0; i < people; i++){
                remain2 -= 1000;
                cnt1++;
                if(remain2 < 1000)
                    break;
            }

            if(cnt1 == 1)
                onetopay2 = 1000;
            else
                onetopay2 = (remain1 - remain2) / cnt1;

            for(int i = 1; i < cnt1+1; i++){
                dutch.put(i, dutch.get(i) + onetopay2);
            }
            dutch.put(cnt1+1, dutch.get(cnt1+1) + remain2);
        }

        int price1 = 0, price2 = 0;

        for(Integer p : dutch.keySet()){
            total += dutch.get(p);
            if(dutch.get(p).equals(dutch.get(1)))
                price1++;
            else
                price2++;
        }
        result.setText("금액확인: " + total + "\n" + dutch.get(1) + "원: " + price1 + "명\n");
        if(price2 > 0)
            result.append(dutch.get(cnt1 + 1) + "원: " + price2 + "명" );
    }
}
