package com.example.hustmanuals;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity {
    private Button buttonSignUp;
    private EditText edtEmail, edtPassword;
    private Button btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        initUi();
        initListener();
    }

    private void initUi(){
        buttonSignUp = findViewById(R.id.btn_singup);
        edtEmail = findViewById(R.id.edt_email_);
        edtPassword = findViewById(R.id.edt_password_);
        btnSignIn = findViewById(R.id.btn_sign_in);

    }
    private void initListener(){
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSignIn();
            }


        });


    }
    private void onClickSignIn() {
        String strEmail = edtEmail.getText().toString().trim();
        String strPassword = edtPassword.getText().toString().trim();

        if (strEmail.isEmpty() || strPassword.isEmpty()) {
            // Hiển thị thông báo yêu cầu nhập lại nếu trường email hoặc password trống
            Toast.makeText(SigninActivity.this, "Vui lòng nhập Email và mật khẩu!", Toast.LENGTH_SHORT).show();
            return;
        }
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(strEmail, strPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SigninActivity.this, "Sai thông tin đăng nhập!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}