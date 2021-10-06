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
public static String[] district=new String[10000];
    public static String[] strings=new String[10000];
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

        HashMap<String, String> districtWiseResult = new HashMap<String, String>();
int j=0,i=0;
   for( i=0;i<sp.length;i++){
       if(sp[i]!=null && !sp[i].equals("")) {
           strings[j++]=sp[i];



       }
   }


        district[0]="Barguna";
        district[1]="Barishal";
        district[2]="Bhola";
        district[3]="Jhalokati";
        district[4]="Patuakhali";
        district[5]="Pirojpur";
        district[6]="Bandarban";
        district[7]="Brahmanbaria";
        district[8]="Chandpur";
        district[9]="Chattogram";
        district[10]="Cox's Bazar";
        district[11]="Cumilla";
        district[12]="Feni";
        district[13]="Khagrachhari";
        district[14]="Lakshmipur";
        district[15]="Noakhali";
        district[16]="Rangamati";
        district[17]="Dhaka";
        district[18]="Faridpur";
        district[19]="Gazipur";
        district[20]="Gopalganj";
        district[21]="Kishorgonj";
        district[22]="Madaripur";
        district[23]="Manikganj";
        district[24]="Munshiganj";
        district[25]="Narayanganj";
        district[26]="Narsingdi";
        district[27]="Rajbari";
        district[28]="Shariatpur";
        district[29]="Tangail";
        district[30]="Bagerhat";
        district[31]="Chuadanga";
        district[32]="Jashore";
        district[33]="Jenaidah";
        district[34]="Khulna";
        district[35]="Kushtia";
        district[36]="Magura";
        district[37]="Meherpur";
        district[38]="Narail";
        district[39]="Satkhira";
        district[40]="Jamalpur";
        district[41]="Mymensingh";
        district[42]="Netrakona";
        district[43]="Sherpur";
        district[44]="Bogura";
        district[45]="Chapai Nawabganj";
        district[46]="Joypurhat";
        district[47]="Naogaon";
        district[48]="Natore";
        district[49]="Pabna";
        district[50]="Rajshahi";
        district[51]="Sirajganj";
        district[52]="Dinajpur";
        district[53]="Gaibandha";
        district[54]="Kurigram";

        district[55]="Lalmonirhat";

        district[56]="Nilphamari";
        district[57]="Panchagarh";
        district[58]="Rangpur";
        district[59]="Thakurgaon";
        district[60]="Habiganj";
        district[61]="Maulvibazar";

        district[62]="Sunamganj";
        district[63]="Sylhet";



for(i=0;i<j-3;i++){
    Log.d(district[i],strings[i+3]);
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

public String[] getStringData(){
       return strings;
}
}