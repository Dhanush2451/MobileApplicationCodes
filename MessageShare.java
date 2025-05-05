package com.example.sendmessage;
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
 EditText m;
 Button b;
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
 m = (EditText) findViewById(R.id.msg);
 b = (Button) findViewById(R.id.button);
 b.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View view) {
 String message = m.getText().toString();
 Intent i = new Intent(getBaseContext(), Second.class);
 i.putExtra("key",message);
 startActivity(i);
 }
 });
 }
}
Second.java
package com.example.sendmessage;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class Second extends AppCompatActivity {
 TextView t;
 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 EdgeToEdge.enable(this);
 setContentView(R.layout.second);
 
ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main
), (v, insets) -> {
 Insets systemBars = 
insets.getInsets(WindowInsetsCompat.Type.systemBars());
 v.setPadding(systemBars.left, systemBars.top, 
systemBars.right, systemBars.bottom);
 return insets;
 });
 t = (TextView)findViewById(R.id.textView2);
 Intent i = getIntent();
 String msg = i.getStringExtra("key");
 t.setText("Message is: "+msg);
 Toast.makeText(getBaseContext(),"Received Msg from first 
Page",1).show();
 }
}
