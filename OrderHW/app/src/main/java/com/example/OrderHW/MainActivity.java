package com.example.OrderHW;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CheckBox chk1, chk2, chk3, chk4, chk5;
    private ImageView output1, output2, output3, output4, output5;
    private TextView showOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化所有的 CheckBox、ImageView 和 TextView
        chk1 = findViewById(R.id.chk1);
        chk2 = findViewById(R.id.chk2);
        chk3 = findViewById(R.id.chk3);
        chk4 = findViewById(R.id.chk4);
        chk5 = findViewById(R.id.chk5);

        output1 = findViewById(R.id.output1);
        output2 = findViewById(R.id.output2);
        output3 = findViewById(R.id.output3);
        output4 = findViewById(R.id.output4);
        output5 = findViewById(R.id.output5);

        showOrder = findViewById(R.id.showOrder);

        // 設置 CheckBox 的監聽器
        chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateOrderText();
                updateOutputVisibility(isChecked, output1);
            }
        });

        chk2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateOrderText();
                updateOutputVisibility(isChecked, output2);
            }
        });

        chk3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateOrderText();
                updateOutputVisibility(isChecked, output3);
            }
        });

        chk4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateOrderText();
                updateOutputVisibility(isChecked, output4);
            }
        });

        chk5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateOrderText();
                updateOutputVisibility(isChecked, output5);
            }
        });
    }

    // 更新顯示訂單的 TextView
    private void updateOrderText() {
        StringBuilder orderText = new StringBuilder("餐點如下: ");
        if (chk1.isChecked()) {
            orderText.append("漢堡、");
        }
        if (chk2.isChecked()) {
            orderText.append("薯條、");
        }
        if (chk3.isChecked()) {
            orderText.append("可樂、");
        }
        if (chk4.isChecked()) {
            orderText.append("玉米濃湯、");
        }
        if (chk5.isChecked()) {
            orderText.append("咖啡、");
        }

        // 移除最後的逗號
        if (orderText.length() > 0) {
            orderText.deleteCharAt(orderText.length() - 1);
        }

        // 更新 TextView
        showOrder.setText(orderText.toString());
    }

    // 更新 ImageView 的可見性
    private void updateOutputVisibility(boolean isChecked, ImageView imageView) {
        if (isChecked) {
            imageView.setVisibility(View.VISIBLE); // 顯示圖片
        } else {
            imageView.setVisibility(View.GONE); // 隱藏圖片
        }
    }
}