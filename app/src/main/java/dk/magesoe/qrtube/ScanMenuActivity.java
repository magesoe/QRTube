package dk.magesoe.qrtube;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;


public class ScanMenuActivity extends Activity {

    private String videoName;
    private int position;
    private VideoView video;
    private ImageView scanImg;
    private boolean isCompleted;
    private Intent currentIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                scanQR(v);
            }
        });

        position = 0;

        if (savedInstanceState != null){
            videoName = savedInstanceState.getString("videoName");
            position = savedInstanceState.getInt("pos");
            isCompleted = savedInstanceState.getBoolean("isCompleted");
        }

        video = (VideoView)findViewById(R.id.videoView);
        video.setZOrderOnTop(true);

        if (videoName != null && !videoName.isEmpty() && !isCompleted) {
            video.setZOrderOnTop(false);
            startVideo();
        }

        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                position = 0;
                isCompleted = true;
                video.setVisibility(View.GONE);
                video.setZOrderOnTop(true);
                scanImg.setVisibility(View.VISIBLE);
            }
        });


        scanImg = (ImageView) findViewById(R.id.scanImg);

    }

    public void startVideo() {
        scanImg.setVisibility(View.GONE);
        video.setVisibility(View.VISIBLE);
        video.setZOrderOnTop(false);
        Uri path = Uri.parse("android.resource://" + getPackageName() + "/raw/" + videoName);
        video.setVideoURI(path);
        video.seekTo(position);
        video.requestFocus();
        video.start();
    }

    public void scanQR(View v) {
        //start the scanning activity
        Intent intent = new Intent(this,ScannerActivity.class);
        startActivityForResult(intent, 0);
    }


    //on ActivityResult method
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                //get the extras that are returned from the intent
                String contents = intent.getStringExtra("SCAN_RESULT");
                if (contents.substring(0,6).equals("QRTube")) {
                    isCompleted = false;
                    videoName = contents.substring(6);
                    startVideo();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Unkown QR code",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    protected void onSaveInstanceState(Bundle output) {
        super.onSaveInstanceState(output);
        output.putString("videoName", videoName);
        output.putInt("pos", video.getCurrentPosition());
        output.putBoolean("isCompleted",isCompleted);
    }


}
