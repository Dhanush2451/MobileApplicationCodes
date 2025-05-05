package com.example.externalstorage;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
public class MainActivity extends AppCompatActivity {
 EditText data;
 Button save,read;
 TextView res;
 private String filename = "SampleFile.txt";
 private String filepath = "MyFileStorage";
 File myExternalFile;
 String myData = "";
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
 data = (EditText) findViewById(R.id.data);
 res = (TextView) findViewById(R.id.Responce);
 save = (Button) findViewById(R.id.write);
 save.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View view) {
 try {
 FileOutputStream fos = new 
FileOutputStream(myExternalFile);
 
fos.write(data.getText().toString().getBytes());
 fos.close();
 } catch (IOException e) {
 e.printStackTrace();
 }
 data.setText("");
 res.setText("SampleFile.txt saved to External 
Storage...");
 }
 });
 read = (Button) findViewById(R.id.read);
 read.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View view) {
 try {
 FileInputStream fis = new 
FileInputStream(myExternalFile);
 DataInputStream in = new 
DataInputStream(fis);
 BufferedReader br = new BufferedReader(new 
InputStreamReader(in));
 String strLine;
 while ((strLine = br.readLine()) != null) {
 myData = myData + strLine;
 }
 in.close();
 } catch (IOException e) {
 e.printStackTrace();
 }
 data.setText(myData);
 res.setText("SampleFile.txt data retrieved from 
Internal Storage...");
 }
 });
 if (!isExternalStorageAvailable() || 
isExternalStorageReadOnly()) {
 save.setEnabled(false);
 }
 else {
 myExternalFile = new 
File(getExternalFilesDir(filepath), filename);
 }
 }
 private static boolean isExternalStorageReadOnly() {
 String extStorageState = 
Environment.getExternalStorageState();
 if 
(Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
 return true;
 }
 return false;
 }
 private static boolean isExternalStorageAvailable() {
 String extStorageState = 
Environment.getExternalStorageState();
 if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
 return true;
 }
 return false;
 }
 }
