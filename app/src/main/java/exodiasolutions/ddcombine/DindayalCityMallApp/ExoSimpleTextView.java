package exodiasolutions.ddcombine.DindayalCityMallApp;

/**
 * Created by ram on 10/08/2017.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class ExoSimpleTextView extends TextView {
    final String fontName = "fonts/Poppins-Medium.ttf";

    public ExoSimpleTextView(Context context) {
        super(context);
        Typeface face=Typeface.createFromAsset(context.getAssets(), fontName);
        this.setTypeface(face);
    }

    public ExoSimpleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face=Typeface.createFromAsset(context.getAssets(), fontName);
        this.setTypeface(face);
    }

    public ExoSimpleTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face=Typeface.createFromAsset(context.getAssets(), fontName);
        this.setTypeface(face);
    }

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);

    }

}