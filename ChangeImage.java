package com.example.changeimage;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {
 Button b;
 ImageView iv;
 boolean flag = true;
 int img[] = { 
R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4};
 int i=0;
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
 b = (Button) findViewById(R.id.img);
 iv = (ImageView) findViewById(R.id.imageView);
 b.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View v) {
 iv.setImageResource(img[i]);
 i++;
 if(i==4) i=0;
 }
 });
 }
}
