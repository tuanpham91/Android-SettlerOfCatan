package com.example.tuan.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.tuan.myapplication.Server.SendJSON;
import com.example.tuan.myapplication.model.Game;

import org.json.JSONException;

public class LobbyActivity extends AppCompatActivity {
    String playerColor;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("New Player")) {
                String color = intent.getStringExtra("Color");
                String name =  intent.getStringExtra("Name");
                int id = intent.getIntExtra("Player",0);
                if (id == Game.getPlayer().id) {
                    confirmed = true;
                }
            }
            if (intent.getAction().equals("Spiel gestarted")) {
                startGame();
            }
        }
    };

    public boolean confirmed;
    public boolean ready;
    ArrayAdapter<CharSequence> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View layout = getLayoutInflater().inflate(R.layout.lobby_activity, null);
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        layout.setSystemUiVisibility(uiOptions);
        setContentView(layout);
        confirmed = false;
        ready = false;
        final Spinner spinnerFrom = (Spinner) findViewById(R.id.spinner_lobby);
         adapter = ArrayAdapter.createFromResource(this,
                R.array.color_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        final ImageView colorPlayer = (ImageView) findViewById(R.id.lobby_player_color);

        final EditText nameText = (EditText) findViewById(R.id.name_input_text);
        final Button confirmButton = (Button) findViewById(R.id.lobby_confirm_button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameText.getText().equals("")) {
                    Intent messageIntent = new Intent(LobbyActivity.this, MessageActivity.class);
                    messageIntent.putExtra("message", "Please Choose a Name!");
                    return;
                } else if (!spinnerFrom.isSelected()) {
                    Intent messageIntent = new Intent(LobbyActivity.this, MessageActivity.class);
                    messageIntent.putExtra("message", "Color not selected !");
                    return;
                } else {
                    try {
                        SendJSON.sendSpieler(Game.getPlayer().id);
                    } catch (JSONException e) {
                        Intent messageIntent = new Intent(LobbyActivity.this, MessageActivity.class);
                        messageIntent.putExtra("message", "Something wrong with Connection, please try again");
                    }
                }


            }
        });

        //ReadyButton
        final Button readyButton = (Button) findViewById(R.id.lobby_ready_button);

        readyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirmed) {
                    try {
                        ready = true;
                        SendJSON.sendSpielStarten(Game.getPlayer().id);
                    } catch (JSONException e) {
                        Intent messageIntent = new Intent(LobbyActivity.this, MessageActivity.class);
                        messageIntent.putExtra("message", "Something wrong with Connection, please try again");
                    }
                }
                else {
                    Intent messageIntent = new Intent(LobbyActivity.this, MessageActivity.class);
                    messageIntent.putExtra("message", "You have to click ready first !");
                }
            }
        });
        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        colorPlayer.setBackgroundColor(Color.parseColor(String.valueOf(R.color.rot)));
                        playerColor = "rot";
                        break;
                    case 1:
                        colorPlayer.setBackgroundColor(Color.parseColor(String.valueOf(R.color.orange)));
                        playerColor = "orange";
                        break;
                    case 2:
                        colorPlayer.setBackgroundColor(Color.parseColor(String.valueOf(R.color.braun)));
                        playerColor = "braun";
                        break;
                    case 3:
                        colorPlayer.setBackgroundColor(Color.parseColor(String.valueOf(R.color.weiss)));
                        playerColor = "weiss";
                        break;
                    case 4:
                        colorPlayer.setBackgroundColor(Color.parseColor(String.valueOf(R.color.grün)));
                        playerColor = "grün";
                        break;
                    case 5:
                        colorPlayer.setBackgroundColor(Color.parseColor(String.valueOf(R.color.blau)));
                        playerColor = "blau";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
    }
    public void removeColorFromSpinner(String c) {

    }
    // TODO : On Notify Game started {
    public void startGame () {
        Intent startIntent = new Intent(getApplicationContext(), PlayActivity.class);
        startActivity(startIntent);
    }
    public static void updateLobby(){

    }


}
