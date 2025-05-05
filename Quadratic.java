package com.example.quadraticroots;
import android.os.Bundle;
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
 EditText a,b,c;
 TextView r1,r2,type;
 Button button;
 int a1,b1,c1;
 double root1,root2;
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
 a = (EditText) findViewById(R.id.a);
 b = (EditText) findViewById(R.id.b);
 c = (EditText) findViewById(R.id.c);
 r1 = (TextView) findViewById(R.id.r1);
 r2 = (TextView) findViewById(R.id.r2);
 type = (TextView) findViewById(R.id.type);
 button = (Button) findViewById(R.id.button);
 button.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View view) {
 a1 = Integer.parseInt(a.getText().toString());
 b1 = Integer.parseInt(b.getText().toString());
 c1 = Integer.parseInt(c.getText().toString());
 double d = (b1*b1)-(4*a1*c1);
 if (d<0)
 {
 r1.setText("Root 1:Not determined");
 r2.setText("Root 2:Not determined ");
 type.setText("Root are imaginary ");
 }
 else {
 root1 = (-b1 + Math.sqrt(d)) / 2 * a1;
 root2 = (-b1 - Math.sqrt(d)) / 2 * a1;
 r1.setText("Root 1: " + String.valueOf(root1));
 r2.setText("Root 2:" + String.valueOf(root2));
 if (root1==root2)
 type.setText("Root are Equal");
 else
 type.setText("Root are Distinct");
 }
 }
 });
 }
}
