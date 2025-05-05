package com.example.basiccalculator123;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {
 EditText Num1,Num2;
 Button Add, Sub, Mul, Div;
 TextView Result;
 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 EdgeToEdge.enable(this);
 setContentView(R.layout.activity_main);
ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main
), (v, insets) -> {
 Insets systemBars = 
insets.getInsets(WindowInsetsCompat.Type.systemBars());
 v.setPadding(systemBars.left, systemBars.top, 
systemBars.right, systemBars.bottom);
 return insets;
 });
 Num1 = (EditText) findViewById(R.id.editText1);
 Num2 = (EditText) findViewById(R.id.editText2);
 Add = (Button) findViewById(R.id.Add);
 Sub = (Button) findViewById(R.id.Sub);
 Mul = (Button) findViewById(R.id.Mul);
 Div = (Button) findViewById(R.id.Div);
 Result = (TextView) findViewById(R.id.textView);
 Add.setOnClickListener(this::onClick);
 Sub.setOnClickListener(this::onClick);
 Mul.setOnClickListener(this::onClick);
 Div.setOnClickListener(this::onClick);
 }
 public void onClick(View v)
 {
 float num1 = 0;
 float num2 = 0;
 float result = 0;
 String oper = "";
 // check if the fields are empty
 if (TextUtils.isEmpty(Num1.getText().toString()) || 
TextUtils.isEmpty(Num2.getText().toString()))
 return;
 // read EditText and fill variables with numbers
 num1 = Float.parseFloat(Num1.getText().toString());
 num2 = Float.parseFloat(Num2.getText().toString());
 if (v == Add)
 {
 oper = "+";
 result = num1 + num2;
 }
 if (v == Sub)
 {
 oper = "-";
 result = num1 - num2;
 }
 if (v == Mul)
 {
 oper = "*";
 result = num1 * num2;
 }
 if (v == Div)
 {
 oper = "/";
 result = num1 / num2;
 }
 // form the output line
 Result.setText(num1 + " " + oper + " " + num2 + " = " + result);
 }
}
