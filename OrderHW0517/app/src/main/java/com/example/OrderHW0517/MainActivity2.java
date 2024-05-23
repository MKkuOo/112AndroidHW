package com.example.OrderHW0517;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView orderSummary = findViewById(R.id.order_txv);

        // 接收來自 MainActivity 的訂單資訊
        String orderDetails = getIntent().getStringExtra("ORDER_DETAILS");
        orderSummary.setText(orderDetails);
    }
}
