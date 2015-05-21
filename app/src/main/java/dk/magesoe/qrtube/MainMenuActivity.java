package dk.magesoe.qrtube;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by FMM on 21-05-2015.
 */
public class MainMenuActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);

        final Button scanButton = (Button) findViewById(R.id.scananimal);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent scanIntent = new Intent(MainMenuActivity.this, ScanMenuActivity.class);
                startActivity(scanIntent);
                finish();
            }
        });

        final Button indexButton = (Button) findViewById(R.id.animalindex);
        indexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent indexIntent = new Intent(MainMenuActivity.this, AnimalIndex.class);
                startActivity(indexIntent);
                finish();
            }
        });


    }
}
