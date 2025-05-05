package com.example.sendemail;
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
 Button send;
 EditText to, subject, body;
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
 to = findViewById(R.id.email);
 subject = findViewById(R.id.sub);
 body = findViewById(R.id.body);
 send = findViewById(R.id.button);
 send.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View view) {
 String emailsend = to.getText().toString();
 String emailsubject = subject.getText().toString();
 String emailbody = body.getText().toString();
 Intent i = new Intent(Intent.ACTION_SEND);
 i.putExtra(Intent.EXTRA_EMAIL, new String[]{emailsend});
 i.putExtra(Intent.EXTRA_SUBJECT, emailsubject);
 i.putExtra(Intent.EXTRA_TEXT, emailbody);
 i.setType("message/rfc822");
 startActivity(Intent.createChooser(i, "Choose an 
Email client :"));
 }
 });
 }
}
