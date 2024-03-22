package com.example.a0315hw_login;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void button_Click (View view) {
        EditText account = findViewById(R.id.account);
        EditText password = findViewById(R.id.password);
        String enteredAccount = account.getText().toString();
        String enteredPassword = password.getText().toString();

        if (enteredAccount.isEmpty()) {
            TextView output = (TextView) findViewById(R.id.textView);
            output.setText("請輸入帳號");
        } else if (enteredPassword.isEmpty()) {
            TextView output = (TextView) findViewById(R.id.textView);
            output.setText("請輸入密碼");
        } else if (!enteredAccount.equals("KLC")) {
            TextView output = (TextView) findViewById(R.id.textView);
            output.setText("帳號或密碼錯誤，登入失敗");
        } else if (!enteredPassword.equals("A111222009")) {
            TextView output = (TextView) findViewById(R.id.textView);
            output.setText("帳號或密碼錯誤，登入失敗");
        } else {
            TextView output = (TextView) findViewById(R.id.textView);
            output.setText("登錄成功!");
        }
    }
}