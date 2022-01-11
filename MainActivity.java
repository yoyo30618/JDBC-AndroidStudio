package com.example.mysqltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*-------------上方為程式自動產生 用於建置APP用---------------------------*/
        setContentView(R.layout.activity_main);//將顯示畫面轉於主畫面
        final TextView text_view = (TextView) findViewById(R.id.textView);//將UI的textview綁定到後端java
        new Thread(new Runnable(){//開啟新的執行序(為了連線)
            @Override
            public void run(){//執行序執行
                MsSqlConnect con = new MsSqlConnect();//建立新的連線，使用的是MsSqlConnect裡的方法
                con.run();
                //con.pushData();
                final String data = con.pushData();//將我們的資料定義為con做getdata回傳的資料

                //Log.v("OK",data);//設定log 檢查用
                //text_view.post(new Runnable() {//因為是執行序 所以需要這樣寫
                //    public void run() {
                //       text_view.setText(data);//把剛得到的資料設定到UI
                //    }
                //});
            }
        }).start();
    }
    /*-------------下方為程式自動產生 用於建置APP用---------------------------*/
}