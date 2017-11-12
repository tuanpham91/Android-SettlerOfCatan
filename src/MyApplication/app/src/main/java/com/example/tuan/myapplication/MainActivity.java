package com.example.tuan.myapplication;

        import android.content.Intent;
        import android.media.MediaPlayer;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.Window;
        import android.view.WindowManager;
        import android.widget.LinearLayout;

        import com.example.tuan.myapplication.Server.SendJSON;

        import org.json.JSONException;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_launch);



        LinearLayout launchLayout = (LinearLayout)findViewById(R.id.launch_layout);
        launchLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent startGame = new Intent(MainActivity.this, StartActivity.class);
                startActivity(startGame);
                return false;
            }
        });

        try {
            SendJSON.sendHalloServer();
        } catch (JSONException e) {
            //Show Message Here
        }
    }
}
