package com.example. backgroundchange;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {
Button red,green,img;
LinearLayout ll;
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
 red = (Button) findViewById(R.id.red);
 green = (Button) findViewById(R.id.green);
 img = (Button) findViewById(R.id.img);
 ll = (LinearLayout)findViewById(R.id.ll);
 red.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View view) {
 ll.setBackgroundResource(R.color.RED);
 }
 });
 green.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View view) {
 ll.setBackgroundResource(R.color.GREEN);
 }
 });
 img.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View view) {
 ll.setBackgroundResource(R.drawable.background);
 }
 });
 }
}
