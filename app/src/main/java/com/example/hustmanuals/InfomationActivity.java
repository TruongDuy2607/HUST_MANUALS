package com.example.hustmanuals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class InfomationActivity extends AppCompatActivity {
    private ImageView infHdsv, infKpat, infKght;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation);

        initUi();
        initListener();
    }
    private void initUi(){
        infHdsv = findViewById(R.id.inf_hdsv);
        infKght = findViewById(R.id.inf_kght);
        infKpat = findViewById(R.id.inf_kpat);
    }
    private void initListener(){
        infHdsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfomationActivity.this, HdsvActivity.class);
                startActivity(intent);
            }
        });
        infKpat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfomationActivity.this, KpatActivity.class);
                startActivity(intent);
            }
        });
        infKght.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfomationActivity.this, KghtActivity.class);
                startActivity(intent);
            }
        });
    }
}