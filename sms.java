package com.example.sendsms;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {
 EditText phone,msg;
 Button sms;
 
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
 if (Build.VERSION.SDK_INT >= 
Build.VERSION_CODES.TIRAMISU) {
 if (ContextCompat.checkSelfPermission(this, 
android.Manifest.permission.POST_NOTIFICATIONS) != 
PackageManager.PERMISSION_GRANTED) {
 ActivityCompat.requestPermissions(this, new 
String[]{Manifest.permission.SEND_SMS}, 100);
 }
 }
 phone = (EditText)findViewById(R.id.pno);
 msg = (EditText)findViewById(R.id.msg);
 sms = (Button)findViewById(R.id.button);
 sms.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View v) {
 try{
 SmsManager smgr = SmsManager.getDefault();
 
smgr.sendTextMessage(phone.getText().toString(),null,msg.getText
().toString(),null,null);
 
Toast.makeText(MainActivity.this, "SMS Sent Successfully", 
Toast.LENGTH_SHORT).show();
 }
 catch (Exception e){
 
Toast.makeText(MainActivity.this, "SMS Failed to Send, Please 
try again", Toast.LENGTH_SHORT).show();
 }
 }
 });
 }
}
