package com.example.filereadwrite;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {
EditText data;
Button read, write;
private static final String FILE_NAME = "example.txt";
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
 read = (Button) findViewById(R.id.read);
 write = (Button) findViewById(R.id.write);
 read.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View view) {
 FileInputStream fis = null;
 try {
 fis = openFileInput(FILE_NAME);
 InputStreamReader isr = new 
InputStreamReader(fis);
 BufferedReader br = new BufferedReader(isr);
 StringBuilder sb = new StringBuilder();
 String text;
 while ((text = br.readLine()) != null) {
 sb.append(text).append("\n");
 }
 data.setText(sb.toString());
 } catch (FileNotFoundException e) {
 e.printStackTrace();
 } catch (IOException e) {
 e.printStackTrace();
 } finally {
 if (fis != null) {
 try {
 fis.close();
 } catch (IOException e) {
 e.printStackTrace();
 }
 }
 }
 }
 });
 write.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View view) {
 String text = data.getText().toString();
 FileOutputStream fos = null;
 try {
 fos = openFileOutput(FILE_NAME, 
MODE_PRIVATE);
 fos.write(text.getBytes());
 data.getText().clear();
 
Toast.makeText(getApplicationContext(),"Saved to " + 
getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
 } catch (FileNotFoundException e) {
 e.printStackTrace();
 } catch (IOException e) {
 e.printStackTrace();
 } finally {
 if (fos != null) {
 try {
 fos.close();
 } catch (IOException e) {
 e.printStackTrace();
 }
 }
 }
 }
 });
 }
}
