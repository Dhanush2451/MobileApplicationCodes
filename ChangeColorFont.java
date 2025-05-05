package com.example.guifontcolor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {
 TextView t1;
 Button c,f;
 int i=1;
 float font = 30;
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
 t1 = (TextView) findViewById(R.id.textView);
 c = (Button) findViewById(R.id.col);
 f = (Button) findViewById(R.id.font);
 c.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View v) {
 switch (i)
 {
 case 1: t1.setTextColor(Color.parseColor("#0000FF"));
 i++; break;
 case 2: t1.setTextColor(Color.parseColor("#00FF00"));
 i++; break;
 case 3: t1.setTextColor(Color.parseColor("#FF0000")); 
 i++; break;
 case 4: t1.setTextColor(Color.parseColor("#00FFFF"));
 i++; break;
 case 5: t1.setTextColor(Color.parseColor("#0FF0FF")); 
 i++; break;
 }
 if(i==6) i=1;
 }
 });
 f.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View v) {
 t1.setTextSize(font);
 font = font+5;
 if(font==50) font=30;
 }
 });
 }
}
