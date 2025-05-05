package com.example.loginnavigation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {
 EditText un,pwd;
 Button login;
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
 String user = un.getText().toString();
 String pass = pwd.getText().toString();
 if(user.equals("MVSREC") && pass.equals("itd"))
 {
 Intent i = new Intent(getBaseContext(), Success.class);
 startActivity(i);
 }
 else {
 Intent i = new Intent(getBaseContext(), Failure.class);
 startActivity(i);
 }
 }
 });
 }
}

Success.java
package com.example.loginnavigation;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class Success extends AppCompatActivity {
 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 EdgeToEdge.enable(this);
 setContentView(R.layout.sucess);
 
ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main
), (v, insets) -> {
 Insets systemBars = 
insets.getInsets(WindowInsetsCompat.Type.systemBars());
 v.setPadding(systemBars.left, systemBars.top, 
systemBars.right, systemBars.bottom);
 return insets;
 });
 }
}
Failure.java
package com.example.loginnavigation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class Failure extends AppCompatActivity {
Button b;
 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 EdgeToEdge.enable(this);
 setContentView(R.layout.failure);
 
ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main
), (v, insets) -> {
 Insets systemBars = 
insets.getInsets(WindowInsetsCompat.Type.systemBars());
 v.setPadding(systemBars.left, systemBars.top, 
systemBars.right, systemBars.bottom);
 return insets;
 });
 b = (Button) findViewById(R.id.button2);
 b.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View view) {
 Intent i = new Intent(getBaseContext(), MainActivity.class);
 startActivity(i);
 }
 });
 }
}
