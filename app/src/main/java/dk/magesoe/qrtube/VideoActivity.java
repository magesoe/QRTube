package dk.magesoe.qrtube;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

/**
 * Created by FMM on 21-05-2015.
 */
public class VideoActivity extends Activity {

    private String videoName;
    private int position;
    private VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

        position = 0;

        videoName = getIntent().getStringExtra("VIDEONAME");

        if (savedInstanceState != null){
            videoName = savedInstanceState.getString("videoName");
            position = savedInstanceState.getInt("pos");
        }

        video = (VideoView)findViewById(R.id.videoView);
        video.setZOrderOnTop(true);

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
