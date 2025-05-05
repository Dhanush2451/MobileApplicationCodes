package com.example.gps;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {
 private static final int REQUEST_LOCATION = 1;
 Button gps;
 TextView loc;
 LocationManager locationManager;
 String latitude, longitude;
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
 
 gps = (Button) findViewById(R.id.button);
 loc = (TextView) findViewById(R.id.loc);
 ActivityCompat.requestPermissions(this,
 new 
String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 
REQUEST_LOCATION);
 gps.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View v) {
 locationManager = (LocationManager)
 
getSystemService(Context.LOCATION_SERVICE);
 Location locationGPS = 
locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDE
R);
 if (locationGPS != null) {
 double lat = locationGPS.getLatitude();
 double longi = locationGPS.getLongitude();
 latitude = String.valueOf(lat);
 longitude = String.valueOf(longi);
 loc.setText("Longitude=" + longitude + "\n" + "Latitude=" + 
latitude + "\n");
 } else {
 loc.setText("Unable to find GPS location");
 }
 }
 });
 }
}
