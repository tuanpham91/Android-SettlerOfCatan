package com.example.tuan.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.tuan.myapplication.Server.SendJSON;
import com.example.tuan.myapplication.model.Card;
import com.example.tuan.myapplication.model.CommunicatorService;
import com.example.tuan.myapplication.model.Cost;
import com.example.tuan.myapplication.model.Game;
import com.example.tuan.myapplication.model.Player;

import org.json.JSONException;

import java.util.ArrayList;

//import com.example.tuan.myapplication.Model.Map;

/**
 * Created by Tuan on 23.05.2017.
 */

public class PlayActivity extends AppCompatActivity {
    public ImageButton settingButton;
    public ImageView buildButton;
    public Button exitButton;
    public ImageView exchangeButton;
    public ImageView tradeButton;
    public FrameLayout fragmentLayout;
    public FrameLayout backgroundLayout;
    public LayoutInflater inflater;
    public  TextView infoTextView;
    public static CustomPlayView playView;
    public ImageView rollDiceButton;
    public ImageView cardsButton;
    public TextView resText;
    public ImageView dice1;
    public ImageView dice2;
    public ArrayList<Integer> dicesBitmap = new ArrayList<>();
    public ImageView player1Icon;
    public ImageView player2Icon;
    public ImageView player3Icon;
    public ImageView player4Icon;
    public ImageView settingHelpIcon;
    public SeekBar seekBar;
    public TextView textSeekbarLeft;
    public TextView textSeekbarRight;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("updateRollDiceAction")) {
                int dice1 = intent.getIntExtra("dice1",0);
                int dice2 = intent.getIntExtra("dice2",0);
                updateRollDiceAction(dice1, dice2);
            }
            if (intent.getAction().equals("Progress")) {

            }
            if (intent.getAction().equals("PlayCard")) {

            }
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


    public void onCreate(Bundle savedBundle) {

        super.onCreate(savedBundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        dicesBitmap.add(R.drawable.dice_1);
        dicesBitmap.add(R.drawable.dice_2);
        dicesBitmap.add(R.drawable.dice_3);
        dicesBitmap.add(R.drawable.dice_4);
        dicesBitmap.add(R.drawable.dice_5);
        dicesBitmap.add(R.drawable.dice_6);
        //Map map = new Map(3,5);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.playactivity);


        backgroundLayout =  (FrameLayout) findViewById(R.id.frame);


        playView = new CustomPlayView(getApplicationContext());
        backgroundLayout.addView(playView);

        settingButton = (ImageButton) findViewById(R.id.imageButton);

        //Seekbar and its elements
        textSeekbarLeft = (TextView) findViewById(R.id.seekBarTextLeft);
        textSeekbarRight = (TextView) findViewById(R.id.seekBarTextRight);


        seekBar = (SeekBar) findViewById(R.id.scaleSeekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (progress>50) {
                    textSeekbarLeft.setText("");
                    textSeekbarRight.setText("+ "+String.valueOf((progress-50))+"%");
                } else if (progress <50) {

                    textSeekbarRight.setText("");
                    textSeekbarLeft.setText( String.valueOf((progress-50))+"%");
                }
                playView.setScaleFactor((progress-50));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        infoTextView = (TextView) findViewById(R.id.infoTextView);

        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        playView.setSystemUiVisibility(uiOptions);

        setupListeners();
    }

    public void rollDiceAction() {
        View layout = inflater.inflate(R.layout.roll_dice_fragment, null, false);
        final PopupWindow pw1 = new PopupWindow(layout);

        pw1.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        pw1.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pw1.setOutsideTouchable(true);

        Button rollDiceButton = (Button) layout.findViewById(R.id.roll_activate_button);

        resText = (TextView) layout.findViewById(R.id.roll_dice_text);
        //random the dice

        dice1 = (ImageView) layout.findViewById(R.id.image_dice_1);
        dice2 = (ImageView) layout.findViewById(R.id.image_dice_2);

        rollDiceButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SendJSON.sendWuerfeln(Game.getPlayer().id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        fragmentLayout = (FrameLayout) findViewById(R.id.fragment_layout1);
        pw1.showAtLocation(fragmentLayout, Gravity.CENTER,-pw1.getWidth()/2, -pw1.getHeight()/2);

    }

    public void updateRollDiceAction(int diceOne , int diceTwo) {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.roll);
        resText.setText("You have rolled " + Integer.toString(diceOne) +" and " + Integer.toString(diceTwo) +" !");

        mp.start();
        dice1.setImageResource(dicesBitmap.get(diceOne-1));
        dice2.setImageResource(dicesBitmap.get(diceTwo-1));

    }

    public void buyDevelopmentsCards() throws JSONException {
        Cost c  = Cost.getCostOfDevelopment();
        if (Game.getPlayer().checkResource(c)) {
            SendJSON.sendEntwicklungskarteKaufen(Game.getPlayer().id);
            Game.getPlayer().useResource(c);
        }
    }

    public  void playDevelopmentCard(Card c) {
        boolean removed =
    }

    public void buildAction() {
        Intent intent = new Intent(getApplicationContext(), BuildActivity.class);
        startActivity(intent);
    }

    public void tradeAction() {
        Intent intent = new Intent(getApplicationContext(), TradeActivity.class);
        //Who do want to Trade With ?

        intent.putExtra("requester", Game.getPlayer().id);
        intent.putExtra("trader", true);
        startActivity(intent);
    }

    public void exchangeAction() {
        Intent intent = new Intent(getApplicationContext(), ExchangeActivity.class);
        startActivity(intent);
    }
    public void setupListeners() {
        settingButton.setOnClickListener( new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoTextView.setText("Choose what you want to do!");
                View layout = inflater.inflate(R.layout.setting_fragment_scrollview, null, false);
                final PopupWindow pw = new PopupWindow(layout);
                pw.setOutsideTouchable(true);

                tradeButton = (ImageView) layout.findViewById(R.id.setting_book_icon) ;
                tradeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent  = new Intent(getApplicationContext(), TradeActivity.class);
                        startActivity(intent);
                    }
                });
                buildButton = (ImageView) layout.findViewById(R.id.setting_build_icon);
                buildButton.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pw.dismiss();
                        buildAction();
                    }
                });


                rollDiceButton = (ImageView) layout.findViewById(R.id.setting_roll_dice_icon_img);
                rollDiceButton.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pw.dismiss();
                        rollDiceAction();
                    }
                });

                cardsButton = (ImageView) layout.findViewById(R.id.setting_card_icon);
                cardsButton.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pw.dismiss();
                        Intent intent = new Intent(getApplicationContext(), CardActivity.class);
                        startActivity(intent);
                    }
                });

                settingHelpIcon = (ImageView) layout.findViewById(R.id.setting_help_icon);
                settingHelpIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View helpLayout = inflater.inflate(R.layout.help_layout, null, false);
                        pw.dismiss();
                        final PopupWindow pw2 = new PopupWindow(helpLayout);
                        pw2.setOutsideTouchable(true);
                        pw2.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                        pw2.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                        fragmentLayout = (FrameLayout) findViewById(R.id.fragment_layout1);
                        pw2.showAtLocation(fragmentLayout, Gravity.CENTER,-pw2.getWidth()/2, -pw2.getHeight()/2);
                    }
                });
                exchangeButton = (ImageView) layout.findViewById(R.id.setting_exchange_icon);
                exchangeButton.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        exchangeAction();
                    }
                });





                pw.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                pw.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                fragmentLayout = (FrameLayout) findViewById(R.id.fragment_layout1);
                pw.showAtLocation(fragmentLayout, Gravity.CENTER,-pw.getWidth()/2, -pw.getHeight()/2);

            }
        });

        player1Icon = (ImageView) findViewById(R.id.player_1_list_icon);
        player1Icon.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });

        player2Icon = (ImageView) findViewById(R.id.player_2_list_icon);
        player2Icon.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });

        player3Icon = (ImageView) findViewById(R.id.player_3_list_icon);
        player3Icon.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });

        player4Icon = (ImageView) findViewById(R.id.player_4_list_icon);
        player4Icon.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }


    public void check(String action) {
        //TODO : Handle the case here
        switch (action) {
            case "Räuber versetzen":
                Intent intent = new Intent(getApplicationContext(),BuildActivity.class);
                intent.putExtra("Activity","Robber");
                startActivity(intent);
                break;
            case "Handeln oder Bauen":
                infoTextView.setText("You can build or Trade !");
                break;
            case "Würfeln":
                break;
            case "Karten wegen Räuber abgeben":
                if (Game.getPlayer().getResources().getAllResources()>7) {
                    Intent intent1 = new Intent(getApplicationContext(), ReleaseCardActivity.class);
                    intent1.putExtra("number", (int) Game.getPlayer().getResources().getAllResources()/2);
                    startActivity(intent1);
                }
                break;
            case "Warten":
                break;
            case "Verbindung verloren":
                break;
        }

    }

    public void showPlayerInfo(int playerId) {
        Player p = Game.findPlayerById(playerId);

        TextView numLumber = (TextView) findViewById(R.id.num_lumber);
        TextView numBrick = (TextView) findViewById(R.id.num_brick);
        TextView numWool = (TextView) findViewById(R.id.num_wool);
        TextView numOre =  (TextView) findViewById(R.id.num_ore);
        TextView numGrain = (TextView) findViewById(R.id.num_wheat);
        numLumber.setText(p.getResources().lumber);
        numBrick.setText(p.getResources().brick);
        numWool.setText(p.getResources().wool);
        numOre.setText(p.getResources().ore);
        numGrain.setText(p.getResources().grain);
    }
}