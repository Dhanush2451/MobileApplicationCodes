package com.example.multithreading;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {
Button mt;
TextView th;
 private static final int t1 = 1;
 private static final int t2 = 2;
 private static final int t3 = 3;
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
 mt = (Button) findViewById(R.id.button);
 th = (TextView) findViewById(R.id.textView);
 Handler handler = new Handler() {
 public void handleMessage(android.os.Message 
msg) {
 if (msg.what == t1) {
 th.append("\nIn Thread 1");
 }
 if (msg.what == t2) {
 th.append("\nIn Thread 2");
 }
 if (msg.what == t3) {
 th.append("\nIn Thread 3");
 }
 } };
 Thread thread1 = new Thread(new Runnable() {
 @Override
 public void run()
 {
 for (int i = 0; i < 5; i++)
 {
 try { Thread.sleep(1000);
 } catch(InterruptedException e)
 { e.printStackTrace(); }
 handler.sendEmptyMessage(t1);
 }}});
 Thread thread2 = new Thread(new Runnable() {
 @Override public void run()
 { for (int i = 0; i < 5; i++)
 {
 try { Thread.sleep(1000); }
 catch(InterruptedException e)
 { e.printStackTrace(); }
 handler.sendEmptyMessage(t2);
 } } });
 Thread thread3 = new Thread(new Runnable()
 {
 @Override
 public void run()
 { for (int i = 0; i < 5; i++)
 { try { Thread.sleep(1000); }
 catch(InterruptedException e)
 { e.printStackTrace(); }
 handler.sendEmptyMessage(t3); } } });
 mt.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View view) {
 th.setText("Main thread");
 thread1.start();
 thread2.start();
 thread3.start();
 }
 });
 }
}
