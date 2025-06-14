package com.example.studentdatabase;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog.Builder;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {
 EditText Rollno, Name, Marks;
 Button Insert, Delete, Update, View1, ViewAll;
 SQLiteDatabase db;
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
 Rollno = (EditText) findViewById(R.id.Rollno);
 Name = (EditText) findViewById(R.id.Name);
 Marks = (EditText) findViewById(R.id.Marks);
 Insert = (Button) findViewById(R.id.Insert);
 Delete = (Button) findViewById(R.id.Delete);
 Update = (Button) findViewById(R.id.Update);
 View1 = (Button) findViewById(R.id.View);
 ViewAll = (Button) findViewById(R.id.ViewAll);
 Insert.setOnClickListener(this::onClick);
 Delete.setOnClickListener(this::onClick);
 Update.setOnClickListener(this::onClick);
 View1.setOnClickListener(this::onClick);
 ViewAll.setOnClickListener(this::onClick);
 // Creating database and table
 db = openOrCreateDatabase("StudentDB", 
Context.MODE_PRIVATE, null);
 db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno 
VARCHAR,name VARCHAR,marks VARCHAR);");
 }
 public void onClick(View view) {
 // Inserting a record to the Student table
 if (view == Insert) {
 // Checking for empty fields
 if (Rollno.getText().toString().trim().length() == 0 
|| Name.getText().toString().trim().length() == 0 || 
Marks.getText().toString().trim().length() == 0) {
 showMessage("Error", "Please enter all values");
 return;
 }
 db.execSQL("INSERT INTO student VALUES('" + 
Rollno.getText() + "','" + Name.getText() + "','" + 
Marks.getText() + "');");
 showMessage("Success", "Record added");
 clearText();
 }
 // Deleting a record from the Student table
 if (view == Delete) {
 // Checking for empty roll number
 if (Rollno.getText().toString().trim().length() == 
0) {
 showMessage("Error", "Please enter Rollno");
 return;
 }
 Cursor c = db.rawQuery("SELECT * FROM student WHERE 
rollno='" + Rollno.getText() + "'", null);
 if (c.moveToFirst()) {
 db.execSQL("DELETE FROM student WHERE rollno='" 
+ Rollno.getText() + "'");
 showMessage("Success", "Record Deleted");
 } else {
 showMessage("Error", "Invalid Rollno");
 }
 clearText();
 }
 // Updating a record in the Student table
 if (view == Update) { // Checking for empty roll number
 if (Rollno.getText().toString().trim().length() == 
0) {
 showMessage("Error", "Please enter Rollno");
 return;
 }
 Cursor c = db.rawQuery("SELECT * FROM student WHERE 
rollno='" + Rollno.getText() + "'", null);
 if (c.moveToFirst()) {
 db.execSQL("UPDATE student SET name='" + 
Name.getText() + "',marks='" + Marks.getText() + "' WHERE 
rollno='" + Rollno.getText() + "'");
 showMessage("Success", "Record Modified");
 } else {
 showMessage("Error", "Invalid Rollno");
 }
 clearText();
 }
 // Display a record from the Student table
 if (view == View1) {
 // Checking for empty roll number
 if (Rollno.getText().toString().trim().length() == 
0) {
 showMessage("Error", "Please enter Rollno");
 return;
 }
 Cursor c = db.rawQuery("SELECT * FROM student WHERE 
rollno='" + Rollno.getText() + "'", null);
 if (c.moveToFirst()) {
 Name.setText(c.getString(1));
 Marks.setText(c.getString(2));
 } else {
 showMessage("Error", "Invalid Rollno");
 clearText();
 }
 }
 // Displaying all the records
 if (view == ViewAll) {
 Cursor c = db.rawQuery("SELECT * FROM student", 
null);
 if (c.getCount() == 0) {
 showMessage("Error", "No records found");
 return;
 }
 StringBuffer buffer = new StringBuffer();
 while (c.moveToNext()) {
 buffer.append("Rollno: " + c.getString(0) + 
"\n");
 buffer.append("Name: " + c.getString(1) + "\n");
 buffer.append("Marks: " + c.getString(2) + 
"\n\n");
 }
 showMessage("Student Details", buffer.toString());
 }
 }
 public void showMessage(String title, String message) {
 Builder builder = new Builder(this);
 builder.setCancelable(true);
 builder.setTitle(title);
 builder.setMessage(message);
 builder.show();
 }
 public void clearText() {
 Rollno.setText("");
 Name.setText("");
 Marks.setText("");
 Rollno.requestFocus();
 }
}
