package dk.magesoe.qrtube;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.VideoView;

/**
 * Created by FMM on 21-05-2015.
 */
public class VideoActivity extends Activity {

    private String videoName;
    private int position;
    private VideoView video;
    private TextView name;
    private TextView desc;
    private TextView height;
    private TextView habitat;
    private TextView weight;
    private TextView eats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videolayout);

        Intent intent = getIntent();

        name = (TextView) findViewById(R.id.txt_animal_name);
        name.setText(intent.getStringExtra("NAME"));
        desc = (TextView) findViewById(R.id.txt_animal_desc);
        desc.setText(intent.getStringExtra("DESC"));


        height = (TextView) findViewById(R.id.txt_animal_height);
        height.setText(intent.getStringExtra("HEIGHT"));
        habitat = (TextView) findViewById(R.id.txt_animal_habitat);
        habitat.setText(intent.getStringExtra("HABITAT"));
        weight = (TextView) findViewById(R.id.txt_animal_weight);
        weight.setText(intent.getStringExtra("WEIGHT"));
        eats = (TextView) findViewById(R.id.txt_animal_eats);
        eats.setText(intent.getStringExtra("EATS"));


        position = 0;

        videoName = intent.getStringExtra("VIDEONAME");

        if (savedInstanceState != null){
            videoName = savedInstanceState.getString("videoName");
            position = savedInstanceState.getInt("pos");
        }

        video = (VideoView)findViewById(R.id.videoView);

        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                position = 0;
            }
        });

        startVideo();

    }

    public void startVideo() {
        Uri path = Uri.parse("android.resource://" + getPackageName() + "/raw/" + videoName);
        video.setVideoURI(path);
        video.seekTo(position);
        video.requestFocus();
        video.start();
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    protected void onSaveInstanceState(Bundle output) {
        super.onSaveInstanceState(output);
        output.putString("videoName", videoName);
        output.putInt("pos", video.getCurrentPosition());
    }

}
