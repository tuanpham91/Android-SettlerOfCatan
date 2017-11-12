package com.example.tuan.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tuan.myapplication.Server.SendJSON;
import com.example.tuan.myapplication.model.Cost;
import com.example.tuan.myapplication.model.ExchangeRate;
import com.example.tuan.myapplication.model.Game;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Tuan on 18.06.2017.
 */

public class ExchangeActivity extends AppCompatActivity{
    public Cost.ResourceType from;
    public Cost.ResourceType to;
    public int fromRate = 4;
    public int toRate= 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View layout = getLayoutInflater().inflate(R.layout.exchange_activity_layout2,null);
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;

        layout.setSystemUiVisibility(uiOptions);
        setContentView(layout);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Spinner spinnerFrom = (Spinner) findViewById(R.id.spinnerFrom);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.resources_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);

        Spinner spinnerTo = (Spinner) findViewById(R.id.spinnerTo);


        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.resources_type_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTo.setAdapter(adapter);

        final ImageView imageFromExchange = (ImageView) findViewById(R.id.imageView);
        final ImageView imageToExchange = (ImageView) findViewById(R.id.imageView2);
        final TextView textFromExchange = (TextView) findViewById(R.id.fromTextExchange);
        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageFromExchange.setImageResource(getImageIdFromPosition(position));

                from = getResourceFromPosition(position);
                fromRate = Game.getPlayer().lookForExchangeRate(from);
                textFromExchange.setText(String.valueOf(fromRate));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageToExchange.setImageResource(getImageIdFromPosition(position));
                fromRate = Game.getPlayer().lookForExchangeRate(from);
                textFromExchange.setText(String.valueOf(fromRate));
                to = getResourceFromPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ImageView exit_activity = (ImageView) findViewById(R.id.exit_exchange_image);
        exit_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView confirmButton = (ImageView) findViewById(R.id.exchange_confirm_button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cost cost;
                switch (from) {
                    case WOOD:
                        cost = new Cost(fromRate,0,0,0,0);
                        break;
                    case BRICK:
                        cost = new Cost(0,fromRate,0,0,0);
                        break;
                    case WOOL:
                        cost = new Cost(0,0,fromRate,0,0);
                        break;
                    case GRAIN:
                        cost = new Cost(0,0,0,fromRate,0);
                        break;
                    default:
                        cost = new Cost (0,0,0,0,fromRate);
                }
                Cost changeTo ;
                switch (from) {
                    case WOOD:
                        changeTo = new Cost(fromRate,0,0,0,0);
                        break;
                    case BRICK:
                        changeTo = new Cost(0,fromRate,0,0,0);
                        break;
                    case WOOL:
                        changeTo = new Cost(0,0,fromRate,0,0);
                        break;
                    case GRAIN:
                        changeTo = new Cost(0,0,0,fromRate,0);
                        break;
                    default:
                        changeTo = new Cost (0,0,0,0,fromRate);
                }
                try {
                    SendJSON.sendSeehandel(Game.getPlayer().id, cost, changeTo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public Cost.ResourceType getResourceFromPosition(int pos) {
        switch (pos){
            case 0:
                return Cost.ResourceType.WOOD;
            case 1:
                return Cost.ResourceType.WOOL;
            case 2:
                return Cost.ResourceType.GRAIN;
            case 3:
                return Cost.ResourceType.ORE;
            case 4:
                return Cost.ResourceType.BRICK;
            default:
                return Cost.ResourceType.ANYTHING;
        }
    }
    public int getImageIdFromPosition (int pos) {
        switch (pos){
            case 0:
                return R.drawable.wood_trade_icon;
            case 1:
                return R.drawable.sheep_trade_icon;
            case 2:
                return R.drawable.wheat_trade_icon;
            case 3:
                return R.drawable.ore_trade_icon;
            case 4:
                return R.drawable.brick_trade_icon;
            default:
                return R.drawable.brick_trade_icon;

        }

    }
}
