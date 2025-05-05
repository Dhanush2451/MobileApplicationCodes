package com.example.sendnotification;
import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {
 EditText msg;
 Button send;
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
String[]{Manifest.permission.POST_NOTIFICATIONS}, 100);
 }
 }
 msg = findViewById(R.id.msg);
 send = findViewById(R.id.send);
 send.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View v) {
 String message = msg.getText().toString();
 int notificationId = (int) 
System.currentTimeMillis();
 Intent notificationIntent = new 
Intent(MainActivity.this, MainActivity.class);
 PendingIntent pendingIntent = 
PendingIntent.getActivity(MainActivity.this, 0, 
notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT | 
PendingIntent.FLAG_IMMUTABLE);
 NotificationCompat.Builder builder = new 
NotificationCompat.Builder(MainActivity.this, 
"notificationChannel")
 
.setSmallIcon(android.R.drawable.ic_dialog_info)
 .setContentTitle("Simple Notification")
 .setContentText(message)
 .setAutoCancel(true)
 .setContentIntent(pendingIntent);
NotificationManager notificationManager = (NotificationManager) 
MainActivity.this.getSystemService(Context.NOTIFICATION_SERVICE)
;
 if (notificationManager != null) {
 notificationManager.notify(notificationId, 
builder.build()); // Unique notification ID
 }
// Create the notification channel (required for Android 8.0 and 
above)
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
 NotificationChannel channel = new 
NotificationChannel("notificationChannel", "Notification", 
NotificationManager.IMPORTANCE_HIGH);
 
notificationManager.createNotificationChannel(channel);
 }
 }
 });
 }
}
