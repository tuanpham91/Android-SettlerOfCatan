package com.example.tuan.myapplication;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.tuan.myapplication.Server.SendJSON;

//import com.example.tuan.myapplication.Server.ServerClientInterface;

public class StartActivity extends AppCompatActivity {
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("Hallo")) {

            }
            if (intent.getAction().equals("Wrong Version")) {
                openMessage();
            }

            if (intent.getAction().equals("Hello")) {

            }
        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.start_game_activity);

        ImageButton startNewGame = (ImageButton) findViewById(R.id.start_new_game_view);

        //TODO : Check if player has an ID already
        startNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startGame = new Intent(StartActivity.this, LobbyActivity.class);
                startActivity(startGame);

            }
        });


        ImageButton gameRules = (ImageButton) findViewById(R.id.game_rules_view);
        gameRules.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        ImageButton options = (ImageButton) findViewById(R.id.options_view);
        options.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        ImageView bookInfo = (ImageView) findViewById(R.id.book_view);
        bookInfo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        ImageView progressInfo = (ImageView)findViewById(R.id.progress_view);
        progressInfo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        ImageView settings = (ImageView)findViewById(R.id.setting_tool_view);
        settings.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        ImageView help = (ImageView)findViewById(R.id.help_view);
        help.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        ImageView exit = (ImageView)findViewById(R.id.exit_icon);
        exit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });



    }

    public void openMessage () {

    }

}
