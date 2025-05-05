package com.example.graphics;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {
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
 Bitmap bg = Bitmap.createBitmap(720, 1280,
 Bitmap.Config.ARGB_8888);
//Setting the Bitmap as background for the ImageView
 ImageView i = (ImageView) findViewById(R.id.imageView);
 i.setBackgroundDrawable(new BitmapDrawable(bg));
//Creating the Canvas Object
 Canvas canvas = new Canvas(bg);
//Creating the Paint Object and set its color & TextSize
 Paint paint = new Paint();
 paint.setColor(Color.BLUE);
 paint.setTextSize(50);
//To draw a Rectangle
 canvas.drawText("Rectangle", 420, 150, paint);
 paint.setColor(Color.RED);
 canvas.drawRect(400, 200, 650, 700, paint);
//To draw a Circle
 canvas.drawText("Circle", 120, 150, paint);
 paint.setColor(Color.YELLOW);
 canvas.drawCircle(200, 350, 150, paint);
 paint.setColor(Color.BLACK);
 canvas.drawCircle(150, 320, 20, paint);
 paint.setColor(Color.BLACK);
 canvas.drawCircle(250, 320, 20, paint);
 paint.setColor(Color.RED);
 canvas.drawArc(120, 375, 275,460,0,180,false, paint);
//To draw a Square
 canvas.drawText("Square", 120, 800, paint);
 paint.setColor(Color.YELLOW);
 canvas.drawRect(50, 850, 350, 1150, paint);
//To draw a Line
 canvas.drawText("Line", 480, 800, paint);
 paint.setColor(Color.CYAN);
 canvas.drawLine(520, 850, 520, 1150, paint);
 }
}
