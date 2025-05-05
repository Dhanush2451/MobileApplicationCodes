package com.example.loginvalidation;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {
 EditText un,pwd;
 Button login;
 String user, pass;
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
 un = (EditText) findViewById(R.id.un);
 pwd = (EditText) findViewById(R.id.pwd);
 login = (Button) findViewById(R.id.button);
 login.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View view) {
 user = un.getText().toString();
 pass = pwd.getText().toString();
 if (user.equals("MVSREC") && pass.equals("itd"))
 {
 Toast.makeText(MainActivity.this, "Login 
Successful", Toast.LENGTH_LONG).show();
 
 }
 else
 {
 Toast.makeText(MainActivity.this, "Login 
Failed", Toast.LENGTH_LONG).show();
 }
 }
 });
 }
}
