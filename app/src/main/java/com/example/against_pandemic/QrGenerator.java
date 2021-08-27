package com.example.against_pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import java.util.Random;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QrGenerator extends AppCompatActivity {
    String TAG="GenerateQrCode";
    EditText qrCodeE1;
    ImageView qrCodeI1;
    Button qrCodeB1;
    Button qrCodeb2;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    static String inputValue;
   // DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_generator);
    //    reference= FirebaseDatabase.getInstance().getReference();
     //   qrCodeI1=(ImageView)findViewById(R.id.QrCodeI1);
     //   qrCodeB1=(Button)findViewById(R.id.QrCodeB1);
    //    qrCodeb2=(Button)findViewById(R.id.qrCodeb2);
    //    qrCodeE1=(EditText)findViewById(R.id.QrCodeE1);
        qrCodeb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final Random random=new Random();
               final int myRandomNumber= random.nextInt(10000000);

                String key=reference.push().getKey();
                Toast.makeText(getApplicationContext(),key,Toast.LENGTH_LONG).show();
                inputValue=key;
                if(inputValue.length()>0){
                    WindowManager manager=(WindowManager)getSystemService(WINDOW_SERVICE);
                    Display display=manager.getDefaultDisplay();
                    Point point=new Point();
                    display.getSize(point);
                    int width=point.x;
                    int height=point.y;
                    int smallerDimension=width<height ? width:height;
                    smallerDimension=smallerDimension*3/4;
                    qrgEncoder =new QRGEncoder(inputValue,null, QRGContents.Type.TEXT,smallerDimension);
                    try{
                        bitmap=qrgEncoder.encodeAsBitmap();
                        qrCodeI1.setImageBitmap(bitmap);
                    }
                    catch (WriterException e){
                        Log.v(TAG,e.toString());
                    }
                }
                else{
                    qrCodeE1.setError("Required");
                }

            }
        });
        qrCodeB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                inputValue=qrCodeE1.getText().toString().trim();
                if(inputValue.length()>0){
                    WindowManager manager=(WindowManager)getSystemService(WINDOW_SERVICE);
                    Display display=manager.getDefaultDisplay();
                    Point point=new Point();
                    display.getSize(point);
                    int width=point.x;
                    int height=point.y;
                    int smallerDimension=width<height ? width:height;
                    smallerDimension=smallerDimension*3/4;
                    qrgEncoder =new QRGEncoder(inputValue,null, QRGContents.Type.TEXT,smallerDimension);
                    try{
                        bitmap=qrgEncoder.encodeAsBitmap();
                        qrCodeI1.setImageBitmap(bitmap);
                    }
                    catch (WriterException e){
                        Log.v(TAG,e.toString());
                    }
                }
                else{
                    qrCodeE1.setError("Required");
                }
            }
        });

    }
    String getQrCode(){
        return inputValue;
    }

}