package com.example.raddiobutton1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView textViewShow = findViewById(R.id.textViewshow);

        // 從 Intent 中取得傳遞過來的資訊
        Intent intent = getIntent();
        if (intent != null) {
            String gender = intent.getStringExtra("gender");
            String ticketType = intent.getStringExtra("ticketType");
            int ticketCount = intent.getIntExtra("ticketCount", 0);
            double ticketPrice = intent.getDoubleExtra("ticketPrice", 0.0);

            // 構建要顯示的文字
            String displayText = "訂單明細: " + "\n" + gender + "\n"
                    + ticketType + ticketCount + "張" + "\n"
                    + "金額 " + formatAmount(ticketPrice) + "元";

            // 在 textViewShow 中顯示文字
            textViewShow.setText(displayText);
        } else {
            textViewShow.setText("無法取得資訊");
        }

        Button btndelete = findViewById(R.id.btndelete);
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在這裡處理刪除操作，例如清空 textViewShow 中的內容
                textViewShow.setText("");
            }
        });

        // 設置返回按鈕的點擊事件
        Button btnreturn = findViewById(R.id.btnreturn);
        btnreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在這裡處理返回操作，關閉當前的 Activity
                finish(); // 關閉當前 Activity，返回上一個 Activity
            }
        });
    }

    private String formatAmount(double amount) {
        // 將金額轉換為字串
        String amountString = String.format("%.2f", amount);

        // 移除小數點後的零
        if (amountString.indexOf(".") > 0) {
            amountString = amountString.replaceAll("0*$", "").replaceAll("\\.$", "");
        }

        return amountString;
    }
}