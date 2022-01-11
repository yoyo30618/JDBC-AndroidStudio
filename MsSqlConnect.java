package com.example.mysqltest;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*-------------上方為程式自動產生 用於建置APP用---------------------------*/
public class MsSqlConnect {

    // 資料庫定義
    //String mysql_ip = "210.240.163.28";//資料庫的IP
    String mysql_ip = "210.240.170.186";//資料庫的IP
    int mysql_port = 3306; // Port 預設為 3306
    //String db_name = "car";//資料表
    String db_name = "newdatabase";//資料表
    String url = "jdbc:mysql://"+mysql_ip+":"+mysql_port+"/"+db_name+"?useUnicode=true&characterEncoding=UTF-8";//套用JDBC的驅動
    //String db_user = "yoyo30618";//資料庫帳號
    String db_user = "lulu";//資料庫帳號
    //String db_password = "yoyo0516";//資料庫密碼
    String db_password = "nttucsie";//資料庫密碼

    public void run() {//初步連線
        //嘗試使用驅動
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Log.v("DB","加載驅動成功");
        }catch( ClassNotFoundException e) {
            Log.e("DB","加載驅動失敗");
            return;
        }

        // 連接資料庫
        try {
            Connection con = DriverManager.getConnection(url,db_user,db_password);
            Log.v("DB","遠端連接成功");
        }catch(SQLException e) {
            Log.e("DB","遠端連接失敗");
            Log.e("DB", e.toString());
        }
    }

    public String getData() {//下載資料部分
        String data = "";
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);//進行一個sql的連線
            String sql = "SELECT * from cc";//指定要執行的sql指令(重點修改此處)
            Statement st = con.createStatement();//建立一個Statement 類似郵差的概念
            st.executeQuery(sql);//獲取資料用這個**注意與上傳不同
            ResultSet rs = st.executeQuery(sql);//把剛拿到的資料使用ResultSet接起來
            while (rs.next())//將得到的資料逐筆讀取
            {
                String ss = rs.getString("ss");
                String aa = rs.getString("aa");
                String dd = rs.getString("dd");
                String ee = rs.getString("ee");
                data += ss + ", " + aa + ", " + dd + ", " + ee + "\n";//此例是把她都串起來
            }
            st.close();//關閉郵差
        } catch (SQLException e) {//如果有狀況
            e.printStackTrace();//印出例外原因
        }
        return data;//回傳給呼叫的人
    }

    public String pushData() {//上傳資料的部分
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);//進行一個sql的連線
            String test="20220108中文測試";
            int test2=99;
            String sql = "INSERT INTO cc(ss, aa, dd, ee) VALUES ('"+test+"','"+test2+"','91234219','99')";//指定要執行的sql指令(重點修改此處)
            Log.e("DB",sql);
            Statement st = con.createStatement();//建立一個Statement 類似郵差的概念

            st.executeUpdate(sql);//新增資料用這個(郵差把指令遞送)**注意與下載不同
            st.close();//關閉郵差
        } catch (SQLException e) {//如果有狀況
            e.printStackTrace();//印出例外原因
        }
        return "OK";
    }
    /*-------------下方為程式自動產生 用於建置APP用---------------------------*/
}
