package com.example.tuan.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tuan.myapplication.Server.SendJSON;
import com.example.tuan.myapplication.model.Cost;
import com.example.tuan.myapplication.model.Game;

import org.w3c.dom.Text;


/**
 * Created by Tuan on 15.06.2017.
 */

public class TradeActivity extends AppCompatActivity {
    public int brick;
    public int grain;
    public int ore;
    public int wool;
    public int wood;

    public int brick2;
    public int grain2;
    public int ore2;
    public int wool2;
    public int wood2;

    ImageView left_side_res1 = (ImageView) findViewById(R.id.left_side_res1);
    ImageView left_side_res2 = (ImageView) findViewById(R.id.left_side_res2);
    ImageView left_side_res3 = (ImageView) findViewById(R.id.left_side_res3);
    ImageView left_side_res4 = (ImageView) findViewById(R.id.left_side_res4);
    ImageView left_side_res5 = (ImageView) findViewById(R.id.left_side_res5);



    ImageView right_side_res1 = (ImageView) findViewById(R.id.right_side_res1);
    ImageView right_side_res2 = (ImageView) findViewById(R.id.right_side_res2);
    ImageView right_side_res3 = (ImageView) findViewById(R.id.right_side_res3);
    ImageView right_side_res4 = (ImageView) findViewById(R.id.right_side_res4);
    ImageView right_side_res5 = (ImageView) findViewById(R.id.right_side_res5);

    TextView l1 = (TextView) findViewById(R.id.text_left_1);
    TextView l2 = (TextView) findViewById(R.id.text_left_2);
    TextView l3 = (TextView) findViewById(R.id.text_left_3);
    TextView l4 = (TextView) findViewById(R.id.text_left_4);
    TextView l5 = (TextView) findViewById(R.id.text_left_5);

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("angebotAngenommen")) {
                finish();
            }
            if (intent.getAction().equals("Progress")) {

            }
        }
    };
    @Override
    public void onCreate(Bundle savedBundle) {
        super.onCreate(savedBundle);
        Intent intent = getIntent();

        int trader1Id = intent.getIntExtra("trader1",0);
        int trader2Id = intent.getIntExtra("trader2", 0);

        //false meaning just accepting this trade
        boolean trader = intent.getBooleanExtra("trader", false);
        brick = 0;
        grain = 0;
        ore = 0;
        wool = 0;
        wood = 0;

        brick2 = 0;
        grain2= 0;
        ore2= 0;
        wool2 = 0;
        wood2 = 0;

        View layout = getLayoutInflater().inflate(R.layout.trade_activity,null);
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;

        layout.setSystemUiVisibility(uiOptions);
        setContentView(layout);
        TextView trader1TextView = (TextView) findViewById(R.id.trader_1_name_text);
        TextView trader2TextView = (TextView) findViewById(R.id.trader_2_name_text);
        String traderName1 = Game.getPlayerNameById(trader1Id);
        String traderName2 = Game.getPlayerNameById(trader2Id);
        trader1TextView.setText(traderName1);
        trader2TextView.setText(traderName2);



        left_side_res1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                brick ++;
                l1.setText(brick);
            }
        });

        left_side_res2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                grain ++;
                l2.setText(grain);
            }
        });
        left_side_res3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ore ++;
                l3.setText(ore);
            }
        });
        left_side_res4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                wool ++;
                l4.setText(wool);
            }
        });
        left_side_res5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                wood ++;
                l5.setText(wood);
            }
        });
        ImageView cancel_trade_button = (ImageView) findViewById(R.id.cancel_trade_button);
        cancel_trade_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView requestButton = (ImageView) findViewById(R.id.request_trade_button);
        ImageView confirmButton = (ImageView) findViewById(R.id.confirm_trade_button);
        if (trader) {
            requestButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Cost offer = new Cost(wood,brick,wool,grain,ore);
                    Cost req = new Cost(wood2,brick2,wool2,grain2,ore2);

                    //TODO : Send trade Request
                    SendJSON.sendHandelsangebot(Game.getPlayer().id , offer, req);
                }
            });
        } else {
            confirmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SendJSON.sendHandelAnnehmen(Game.getPlayer().id);
                }

            });

        }
    }
}
