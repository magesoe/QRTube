package dk.magesoe.qrtube;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by FMM on 20-05-2015.
 */
public class SplashActivity extends Activity {

    private final int SPLASH_LENGTH = 2000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent menuIntent = new Intent(SplashActivity.this,MainMenuActivity.class);
                SplashActivity.this.startActivity(menuIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_LENGTH);

    }

}
