package com.example.alertdialogue;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {
 Button submitButton, closeButton;
 EditText e1;
 AlertDialog.Builder builder;
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
 e1 = (EditText) findViewById(R.id.e1);
 submitButton = (Button) findViewById(R.id.button);
 closeButton = (Button)findViewById(R.id.button2);
 builder = new AlertDialog.Builder(this);
 submitButton.setOnClickListener(new 
View.OnClickListener() {
 @Override
 public void onClick(View v) {
 builder.setMessage(R.string.dialog_message) 
.setTitle(R.string.dialog_title);
//Setting message manually and performing action on button click
 builder.setMessage("Do you want to submit your 
Message ?")
 .setCancelable(false)
 .setPositiveButton("Yes", new 
DialogInterface.OnClickListener() {
 public void onClick(DialogInterface 
dialog, int id) {
 String s1 = " Your Message: 
"+e1.getText().toString()+" has been submitted successfully";
// finish();
 dialog.cancel();
 
Toast.makeText(getApplicationContext(),s1,Toast.LENGTH_SHORT).sh
ow();
 }
 })
 .setNegativeButton("No", new 
DialogInterface.OnClickListener() {
 public void onClick(DialogInterface 
dialog, int id) {
// Action for 'NO' Button
 dialog.cancel();
 
Toast.makeText(getApplicationContext(),"You choosen not to send 
message", Toast.LENGTH_SHORT).show();
 }
 });
//Creating dialog box
 AlertDialog alert = builder.create();
//Setting the title manually
 alert.setTitle("Alert Dialog");
 alert.show();
 }
 });
 closeButton.setOnClickListener(new 
View.OnClickListener() {
 @Override
 public void onClick(View v) {
//Uncomment the below code to Set the message and title from the 
strings.xml file
 
builder.setMessage(R.string.dialog_message).setTitle(R.string.di
alog_title);
//Setting message manually and performing action on button click
 builder.setMessage("Do you want to close this 
application ?").setCancelable(false)
 .setPositiveButton("Yes", new 
DialogInterface.OnClickListener() {
 
public void onClick(DialogInterface dialog, int id) {
 finish();
 
Toast.makeText(getApplicationContext(), "Your App has been 
Closed", Toast.LENGTH_SHORT).show();
 }
 })
 .setNegativeButton("No", new 
DialogInterface.OnClickListener() {
 public void onClick(DialogInterface dialog, int id) {
// Action for 'NO' Button
 dialog.cancel();
 
Toast.makeText(getApplicationContext(), "You choosen not to 
close App",Toast.LENGTH_SHORT).show();
 }
 });
 AlertDialog alert = builder.create();
//Setting the title manually
 alert.setTitle("Alert Dialog");
 alert.show();
 }
 });
 }
}
Strings
