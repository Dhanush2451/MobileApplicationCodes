package com.example.temperatureconvert;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {
 EditText temp;
 TextView res;
 RadioButton cf,fc;
 Button con;
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
 temp = (EditText) findViewById(R.id.t);
 res = (TextView) findViewById(R.id.res);
 cf = (RadioButton) findViewById(R.id.cf);
 fc = (RadioButton) findViewById(R.id.fc);
 con = (Button) findViewById(R.id.con);
 con.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View v) {
 double r=0;
 int t = 
Integer.parseInt(temp.getText().toString());
 if(cf.isChecked())
 {
 r = (double) (t*9)/5 + 32;
 }
 if(fc.isChecked())
 {
 r = (double) (t-32)*5/9;
 }
 res.setText("Result is: "+String.valueOf(r));
 }
 });
 }
}
