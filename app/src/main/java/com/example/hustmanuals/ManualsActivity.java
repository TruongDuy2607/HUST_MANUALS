package com.example.hustmanuals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class ManualsActivity extends AppCompatActivity {
    private RelativeLayout manuIctsv, manuHsv, manuDtn, manuBktn, manuDrl, manuHtht;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manuals);
        initUrl();
        initListener();
    }

    public void initUrl() {
        manuIctsv =  findViewById(R.id.manu_ictsv);
        manuHsv = findViewById(R.id.manu_hsv);
        manuDtn = findViewById(R.id.manu_dtn);
        manuBktn = findViewById(R.id.manu_bktn);
        manuDrl = findViewById(R.id.manu_drl);
        manuHtht = findViewById(R.id.manu_htsv);
    }
    private void initListener() {
        String ictsv = "https://www.facebook.com/ictsv.hust";
        String hsv = "https://www.facebook.com/hoisinhvienbkhn";
        String dtn = "https://www.facebook.com/doanthanhnienbkhn";
        String dch = "https://www.facebook.com/bachkhoathanhniendongchihoi";
        String drl = "https://www.facebook.com/groups/DiemRenLuyenHUST";
        String htht = "https://www.facebook.com/groups/hotrosinhvienontapdaicuong/";
        manuIctsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ictsv));
                startActivity(intent);
            }
        });
        manuHsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(hsv));
                startActivity(intent);
            }
        });
        manuDtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(dtn));
                startActivity(intent);
            }
        });
        manuBktn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(dch));
                startActivity(intent);
            }
        });
        manuDrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(drl));
                startActivity(intent);
            }
        });
        manuHtht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(htht));
                startActivity(intent);
            }
        });
    }
}
