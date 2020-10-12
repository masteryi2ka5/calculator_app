package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private enum states {
        NUM1,
        NUM2
    };
    private states state;
    private long num1, num2;
    private String operator;
    private TextView tvMath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMath = findViewById(R.id.tvMath);

        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);
        findViewById(R.id.btnAdd).setOnClickListener(this);
        findViewById(R.id.btnSub).setOnClickListener(this);
        findViewById(R.id.btnMul).setOnClickListener(this);
        findViewById(R.id.btnDiv).setOnClickListener(this);
        findViewById(R.id.btnEqual).setOnClickListener(this);
        findViewById(R.id.btnBS).setOnClickListener(this);
        findViewById(R.id.btnCE).setOnClickListener(this);
        findViewById(R.id.btnC).setOnClickListener(this);

        state = states.NUM1;
        num1 = 0;
        num2 = 0;
        operator = "";
        tvMath.setText("0");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch(id) {
            case R.id.btnAdd:
                operator = "Add";
                state = states.NUM2;
                break;
            case R.id.btnSub:
                operator = "Sub";
                state = states.NUM2;
                break;
            case R.id.btnMul:
                operator = "Mul";
                state = states.NUM2;
                break;
            case R.id.btnDiv:
                operator = "Div";
                state = states.NUM2;
                break;
            case R.id.btnEqual:
                viewResult();
                break;
            case R.id.btnBS:
                removeOneDigit();
                break;
            case R.id.btnCE:
                clearCurrentNum();
                break;
            case R.id.btnC:
                state = states.NUM1;
                num1 = 0;
                num2 = 0;
                operator = "";
                tvMath.setText("0");
                break;
            default:
                addDigit(Integer.parseInt(((Button)view).getText().toString()));
        }
    }

    private void viewResult() {
        double rs = 0;
        int error = 0;
        switch(operator) {
            case "Add":
                rs = num1 + num2;
                break;
            case "Sub":
                rs = num1 - num2;
                break;
            case "Mul":
                rs = num1 * num2;
                break;
            case "Div":
                if (operator == "Div" && num2 == 0) {
                    error = 1;
                }
                else {
                    rs = (double) num1 / num2;
                }
                break;
        }
        if (error == 1) {
            tvMath.setText("ERROR");
        } else {
            tvMath.setText(String.valueOf(rs));
        }
        state = states.NUM1;
        num1 = 0;
        num2 = 0;
        operator = "";
    }

    private void removeOneDigit() {
        if (state == states.NUM1) {
            num1 = num1 / 10;
            tvMath.setText(String.valueOf(num1));
        } else {
            num2 = num2 / 10;
            tvMath.setText(String.valueOf(num2));
        }
    }

    private void clearCurrentNum() {
        if (state == states.NUM1) {
            num1 = 0;
            tvMath.setText(String.valueOf(num1));
        } else {
            num2 = num2 / 10;
            tvMath.setText(String.valueOf(num2));
        }
    }

    private void addDigit(int digit) {
        if (state == states.NUM1) {
            num1 = num1 * 10 + digit;
            tvMath.setText(String.valueOf(num1));
        } else {
            num2 = num2 * 10 + digit;
            tvMath.setText(String.valueOf(num2));
        }
    }

}