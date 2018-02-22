package exodiasolutions.ddcombine.DindayalCityMallApp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class ShopDetails extends AppCompatActivity {
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);
        id = getIntent().getStringExtra("id");

        select_Class_Loader2 loader = new select_Class_Loader2(ShopDetails.this);
        loader.execute();


       // Toast.makeText(ShopDetails.this, "Name:" +name+"\nID:"+id+"\nicon: "+icon, Toast.LENGTH_SHORT).show();
    }


    public class select_Class_Loader2 extends AsyncTask<Void,Void,String> {

        Context context;
        select_Class_Loader2(Context context)
        {
            this.context=context;
        }

    protected String doInBackground(Void... params) {

        String login_url= "https://corpzpro-incredible100rav.c9users.io/DDMALL/getshopdata.php";

        try{

            URL url =new URL(login_url);
            HttpURLConnection httpurlconnection= (HttpURLConnection) url.openConnection();
            httpurlconnection.setRequestMethod("POST");
             httpurlconnection.setDoOutput(true);
            httpurlconnection.setDoInput(true);


            OutputStream outputstream1 = httpurlconnection.getOutputStream();
            BufferedWriter bufferedwriter1 = new BufferedWriter(new OutputStreamWriter(outputstream1, "UTF-8"));
            String post_data1 = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
            bufferedwriter1.write(post_data1);
            bufferedwriter1.flush();
            bufferedwriter1.close();

            InputStream inputstream= httpurlconnection.getInputStream();
            BufferedReader bufferedreader= new BufferedReader(new InputStreamReader(inputstream,"iso-8859-1"));
            String result="";
            String line="";
            while((line = bufferedreader.readLine())!=null){
                result+=line;
            }
            bufferedreader.close();
            inputstream.close();
            httpurlconnection.disconnect();

            return result;

        }catch(MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

    }


    @Override
    protected void onPreExecute() {
        // Toast.makeText(Dashboard.this, "Yeah", Toast.LENGTH_SHORT).show();
        super.onPreExecute();
        // pb.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onPostExecute(String result ){
        //  Toast.makeText(context, ""+result, Toast.LENGTH_SHORT).show();
        // pb.setVisibility(View.INVISIBLE);
        if(!result.equalsIgnoreCase("")){
            Toast.makeText(context, ""+result, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Server is down :-(", Toast.LENGTH_SHORT).show();
        }



        //Toast.makeText(context, " result : -"+result, Toast.LENGTH_SHORT).show();
    }


}
}
