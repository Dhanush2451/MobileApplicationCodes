package com.example.texttospeech;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Locale;
public class MainActivity extends AppCompatActivity {
 TextToSpeech t1;
 EditText e1;
 Button b;
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
 e1 = (EditText)findViewById(R.id.editText);
 b = (Button)findViewById(R.id.button);
 t1 = new TextToSpeech(getApplicationContext(), new 
TextToSpeech.OnInitListener() {
 @Override
 public void onInit(int status) {
 if (status!=TextToSpeech.ERROR){
 t1.setLanguage(Locale.UK);
 }
 }
 });
 b.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View view) {
 String tospeak = e1.getText().toString();
 
Toast.makeText(getBaseContext(),tospeak,Toast.LENGTH_LONG).show(
);
 t1.speak(tospeak,TextToSpeech.QUEUE_FLUSH,null);
 }
 });
 }
}
