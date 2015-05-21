package dk.magesoe.qrtube;

import android.app.Activity;
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

        name = (TextView) findViewById(R.id.txt_animal_name);
        name.setText("Pingvin");
        desc = (TextView) findViewById(R.id.txt_animal_desc);
        desc.setText("Pingvinen er et elegant dyr, som ikke kan flyve...");


        height = (TextView) findViewById(R.id.txt_animal_height);
        height.setText("65 cm");
        habitat = (TextView) findViewById(R.id.txt_animal_habitat);
        habitat.setText("Sydpolen/Arktis");
        weight = (TextView) findViewById(R.id.txt_animal_weight);
        weight.setText("10 kg");
        eats = (TextView) findViewById(R.id.txt_animal_eats);
        eats.setText("Alt det kan komme i nærheden af");


        position = 0;

        videoName = getIntent().getStringExtra("VIDEONAME");

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
