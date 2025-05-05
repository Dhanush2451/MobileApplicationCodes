package com.example.productdatabase;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {
EditText pid,pname,price;
Button insert, display;
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
 pid = (EditText) findViewById(R.id.pid);
 pname = (EditText) findViewById(R.id.pname);
 price = (EditText) findViewById(R.id.price);
 insert = (Button) findViewById(R.id.insert);
 display = (Button) findViewById(R.id.display);
 
db = openOrCreateDatabase("ProductDB", Context.MODE_PRIVATE, 
null);
 db.execSQL("CREATE TABLE IF NOT EXISTS product(pid 
VARCHAR,pname VARCHAR,price VARCHAR);");
 insert.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View view) {
 if (pid.getText().toString().trim().length() == 0 || 
pname.getText().toString().trim().length() == 0 || 
price.getText().toString().trim().length() == 0) {
 // Toast.makeText(getBaseContext(),"Enter all 
values",Toast.LENGTH_LONG);
 showMessage("Error", "Please enter all values");
 return;
 }
 db.execSQL("INSERT INTO product VALUES('" + 
pid.getText() + "','" + pname.getText() + "','" + 
price.getText() + "');");
 // Toast.makeText(getBaseContext(),"Record added 
successfully",Toast.LENGTH_LONG);
 showMessage("Success", "Record added");
 }
 });
 display.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View view) {
 Cursor c = db.rawQuery("SELECT * FROM product", null);
 if (c.getCount() == 0) {
 // Toast.makeText(getBaseContext()," Error : 
No records found",Toast.LENGTH_LONG);
 showMessage("Error", "No records found");
 return;
 }
 StringBuffer buffer = new StringBuffer();
 while (c.moveToNext()) {
 buffer.append("PID: " + c.getString(0) + "\n");
 buffer.append("PNAME: " + c.getString(1) + "\n");
 buffer.append("PRICE: " + c.getString(2) + "\n\n");
 }
 // Toast.makeText(getBaseContext(),"Product 
Details" + buffer.toString(),Toast.LENGTH_LONG);
 showMessage("Product Details", buffer.toString());
 }
 });
 }
 public void showMessage(String title, String message) {
 AlertDialog.Builder builder = new AlertDialog.Builder(this);
 builder.setCancelable(true);
 builder.setTitle(title);
 builder.setMessage(message);
 builder.show();
 }
}
OUTPUT ON EM
