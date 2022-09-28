package com.example.sms_android;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText txtMobile;
    private EditText txtMessage;
    private Button btnSms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMobile = (EditText)findViewById(R.id.mblTxt);
        txtMessage = (EditText)findViewById(R.id.msgTxt);
        btnSms = (Button)findViewById(R.id.btnSend);
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // có 2 cách send sms là dùng smsmanager hoặc intent
//                sendSMSManager();
                sendIntent();
            }
        });
    }

    private void sendSMSManager(){
        try{
            SmsManager smgr = SmsManager.getDefault();
            smgr.sendTextMessage(txtMobile.getText().toString(),null,txtMessage.getText().toString(),null,null);
            Toast.makeText(MainActivity.this, "Gửi tin nhắn thành công!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            System.out.println(e);
            Toast.makeText(MainActivity.this, "Đã có lỗi khi gửi tin nhắn!", Toast.LENGTH_SHORT).show();
        }
    }

    private  void sendIntent(){
        try{
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("smsto:"));
            i.setType("vnd.android-dir/mms-sms");
            i.putExtra("address", new String(txtMobile.getText().toString()));
            i.putExtra("sms_body",txtMessage.getText().toString());
            startActivity(Intent.createChooser(i, "Gửi tin nhắn qua:"));
        }
        catch(Exception e){
            Toast.makeText(MainActivity.this, "Đã có lỗi khi gửi tin nhắn!", Toast.LENGTH_SHORT).show();
        }
    }
}