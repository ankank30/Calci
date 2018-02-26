package com.example.ankeshanku.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

        boolean operator = false;
        boolean dot2 = false;
        boolean dot1 = false;
        boolean equal = false;
        double num1 = 0, num2 = 0, ans;
        char oper;
        int i = 1, j = 1;
        int intAns;
        String input = " ";

    public void operand (View view) {
        if (!equal) {
            Button but = (Button) view;
            if (operator == false) {
                if (dot1 == false) {
                    num1 = (num1 * 10) + Double.parseDouble(but.getText().toString());
                    input = input + but.getText().toString();
                } else {
                    num1 = num1 + (Math.pow(0.1, i) * Double.parseDouble(but.getText().toString()));
                    i++;
                    input = input + but.getText().toString();
                }
                inputDisplay();
            } else {
                if (dot2 == false) {
                    num2 = (num2 * 10) + Double.parseDouble(but.getText().toString());
                    input = input + but.getText().toString();
                } else {
                    num2 = num2 + (Math.pow(0.1, j) * Double.parseDouble(but.getText().toString()));
                    input = input + but.getText().toString();
                    j++;
                }
                inputDisplay();
            }
        }
    }

    private void inputDisplay() {
        TextView inp = findViewById(R.id.input);
        inp.setText(input);
    }

    private void outDisplay() {
        TextView out = findViewById(R.id.output);
        if(ans % 1 != 0) {
            out.setText(Double.toString(ans));
        } else {
            intAns = (int) ans;
            out.setText(Integer.toString(intAns));
        }
    }

    public void dot (View view) {
        input = input + ".";
        if(operator == false) {
            dot1 = true;
        } else {
            dot2 = true;
        }
        inputDisplay();
    }

    public void operator (View view) {
        if(!equal) {
            Button but = (Button) view;
            if (!operator && !equal) {
                operator = true;
            } else if (operator && !equal) {
                //Calculating num1(operation)num2 and storing in num1
                operations();
                num1 = ans;
                num2 = 0;
                dot2 = false;
                j = 1;
            }
            oper = but.getText().toString().charAt(0);
            input = input + " " + but.getText().toString() + " ";
            inputDisplay();
        }
    }

    private void operations () {
        switch (oper) {
            case '+' : ans = num1 + num2;     break;
            case '-' : ans = num1 - num2;     break;
            case 'X' : ans = num1 * num2;     break;
            case '/' : ans = num1 / num2;     break;
        }
    }

    public void equal (View view) {
        operations();
        if(!operator) {
            ans = num1;
        }
        outDisplay();
        num1 = ans;
        equal = true;
    }

    public void clear (View view){
        operator = false;
        dot2 = false;
        dot1 = false;
        num1 = 0;
        num2 = 0;
        ans = 0;
        oper = ' ';
        i = 1;
        j = 1;
        input = "";
        inputDisplay();
        TextView out = findViewById(R.id.output);
        out.setText(" ");
        equal = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("num1", num1);
        outState.putDouble("num2", num2);
        outState.putBoolean("operator", operator);
        outState.putBoolean("dot1", dot1);
        outState.putBoolean("dot2", dot2);
        outState.putDouble("ans", ans);
        outState.putInt("intAns", intAns);
        outState.putInt("i", i);
        outState.putInt("j", j);
        outState.putString("input", input);
        outState.putBoolean("equal", equal);
        outState.putChar("oper", oper);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        num1 = savedInstanceState.getDouble("num1");
        num2 = savedInstanceState.getDouble("num2");
        operator = savedInstanceState.getBoolean("operator");
        dot1 = savedInstanceState.getBoolean("dot1");
        dot2 = savedInstanceState.getBoolean("dot2");
        ans = savedInstanceState.getDouble("ans");
        intAns = savedInstanceState.getInt("intAns");
        i = savedInstanceState.getInt("i");
        j = savedInstanceState.getInt("j");
        input = savedInstanceState.getString("input");
        equal = savedInstanceState.getBoolean("equal");
        oper = savedInstanceState.getChar("oper");
        TextView inp = findViewById(R.id.input);
        inp.setText(input);
        TextView out = findViewById(R.id.output);
        if(equal) {
            if(ans % 1 != 0) {
                out.setText(Double.toString(ans));
            } else {
                intAns = (int) ans;
                out.setText(Integer.toString(intAns));
            }
        }
    }
}