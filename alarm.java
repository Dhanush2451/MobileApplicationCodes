package com.example.alarmclock;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Calendar;
public class MainActivity extends AppCompatActivity {
 TimePicker alarmTimePicker;
 PendingIntent pendingIntent;
 AlarmManager alarmManager;
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
 
alarmTimePicker = (TimePicker) findViewById(R.id.timePicker);
alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
 }
 public void OnToggleClicked(View view)
 {
 long time;
 if (((ToggleButton)view).isChecked())
 {
 Toast.makeText(MainActivity.this, "ALARM ON", 
Toast.LENGTH_SHORT).show();
 Calendar calendar = Calendar.getInstance();
 calendar.set(Calendar.HOUR_OF_DAY, 
alarmTimePicker.getCurrentHour());
 calendar.set(Calendar.MINUTE, 
alarmTimePicker.getCurrentMinute());
 Intent intent = new Intent(this, 
AlarmReceiver.class);
 //startActivity(intent);
 PendingIntent pendingIntent = 
PendingIntent.getBroadcast(this, 0, intent, 
PendingIntent.FLAG_IMMUTABLE);
 time = (calendar.getTimeInMillis() -
(calendar.getTimeInMillis() % 60000));
 if (System.currentTimeMillis() > time)
 {
 if (Calendar.AM_PM == 0)
 time = time + (1000 * 60 * 60 * 12);
 else time = time + (1000 * 60 * 60 * 24);
 }
 alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 
10000, pendingIntent);
 // alarmManager.set(AlarmManager.RTC_WAKEUP, 
System.currentTimeMillis() + (time * 1000), pendingIntent);
 }
 else { alarmManager.cancel(pendingIntent);
 Toast.makeText(MainActivity.this, "ALARM OFF", 
Toast.LENGTH_SHORT).show();
 }
 }
}
AlarmReceiver.java
package com.example.alarmclock;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
public class AlarmReceiver extends BroadcastReceiver
{
 // @RequiresApi(api = Build.VERSION_CODES.Q)
 @Override // implement onReceive() method
 public void onReceive(Context context, Intent intent)
 {
 // we will use vibrator first
 Vibrator vibrator = (Vibrator) 
context.getSystemService(Context.VIBRATOR_SERVICE);
 vibrator.vibrate(4000);
 Toast.makeText(context, "Alarm! Wake up! Wake up!", 
Toast.LENGTH_LONG).show();
 Uri alarmUri = 
RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
 if (alarmUri == null)
 {
 alarmUri = 
RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
 }
 // setting default ringtone
 Ringtone ringtone = RingtoneManager.getRingtone(context, 
alarmUri);
 // play ringtone
 ringtone.play();
 }
}
