package exodiasolutions.ddcombine.DindayalCityMallApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class AboutUs extends AppCompatActivity {

    RelativeLayout lol;
    int height,width;
    Button p1_button;
    View bar1,bar2,bar3,bar4,bar5;

    TextView info_text,shopping,our_story,fun,why,heading;
    boolean open =false;
    int width_total;
    boolean first=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ActionBar ab = getSupportActionBar();
        ab.hide();

        info_text = (TextView) findViewById(R.id.info_text);
        shopping = (TextView) findViewById(R.id.shopping);
        our_story = (TextView) findViewById(R.id.our_story);
        fun = (TextView) findViewById(R.id.fun);
        why = (TextView) findViewById(R.id.why);
        heading = (TextView) findViewById(R.id.heading);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Oxygen-Bold.ttf");
        Typeface type2 = Typeface.createFromAsset(getAssets(),"fonts/JosefinSans-Bold.ttf");
        Typeface type3 = Typeface.createFromAsset(getAssets(),"fonts/Anton-Regular.ttf");

        info_text.setTypeface(type);
        shopping.setTypeface(type3);
        our_story.setTypeface(type2);
        fun.setTypeface(type3);
        why.setTypeface(type2);
        heading.setTypeface(type3);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Double d = new Double((width_total-130)*.8);
                Double d2 = new Double((width_total-130)*.98);
                Double d3 = new Double((width_total-130));
                Double d4= new Double((width_total-130)*.84);

                ResizeWidthAnimation anim = new ResizeWidthAnimation(bar1, d.intValue());
                anim.setDuration(1000);
                bar1.startAnimation(anim);

                ResizeWidthAnimation anim2 = new ResizeWidthAnimation(bar2, d2.intValue());
                anim2.setDuration(1000);
                bar2.startAnimation(anim2);

                ResizeWidthAnimation anim3 = new ResizeWidthAnimation(bar3, d.intValue());
                anim3.setDuration(1000);
                bar3.startAnimation(anim3);

                ResizeWidthAnimation anim4 = new ResizeWidthAnimation(bar4, d3.intValue());
                anim4.setDuration(1000);
                bar4.startAnimation(anim4);

                ResizeWidthAnimation anim5 = new ResizeWidthAnimation(bar5, d4.intValue());
                anim5.setDuration(1000);
                bar5.startAnimation(anim5);





                //Do something after 100ms
            }
        }, 400);



    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus) {
        // the height will be set at this point

        if(first==false) {
            first=true;
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

            width_total = displayMetrics.widthPixels;

            lol = (RelativeLayout) findViewById(R.id.info);

            height = lol.getMeasuredHeight();

            bar1 = findViewById(R.id.bar1);
            bar2 = findViewById(R.id.bar2);
            bar3 = findViewById(R.id.bar3);
            bar4 = findViewById(R.id.bar4);
            bar5 = findViewById(R.id.bar5);


            width = bar1.getMeasuredWidth();
            //  Toast.makeText(MainActivity.this, ""+height, Toast.LENGTH_SHORT).show();
            ViewGroup.LayoutParams params = lol.getLayoutParams();
            params.height = 500;
            lol.setLayoutParams(params);
        }
    }
    public void reveal_info(View v) {

        p1_button = (Button)findViewById(R.id.read);


        lol = (RelativeLayout) findViewById(R.id.info);
        View grad = findViewById(R.id.grad);


        //lol.setLayoutParams(params);
        ResizeAnimation a = new ResizeAnimation(lol);
        a.setDuration(1000);

        if(open==false) {
            a.setParams(lol.getHeight(), height);
            lol.startAnimation(a);
            grad.animate().alpha(0.0f).setDuration(2000);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    p1_button.setText("Read Less");
                    //Do something after 100ms
                }
            }, 1000);


            open = true;
        }
        else{
            p1_button.setText("Read More");
            a.setParams(lol.getHeight(), 500);
            lol.startAnimation(a);
            grad.animate().alpha(1.0f).setDuration(1000);
            open=false;
        }

    }

}
