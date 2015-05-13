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


public class MainActivity extends Activity {

    private String videoName;
    private int position;
    private VideoView video;
    private ImageView scanImg;
    private boolean isCompleted;

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
        Uri path = Uri.parse("android.resource://" + getPackageName() + "/raw/" + videoName);
        video.setVideoURI(path);
        video.seekTo(position);
        video.requestFocus();
        video.start();
    }

    public void scanQR(View v) {
        try {
            //start the scanning activity from the com.google.zxing.client.android.SCAN intent
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException e) {
            //on catch, show the download dialog
            showDialog(MainActivity.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
    }

    //alert dialog for downloadDialog
    private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title);
        downloadDialog.setMessage(message);
        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    act.startActivity(intent);
                } catch (ActivityNotFoundException e) {

                }
            }
        });
        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return downloadDialog.show();
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
