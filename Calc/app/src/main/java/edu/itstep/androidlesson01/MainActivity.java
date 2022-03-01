package edu.itstep.androidlesson01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editText = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextResult);
        editText.setKeyListener(null);
    }

    @Override
    public void onClick(View view) {
        Button button = (Button)view;
        String text = button.getText().toString();

        String editStr = editText.getText().toString();

        switch (text) {
            case "%":
                break;
            case "delete":
                if(editStr.length() == 1) editText.setText("0");
                else if(editStr.length() > 0 && !editStr.equals("0"))
                    editText.setText(editStr.substring(0,
                            editStr.length() -1)); 
                break;
            case "C":
                editText.setText("0");
                break;
            case "=":
                String res = String.valueOf(
                        getResult(editText.getText().toString()
                        ));

                editText.setText(res);
                break;

            default:

                if (text.matches("\\d|[+|\\-|x|÷|.]")) {
                    if(editStr.equals("0")) {
                        editStr = text;
                    }
                    else {
                        if(text.equals("x")) editStr += "*";
                        else if(text.equals("÷")) editStr += "/";
                        else editStr += text;
                    }
                    editText.setText(editStr);
                }
                break;
        }
    }

    public static double getResult(String str) {
        char[] chars = str.toCharArray();
        double result = 0;
        String oper1 = null, oper2 = null;
        Character actionChar = null;

        for (int i = 0; i < chars.length; i++) {
            if(!Character.isDigit(chars[i]) && chars[i] != '.') {
                actionChar = chars[i];
            }
            else
            {
                if(actionChar == null) {
                    oper1 = oper1 == null ? Character.toString(chars[i]) : oper1 + chars[i];
                }
                else {
                    if(Character.isDigit(chars[i]) || chars[i] == '.') {
                        oper2 = oper2 == null ? Character.toString(chars[i]) : oper2 + chars[i];
                        if(i + 1 < chars.length) {
                            if ((!Character.isDigit(chars[i + 1]) && chars[i + 1] != '.')
                                    || i + 1 == chars.length) {
                                oper1 = Double.toString(calc(
                                        Double.parseDouble(oper1),
                                        Double.parseDouble(oper2),
                                        actionChar));
                                oper2 = null;
                            }
                        }
                        else {
                            result = calc(
                                    Double.parseDouble(oper1),
                                    Double.parseDouble(oper2),
                                    actionChar);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static double calc(double opr1, double opr2, Character action) {
        switch (action) {
            case '+':
                return opr1 + opr2;
            case '-':
                return opr1 - opr2;
            case '*':
                return opr1 * opr2;
            case '/':
                return opr1 / opr2;
            default:
                return 0;
        }
    }
}