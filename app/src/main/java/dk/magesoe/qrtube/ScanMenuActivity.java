package dk.magesoe.qrtube;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
        try {
            //start the scanning activity
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException e) {
            // on catch make user download the app
            showDialog(ScanMenuActivity.this, "No scanner found", "Download a scanner?", "Yes", "No").show();

        }

    }

    private static AlertDialog showDialog(final Activity activity, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(activity);
        downloadDialog.setTitle(title);
        downloadDialog.setMessage(message);
        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    activity.startActivity(intent);
                } catch (ActivityNotFoundException e) {

                }
            }
        });

        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

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
                    AnimalDatabase animalDatabase = new AnimalDatabase(getApplicationContext());
                    Animal scannedAnimal = null;

                    for (Animal animal : animalDatabase.getAnimalList()) {
                        if (animal.getVideoname().equalsIgnoreCase(contents.substring(6))) {
                            scannedAnimal = animal;
                        }
                        Log.v("bla1",animal.getName());
                        Log.v("bla2",contents.substring(6).toLowerCase());

                    }


                    if (scannedAnimal != null) {
                        Intent videoStart = new Intent(this, VideoActivity.class);
                        videoStart.putExtra("VIDEONAME", scannedAnimal.getVideoname());
                        videoStart.putExtra("NAME", scannedAnimal.getName());
                        videoStart.putExtra("DESC", scannedAnimal.getDesc());
                        videoStart.putExtra("HABITAT", scannedAnimal.getHabitat());
                        videoStart.putExtra("WEIGHT", scannedAnimal.getWeight());
                        videoStart.putExtra("EATS", scannedAnimal.getEats());
                        videoStart.putExtra("CAMEFROM", "scan");
                        startActivity(videoStart);
                        finish();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Unkown QR code",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent mainMenu = new Intent(this, MainMenuActivity.class);
        startActivity(mainMenu);
        finish();
    }

}
