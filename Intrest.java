package com.example.interestcalculator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {
 EditText p, t, r;
 TextView t1,t2;
 Button s, c;
 int p1, r1, time;
 float s1, c1, tot;
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
 p = (EditText) findViewById(R.id.p);
 r = (EditText) findViewById(R.id.r);
 t = (EditText) findViewById(R.id.t);
 t1 = (TextView) findViewById(R.id.t1);
 t2 = (TextView) findViewById(R.id.t2);
 s = (Button) findViewById(R.id.si);
 c = (Button) findViewById(R.id.ci);
 s.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View view) {
 p1 =Integer.parseInt(p.getText().toString());
 r1 =Integer.parseInt(r.getText().toString());
 time =Integer.parseInt(t.getText().toString());
 s1 = (p1*r1*time)/100;
 tot = p1+s1;
 t1.setText("Interest is:"+String.valueOf(s1));
 t2.setText("Total Amount 
is"+String.valueOf(tot));
 }
 });
 c.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View view) {
 p1 =Integer.parseInt(p.getText().toString());
 r1 =Integer.parseInt(r.getText().toString());
 time =Integer.parseInt(t.getText().toString());
 double total = p1*Math.pow(1 + (double) r1 /100, time);
 double c2 = total - p1;
 t1.setText("Interest is:"+String.valueOf(c2));
 t2.setText("Total Amount is"+String.valueOf(total));
 }
 });
 }
}
