package Thrax.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent playVideo = new Intent(MainActivity.this , YoutubeActivity.class);


        Button videoButton = (Button) findViewById(R.id.video_btn);
        Button listbtn = (Button) findViewById(R.id.list_btn);

        View.OnClickListener videoPlay = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playVideo.putExtra("video" , "video");
                startActivity(playVideo);

            }
        };


        View.OnClickListener playListPlay = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playVideo.putExtra("video" , "list");
                startActivity(playVideo);
            }
        };



        videoButton.setOnClickListener(videoPlay);
        listbtn.setOnClickListener(playListPlay);



    }



}