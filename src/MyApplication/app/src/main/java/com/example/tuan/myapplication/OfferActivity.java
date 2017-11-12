package com.example.tuan.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tuan.myapplication.Server.SendJSON;
import com.example.tuan.myapplication.model.Game;

import org.json.JSONException;

/**
 * Created by phamt on 24.07.2017.
 */

public class OfferActivity extends AppCompatActivity {

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("new Taker")) {
                int pId = intent.getIntExtra("Player",0);
                updatePlayer(pId);
            }

        }
    };

    public int count;
    public boolean selected;
    int player1;
    int player2;
    int player3;
    public TextView n1;
    public TextView n2;
    public TextView n3;
    public TextView n4;
    public TextView n5;
    int tradeId;
    public ImageView i1;
    public ImageView i2;
    public ImageView i3;
    public ImageView i4;
    public ImageView i5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        player1 = intent.getIntExtra("Player",0);
        tradeId = intent.getIntExtra("Trade",0);
        selected = false;
        count = 1;
        setContentView(R.layout.release_card_activity);
        n1  = (TextView) findViewById(R.id.textView11);
        n2  = (TextView) findViewById(R.id.textView11);
        n3  = (TextView) findViewById(R.id.textView11);
        n4  = (TextView) findViewById(R.id.textView11);
        n5  = (TextView) findViewById(R.id.textView11);

        n1.setText(Game.getPlayerById(player1).getName());

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!selected)  {
                    try {
                        SendJSON.sendHandelAbschliessen(player1,tradeId);
                        selected = true;
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Intent inOut = new Intent(getApplicationContext(),MessageActivity.class);
                    inOut.putExtra("message","You chose another player!");


                }

            }
        });

        i1 = (ImageView) findViewById(R.id.offer_confirm_button1);

        i2 = (ImageView) findViewById(R.id.offer_confirm_button2);
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!selected)  {
                    try {
                        SendJSON.sendHandelAbschliessen(player2,tradeId);
                        selected = true;
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Intent inOut = new Intent(getApplicationContext(),MessageActivity.class);
                    inOut.putExtra("message","You chose another player!");


                }

            }
        });
        i3 = (ImageView) findViewById(R.id.offer_confirm_button3);
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!selected)  {
                    try {
                        SendJSON.sendHandelAbschliessen(player3,tradeId);
                        selected = true;
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Intent inOut = new Intent(getApplicationContext(),MessageActivity.class);
                    inOut.putExtra("message","You chose another player!");

                }

            }
        });



    }
    public void updatePlayer(int a) {
        if (count == 1) {
            count ++;
            player2 = a;
            n2.setText(Game.getPlayerById(a).getName());
        }
        if (count == 2) {
            count ++;
            player3 = a;
            n3.setText(Game.getPlayerById(a).getName());
        }

    }
}
