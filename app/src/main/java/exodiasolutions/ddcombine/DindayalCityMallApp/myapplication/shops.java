package exodiasolutions.ddcombine.DindayalCityMallApp.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import exodiasolutions.ddcombine.DindayalCityMallApp.Modal.ShopClass;
import exodiasolutions.ddcombine.DindayalCityMallApp.R;
import exodiasolutions.ddcombine.DindayalCityMallApp.RecyclerItemClickListener;
import exodiasolutions.ddcombine.DindayalCityMallApp.ShopDetails;

public class shops extends AppCompatActivity {
    private JSONArray category_json;
    private  RecyclerView recyclerView ;
    ProgressBar loader;
    private RecyclerCustomAdapter adapter;
    ArrayList<ShopClass> shop_arrayList = new ArrayList<>();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Write your logic here
                shops.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);
        Fresco.initialize(this);
        loader = (ProgressBar) findViewById(R.id.loader);
        final select_Class_Loader select_class_loader = new select_Class_Loader(this);
        select_class_loader.execute();
        ActionBar ab = getSupportActionBar();
        ab.setTitle("SHOPS");


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter= new RecyclerCustomAdapter(this,shop_arrayList);
        recyclerView.setAdapter(adapter);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);


        recyclerView.addOnItemTouchListener( new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override public void onItemClick(View view, int position) {
                // TODO Handle item click

                ShopClass c = shop_arrayList.get(position);
                Intent in = new Intent(shops.this,ShopDetails.class);
                in.putExtra("id",c.getId());
                 in.putExtra("icon",c.getIcon());
                   in.putExtra("name",c.getName());
                 startActivity(in);

            }
        }));


    }

    public static class RecyclerCustomAdapter extends
            RecyclerView.Adapter<RecyclerCustomAdapter.ViewHolder> {

        Context mContext;
        ArrayList<ShopClass> mArrayList;
    public RecyclerCustomAdapter(Context context, ArrayList<ShopClass> marrayList){
        mContext = context;
        mArrayList = marrayList;
    }

    //easy access to context items objects in recyclerView
    private Context getContext() {
        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.shopscard, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        // Get the data model based on position
        ShopClass cat = mArrayList.get(position);

        // Set item views based on your views and data model
        TextView head_tv = viewHolder.title;
        SimpleDraweeView img = viewHolder.cat_img;
        head_tv.setText(cat.getName());
      //  TextView desc = viewHolder.desc;
       // desc.setText(cat.getDesc());

        Uri imageUri = Uri.parse("http://dindayalcitymall.in/upload/"+cat.getIcon());
        Drawable myIcon = getContext().getResources().getDrawable( R.drawable.user_wh );



        GenericDraweeHierarchyBuilder builder =
                new GenericDraweeHierarchyBuilder(getContext().getResources());
        GenericDraweeHierarchy hierarchy = builder
                .setFadeDuration(300)
                .setPlaceholderImage(myIcon)
                .setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP)
                .build();
        img.setHierarchy(hierarchy);



        img.setImageURI(imageUri);
       // Log.e("Fresco", "ImagePath uri " + imageUri);

    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView title;
        SimpleDraweeView cat_img;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);


            title = (TextView) itemView.findViewById(R.id.cat_text);

            cat_img = (SimpleDraweeView) itemView.findViewById(R.id.cat_img);

        }
    }
}


    public class select_Class_Loader extends AsyncTask<Void,Void,String> {

        Context context;
        select_Class_Loader(Context context)
        {
            this.context=context;
        }


        @Override
        protected String doInBackground(Void... params) {

            String login_url= "https://corpzpro-incredible100rav.c9users.io/DDMALL/getshops.php";

            try{

                URL url =new URL(login_url);
                HttpURLConnection httpurlconnection= (HttpURLConnection) url.openConnection();
                //httpurlconnection.setRequestMethod("POST");
                // httpurlconnection.setDoOutput(true);
                httpurlconnection.setDoInput(true);

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
            loader.setVisibility(View.INVISIBLE);
             // Toast.makeText(context, ""+result, Toast.LENGTH_SHORT).show();
           // pb.setVisibility(View.INVISIBLE);
            if(!result.equalsIgnoreCase("")){

                try {
                    category_json =new JSONArray(result);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for(int i=0;i<category_json.length();i++)
                {
                    JSONObject obj= category_json.optJSONObject(i);

                    try {
                        shop_arrayList.add(new ShopClass(obj.getString("name"),obj.getString("icon"),obj.getString("id")));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
            else {
                Toast.makeText(context, "Server is down :-(", Toast.LENGTH_SHORT).show();
            }



            //Toast.makeText(context, " result : -"+result, Toast.LENGTH_SHORT).show();
        }


    }


}
