//A111222009
package com.example.raddiobutton1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.raddiobutton1.MainActivity2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btnchoose);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rdbBoy = findViewById(R.id.rdbBoy);
                RadioButton rdbGirl = findViewById(R.id.rdbGirl);
                RadioGroup rgType = findViewById(R.id.rgType);
                EditText edtNumber = findViewById(R.id.edtNumber);
                TextView lblOutput = findViewById(R.id.lblOutput);

                // 取得使用者選擇的性別
                String gender = "";
                if (rdbBoy.isChecked()) {
                    gender = getResources().getString(R.string.male);
                } else if (rdbGirl.isChecked()) {
                    gender = getResources().getString(R.string.female);
                }

                // 取得使用者選擇的票種
                String ticketType = "";
                if (rgType.getCheckedRadioButtonId() == R.id.rdbAdult) {
                    ticketType = getResources().getString(R.string.regularticket);
                } else if (rgType.getCheckedRadioButtonId() == R.id.rdbChild) {
                    ticketType = getResources().getString(R.string.childticket);
                } else {
                    ticketType = getResources().getString(R.string.studentticket);
                }

                // 取得購買張數
                String ticketCountStr = edtNumber.getText().toString();
                int ticketCount = 0;
                if (!ticketCountStr.isEmpty()) {
                    ticketCount = Integer.parseInt(ticketCountStr);
                }

                // 計算票價金額
                double ticketPrice = calculateTicketPrice(ticketType, ticketCount);

                // 構建輸出資訊字串
                String outputStr = "性別: " + gender + "\n"
                        + "票種: " + ticketType + "\n"
                        + "購買張數: " + ticketCount + "\n"
                        + "金額: $" + formatAmount(ticketPrice);

                // 顯示到lblOutput
                lblOutput.setText(outputStr);
            }
        });

        Button buttoncorret = findViewById(R.id.buttoncorret);
        buttoncorret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rdbBoy = findViewById(R.id.rdbBoy);
                RadioButton rdbGirl = findViewById(R.id.rdbGirl);
                RadioGroup rgType = findViewById(R.id.rgType);
                EditText edtNumber = findViewById(R.id.edtNumber);

                String gender = (rdbBoy.isChecked()) ? getResources().getString(R.string.male) : getResources().getString(R.string.female);
                String ticketType = "";
                if (rgType.getCheckedRadioButtonId() == R.id.rdbAdult) {
                    ticketType = getResources().getString(R.string.regularticket);
                } else if (rgType.getCheckedRadioButtonId() == R.id.rdbChild) {
                    ticketType = getResources().getString(R.string.childticket);
                } else if (rgType.getCheckedRadioButtonId() == R.id.rdbStudent) {
                    ticketType = getResources().getString(R.string.studentticket);
                }
                String ticketCountStr = edtNumber.getText().toString();
                int ticketCount = (ticketCountStr.isEmpty()) ? 0 : Integer.parseInt(ticketCountStr);

                double ticketPrice = calculateTicketPrice(ticketType, ticketCount);

                // 創建 Intent
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("gender", gender);
                intent.putExtra("ticketType", ticketType);
                intent.putExtra("ticketCount", ticketCount);
                intent.putExtra("ticketPrice", ticketPrice);
                startActivity(intent);
            }
        });
    }

    // 根據票種和購買張數計算票價金額
    private double calculateTicketPrice(String ticketType, int ticketCount) {
        double ticketPrice = 0.0 ;
        // 根據票種計算票價金額
        if (ticketType.equals(getResources().getString(R.string.regularticket))) {
            ticketPrice = 500.0 * ticketCount; // 全票價格為 $500
        } else if (ticketType.equals(getResources().getString(R.string.studentticket))) {
            ticketPrice = 400.0 * ticketCount; // 學生票價格為 $400
        } else if (ticketType.equals(getResources().getString(R.string.childticket))) {
            ticketPrice = 250.0 * ticketCount; // 兒童票價格為 $250
        }
        return ticketPrice;
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