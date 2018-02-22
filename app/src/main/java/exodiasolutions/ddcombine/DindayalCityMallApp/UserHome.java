package exodiasolutions.ddcombine.DindayalCityMallApp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserHome extends AppCompatActivity {
    private SharedPreferences.Editor Editor;
    private SharedPreferences mSettings;
    String email,name;
    TextView email_tv,name_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        mSettings = this.getSharedPreferences("settings", 0);
        Editor = mSettings.edit();
        email = getIntent().getStringExtra("account");
        name = getIntent().getStringExtra("name");
        email_tv = (TextView) findViewById(R.id.email);
        name_tv= (TextView) findViewById(R.id.name);
        email_tv.setText(email);
        name_tv.setText(name);

    }

    public void logout(View v){
        Editor.putString("remember_me", "0");
        Editor.apply();
        Intent i = new Intent(UserHome.this,Login.class);
        startActivity(i);
        UserHome.this.finish();
    }
}
