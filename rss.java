package com.example.rssfeed;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
public class MainActivity extends AppCompatActivity {
 List headlines;
 List links;
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
 new MyAsyncTask().execute();
 }
 class MyAsyncTask extends AsyncTask<Object,Void, 
ArrayAdapter>
 {
 @Override
 protected ArrayAdapter doInBackground(Object[] params)
 {
 headlines = new ArrayList();
 links = new ArrayList();
 try
 {
 URL url = new URL("https://codingconnect.net/feed");
// URL url = new URL("https://xkcd.com/rss.xml");
 XmlPullParserFactory factory = 
XmlPullParserFactory.newInstance();
 factory.setNamespaceAware(false);
 XmlPullParser xpp = factory.newPullParser();
// We will get the XML from an input stream
 xpp.setInput(getInputStream(url), "UTF_8");
 boolean insideItem = false;
// Returns the type of current event: START_TAG, END_TAG, etc..
 int eventType = xpp.getEventType();
 while (eventType != XmlPullParser.END_DOCUMENT)
 {
 if (eventType == XmlPullParser.START_TAG)
 {
 if 
(xpp.getName().equalsIgnoreCase("item"))
 {
 insideItem = true;
 }
 else if 
(xpp.getName().equalsIgnoreCase("title"))
 {
 if (insideItem)
 headlines.add(xpp.nextText()); 
//extract the headline
 }
 else if 
(xpp.getName().equalsIgnoreCase("link"))
 {
 if (insideItem)
 links.add(xpp.nextText()); 
//extract the link of article
 }
 }
 else if(eventType==XmlPullParser.END_TAG && 
xpp.getName().equalsIgnoreCase("item"))
 {
 insideItem=false;
 }
 eventType = xpp.next(); //move to next 
element
 }
 }
 catch (MalformedURLException e)
 {
 e.printStackTrace();
 }
 catch (XmlPullParserException e)
 {
 e.printStackTrace();
 }
 catch (IOException e)
 {
 e.printStackTrace();
 }
 return null;
 }
 protected void onPostExecute(ArrayAdapter adapter)
 {
 adapter = new ArrayAdapter(MainActivity.this, 
android.R.layout.simple_list_item_1,headlines);
 ListView lv = (ListView)findViewById(R.id.listView);
 lv.setAdapter(adapter);
 }
 }
 protected void onListItemClick(ListView l, View v, int
position, long id)
 {
 Uri uri = Uri.parse((links.get(position)).toString());
 Intent intent = new Intent(Intent.ACTION_VIEW, uri);
 startActivity(intent);
 }
 public InputStream getInputStream(URL url)
 {
 try
 {
 return url.openConnection().getInputStream();
 }
 catch (IOException e)
 {
 return null;
 }
 }
}
