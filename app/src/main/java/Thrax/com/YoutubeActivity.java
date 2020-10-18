package Thrax.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener
{
    private static final String TAG = "YoutubeActivity";
    static final String GOOGLE_API_KEY = "AIzaSyBYY8MYDrFxAlIUoWG54-Bovj4SR5oTTOg";
    static final String YOUTUBE_VIDEO_ID = "ZnZsVLiVPLs";
    static final String YOUTUBE_PLAYLIST = "PLzcK7AwShFBmc0Pigajs_u5nhQIwARAmE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ConstraintLayout layout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_youtube, null);
        setContentView(layout);


        YouTubePlayerView playerView = new YouTubePlayerView(this);
        playerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT ));
        layout.addView(playerView);
        playerView.initialize(GOOGLE_API_KEY , this);

    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);

        Log.d(TAG, "onInitializationSuccess: Starts");
        Toast.makeText(this , "The video has been initialized successfully", Toast.LENGTH_LONG).show();

        if (!b) {
            Intent getType = getIntent();

            String type = getType.getStringExtra("video");

            if("video".equals(type)){

               youTubePlayer.loadVideo(YOUTUBE_VIDEO_ID);

            }

            else if("list".equals(type)){
                youTubePlayer.cuePlaylist(YOUTUBE_PLAYLIST);
            }

            else{
                youTubePlayer.cuePlaylist(YOUTUBE_PLAYLIST);
            }

        }
    }


    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {

        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {
            Toast.makeText(YoutubeActivity.this , "The video started..", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };



    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {

        }

        @Override
        public void onPaused() {
            Toast.makeText(YoutubeActivity.this , "Why did u fucking paused the video??", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };



    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        final int REQUEST_CODE = 1;

        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this , REQUEST_CODE).show();
            Toast.makeText(this , REQUEST_CODE , Toast.LENGTH_LONG).show();
        }

        else{

            String errorMessage = String.format("There was a error initializing Youtube player (%1$s)" , youTubeInitializationResult.toString());
            Toast.makeText(this , errorMessage , Toast.LENGTH_LONG).show();
        }

    }
}