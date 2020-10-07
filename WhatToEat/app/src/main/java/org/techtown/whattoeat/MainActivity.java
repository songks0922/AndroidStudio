package org.techtown.whattoeat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    public static final int SELECT_CODE_MENU = 101;
    public static final int TOURNAMENT_CODE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.imageView);



        Button select = findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectMenu.class);
                startActivityForResult(intent, SELECT_CODE_MENU);
            }
        });
        
        Button tournament = findViewById(R.id.tournament);
        tournament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Tournament.class);
                startActivityForResult(intent, TOURNAMENT_CODE);
            }
        });
    }
}
