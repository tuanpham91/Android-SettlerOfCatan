package com.example.tuan.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.tuan.myapplication.Server.SendJSON;
import com.example.tuan.myapplication.model.Cost;
import com.example.tuan.myapplication.model.Game;
import com.example.tuan.myapplication.model.RoadLengthCalculator;
import com.example.tuan.myapplication.model.TradeOffer;

import org.json.JSONException;

/**
 * Created by Tuan on 11.06.2017.
 */
public class BuildActivity extends AppCompatActivity {
    public TextView infoTextViewBuildAct;
    public CustomPlayView playView;

    public BuildState state;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("Build")) {
                String type = intent.getStringExtra("Type");
                Intent intent2 = new Intent (getApplicationContext(), BuildActivity.class);
                intent2.putExtra("Typ", type);
                startActivity(intent2);
            }
            if (intent.getAction().equals("Progress")) {
                //TODO
            }
            if (intent.getAction().equals("Offer come")){
                int offer = intent.getIntExtra("Player", 0);
                int offCostLehm = intent.getIntExtra("Brick",0);
                int offCostHolz = intent.getIntExtra("Lumber",0);
                int offCostGetreide = intent.getIntExtra("Grain",0);
                int offCostErz  = intent.getIntExtra("Ore",0);
                int offCostWolle = intent.getIntExtra("Wool",0);
                Intent intent2 = new Intent(getApplicationContext(), TradeActivity.class);
                intent2.putExtra("Brick", offCostLehm);
                intent2.putExtra("Lumber", offCostHolz);
                intent2.putExtra("Grain", offCostGetreide);
                intent2.putExtra("Ore", offCostErz);
                intent2.putExtra("Wool", offCostWolle);
                startActivity(intent2);

            }
            if (intent.getAction().equals("Offertaker come")){
                Intent intent1 = new Intent(getApplicationContext(), OfferActivity.class);
                int takerId = intent.getIntExtra("Another Player",0);
                int tradeId = intent.getIntExtra("Trade" , 0);
                intent1.putExtra("Another Player", takerId);
                intent1.putExtra("Trade", tradeId);
                startActivity(intent);
            }
            if (intent.getAction().equals("message")) {
                Intent intent2 = new Intent(getApplicationContext(), MessageActivity.class);
                String mes = intent.getStringExtra("message");
            }
            if (intent.getAction().equals("Release Card")) {
                Intent intent2 = new Intent(getApplicationContext(), ReleaseCardActivity.class);
                startActivity(intent2);
            }


        }
    };
    @Override
    public void onCreate (Bundle savedBundle) {

        super.onCreate(savedBundle);
        Intent intent = getIntent();
        setContentView(R.layout.build_activity);

        state = null;
        FrameLayout frame = (FrameLayout) findViewById(R.id.build_act_frame_layout_1);
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;

        playView = new CustomPlayView(getApplicationContext());
        playView.setScaleFactor(0);

        frame.addView(playView);
        playView.setSystemUiVisibility(uiOptions);

        infoTextViewBuildAct = (TextView) findViewById(R.id.textView4);
        infoTextViewBuildAct.setText("Please choose the type of Buldings you want!");

        ImageView buildStreetButton = (ImageView) findViewById(R.id.build_street_button);

        ImageView buildCityButton = (ImageView) findViewById(R.id.build_city_button);

        ImageView buildSettlementButton = (ImageView) findViewById(R.id.build_settlement_button);

        Button confirmButton = (Button) findViewById(R.id.build_button_buildact);

        ImageView exit_buildactivity  = (ImageView) findViewById(R.id.exit_buildactivity);

        exit_buildactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playView.resetSelected();
                finish();
            }
        });

        buildSettlementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = BuildState.BUILD_HOUSE;
                playView.resetSelected();
            }
        });

        buildStreetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = BuildState.BUILD_STREET;
                playView.resetSelected();
            }
        });

        buildCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = BuildState.UPGRADE_HOUSE;
                playView.resetSelected();
            }
        });

        confirmButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (state == BuildState.BUILD_STREET) {


                    if (playView.lastTile == null || playView.beforeLastTile == null) {
                        infoTextViewBuildAct.setText("Not enough Tile Selected");
                    } else {
                        Cost c = Cost.getCostOfStreet();
                        if (Game.getPlayer().checkResource(c)){
                            if (playView.buildRoadOnSelectedTiles()) {
                                try {
                                    SendJSON.sendBauen(Game.getPlayer().id, "Straße", playView.lastTile.xPos, playView.lastTile.yPos, playView.beforeLastTile.xPos, playView.beforeLastTile.yPos, 0 ,0 );
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                int max = RoadLengthCalculator.getMaxRoadLength(Game.map.roads);
                                infoTextViewBuildAct.setText("Build successful! Longest road is" + String.valueOf(max));
                            } else {
                                 infoTextViewBuildAct.setText("Build not successful!");
                            }
                        }
                    }
                }
                if (state == BuildState.BUILD_HOUSE) {


                    if (playView.lastTile == null || playView.beforeLastTile == null || playView.beforeTwoTile == null) {
                        infoTextViewBuildAct.setText("Not enough Tile Selected");
                    } else {
                        Cost c  = Cost.getCostOfHouse();
                        if (Game.getPlayer().checkResource(c)) {
                            if (playView.buildHouseOnSelectedTiles()) {
                                try {
                                    SendJSON.sendBauen(Game.getPlayer().id, "Dorf", playView.lastTile.xPos, playView.lastTile.yPos, playView.beforeLastTile.xPos, playView.beforeLastTile.yPos, playView.beforeTwoTile.xPos, playView.beforeTwoTile.yPos);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Game.getPlayer().useResource(c);
                                infoTextViewBuildAct.setText("Build successful!");
                            } else {
                                infoTextViewBuildAct.setText("Build not successful!");
                            }
                        }
                        else {
                            infoTextViewBuildAct.setText("You dont have enough resources, Sorry !");

                        }
                    }

                }
                if (state == BuildState.UPGRADE_HOUSE) {

                    if (playView.selectedHouse == null) {
                        infoTextViewBuildAct.setText("No house selected!");
                    } else {
                        Cost c = Cost.getCostOfUpgrade() ;
                        if (Game.getPlayer().checkResource(c)) {
                            boolean done = playView.selectedHouse.levelUp();
                            try {
                                SendJSON.sendBauen(Game.getPlayer().id, "Stadt", playView.lastTile.xPos, playView.lastTile.yPos, playView.beforeLastTile.xPos, playView.beforeLastTile.yPos, playView.beforeTwoTile.xPos, playView.beforeTwoTile.yPos);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            playView.upgradeSelectedHouse();
                            if (!done) {
                                infoTextViewBuildAct.setText("Settlement can not be upgraded!");
                            } else {
                                infoTextViewBuildAct.setText("Settlement upgraded!");
                            }
                        } else {
                            infoTextViewBuildAct.setText("You dont have enough Resources");
                        }
                    }

                }


            }
        });
        playView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                handleTouchEvent(event.getX(),event.getY());
                return false;
            }
        });

    }

    public void handleTouchEvent(double x, double y) {
        if (state == BuildState.BUILD_STREET) {
            int a = playView.get2TileSelected(x,y);
            if (a >= 0) {
                infoTextViewBuildAct.setText("Tile "+Integer.toString(a+1) + " selected! Please Select another Tile");
            } else {
                infoTextViewBuildAct.setText("Its not a tile");
            }
        }
        if (state == BuildState.BUILD_HOUSE) {
            int a = playView.get3TileSelected(x,y);
            if (a >= 0) {
                infoTextViewBuildAct.setText("Tile "+Integer.toString(a+1) + " selected! Please Select another Tile");
            } else {
                infoTextViewBuildAct.setText("Its not a tile");
            }
        }
        if (state == BuildState.UPGRADE_HOUSE)  {
            boolean found = playView.setHouseSelected(x,y);
            if (found) {
                infoTextViewBuildAct.setText("Settlement selected");
            }
        }
    }

    @Override
    public void onBackPressed() {
        playView.resetSelected();
        super.onBackPressed();


    }

    public enum BuildState {
        BUILD_STREET, BUILD_HOUSE, UPGRADE_HOUSE;
    }
}
