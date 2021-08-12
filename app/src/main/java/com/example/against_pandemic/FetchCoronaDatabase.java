package com.example.against_pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FetchCoronaDatabase extends AppCompatActivity {
Button fetchData;
TextView coronaData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_corona_database);
        fetchData=(Button) findViewById(R.id.fetchData);
        coronaData=(TextView) findViewById(R.id.coronaData);
        fetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            getApiData();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }


        });
    }
    public void getApiData() throws IOException {
        Document document=null;
        document= Jsoup.connect("http://dashboard.dghs.gov.bd/webportal/pages/covid19.php").get();
      String title=document.title();
      Element a= document.getElementById("example");
      String[] sp=String.valueOf(a).split("\\D");
      Log.d("asd",String.valueOf(sp.length));
      String[] strings=new String[10000];
        HashMap<String, String> districtWiseResult = new HashMap<String, String>();
int j=0,i=0;
   for( i=0;i<sp.length;i++){
       if(sp[i]!=null && !sp[i].equals("")) {
           strings[j++]=sp[i];



       }
   }




//       String b= a.data();
//        Pattern pat = Pattern.compile("\\d+");
//
//        Matcher mat = pat.matcher(b);
//
//        while (mat.find())
//           Log.d("Match: " , mat.group());
       // Log.d("as", String.valueOf(b));
        districtWiseResult.put("Barguna",strings[0]);
        districtWiseResult.put("Barishal",strings[1]);
        districtWiseResult.put("Bhola",strings[2]);
        districtWiseResult.put("Jhalokati",strings[3]);
        districtWiseResult.put("Patuakhali",strings[4]);
        Log.d("asdas",String.valueOf(districtWiseResult));

    }


}