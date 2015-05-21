package dk.magesoe.qrtube;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class ScanMenuActivity extends Activity {

    private ImageView scanImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanmenu);

        final Button scanbutton = (Button) findViewById(R.id.button1);
        scanbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScanMenuActivity.this.scanQR(view);
            }
        });


        scanImg = (ImageView) findViewById(R.id.scanImg);

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
                    Intent videoStart = new Intent(this,VideoActivity.class);
                    videoStart.putExtra("VIDEONAME", contents.substring(6));
                    startActivity(videoStart);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Unkown QR code",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}
