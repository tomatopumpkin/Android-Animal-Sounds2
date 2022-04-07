package com.wimonsiri.animalsounds2;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import java.io.IOException;
public class MainActivity extends AppCompatActivity implements
        MediaPlayer.OnPreparedListener,
        MediaController.MediaPlayerControl, View.OnClickListener {
    private MediaPlayer mPlayer;
    private MediaController mControl;
    private String filename;
    private Button btnPlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);
        mPlayer = new MediaPlayer();
        mPlayer.setOnPreparedListener(this);
        mControl = new MediaController(this);
    }
    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mControl.setMediaPlayer(this);
        mControl.setAnchorView(findViewById(R.id.controller));
        mControl.setEnabled(true);
        mControl.show();
    }
    @Override
    public void onClick(View view) {
        filename = "android.resource://"+this.getPackageName()+"/raw/music01";
        try {
            mPlayer.setDataSource(this, Uri.parse(filename));
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Toast.makeText(this, "Could not open file "+ filename +
                    " for play back." + e, Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mControl.show();
        return super.onTouchEvent(event);
    }
    @Override
    public void start() {
        mPlayer.start();
    }
    @Override
    public void pause() {
        mPlayer.pause();
    }
    @Override
    public int getDuration() {
        return mPlayer.getDuration();
    }
    @Override
    public int getCurrentPosition() {
        return mPlayer.getCurrentPosition();
    }
    @Override
    public void seekTo(int i) {
        mPlayer.seekTo( i );
    }
    @Override
    public boolean isPlaying() {
        return mPlayer.isPlaying();
    }
    @Override
    public int getBufferPercentage() {
        int percentage = (mPlayer.getCurrentPosition()*100)/mPlayer.getDuration();
        return percentage;
    }
    @Override
    public boolean canPause() {
        return true;
    }
    @Override
    public boolean canSeekBackward() {
        return true;
    }
    @Override
    public boolean canSeekForward() {
        return true;
    }
    @Override
    public int getAudioSessionId() {
        return 0;
    }
}