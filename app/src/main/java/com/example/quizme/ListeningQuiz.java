package com.example.quizme;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ListeningQuiz extends AppCompatActivity{
    ImageView playbtn;
    SeekBar seekBar;
    MediaPlayer mediaPlayer;
    Runnable runnable;
    Handler handler;
    private CardView cardView;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listening_quiz_page);
        playbtn = findViewById(R.id.btnplay);
        seekBar = findViewById(R.id.seek_bar);

        playbtn.setOnClickListener(btnClickListen);

        mediaPlayer = new MediaPlayer();
        handler = new Handler();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    mediaPlayer.seekTo(i);
                    seekBar.setProgress(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private View.OnClickListener btnClickListen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            playbtn.setImageResource(R.drawable.ic_baseline_stop_circle_24);
            playSong();
        }
};

    public void playSong(){
        Uri uri = Uri.parse("https://adeptenglish.com/lessons/conversations-in-english-deal-with-stress/English%20Conversations%20For%20English%20Learners-Why%20Having%20A%20Big%20Brain%20Is%20Not%20Always%20An%20Advantage%20Ep%20530-b4e105.mp3");
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.reset();

        try{
            mediaPlayer.setDataSource(ListeningQuiz.this,uri);
        }catch (Exception e){e.printStackTrace();}


    mediaPlayer.prepareAsync();
    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            seekBar.setMax(mp.getDuration());
            mediaPlayer.start();
            updateSeekbar();
        }
    });

    mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {

            double ratio = i/100.0;
            int bufferingLevel = (int)(mediaPlayer.getDuration()*ratio);
            seekBar.setSecondaryProgress(bufferingLevel);
        }
    });
    }

    public void updateSeekbar(){
        int currPos = mediaPlayer.getCurrentPosition();
        seekBar.setProgress(currPos);

        runnable = new Runnable(){
        @Override
        public void run() {
            updateSeekbar();
        }
        };
            handler.postDelayed(runnable,1000);
    }


    }
