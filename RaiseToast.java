package com.example.raisetoast;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {
 Button b ;
 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 EdgeToEdge.enable(this);
 setContentView(R.layout.activity_main);

ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
 Insets systemBars = 
insets.getInsets(WindowInsetsCompat.Type.systemBars());
 v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
 return insets;
 });
 b = (Button) findViewById(R.id.button);
 b.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View v) {
 Toast.makeText(getBaseContext(),"Hello All.. Welcome to MAD LAB", Toast.LENGTH_LONG).show();
 }
 });
 }
}
