package com.example.tuan.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tuan.myapplication.model.Cost;

import org.w3c.dom.Text;

/**
 * Created by Tuan on 23.07.2017.
 */
public class ReleaseCardActivity extends AppCompatActivity {
    int toRelease = 0;
    Cost current ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        current = new Cost();
        toRelease = intent.getIntExtra("number",0);
        setContentView(R.layout.release_card_activity);
        final TextView brickText = (TextView)findViewById(R.id.text_left_11);
        final TextView grainText = (TextView)findViewById(R.id.text_left_21);
        final TextView oreText = (TextView)findViewById(R.id.text_left_31);
        final TextView woolText = (TextView)findViewById(R.id.text_left_41);
        final TextView woodText = (TextView)findViewById(R.id.text_left_51);
        ImageView brickButton = (ImageView) findViewById(R.id.right_side_res11);
        brickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addOneMore()) {
                    current.brick++;
                    brickText.setText(current.brick);
                } else {
                    Intent in = new Intent(getApplicationContext(), MessageActivity.class);
                    in.putExtra("message","You give enough resource away!");
                }
            }
        });

        ImageView grainButton = (ImageView) findViewById(R.id.right_side_res21);
        grainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addOneMore()) {
                    current.grain++;
                    grainText.setText(current.grain);
                } else {
                    Intent in = new Intent(getApplicationContext(), MessageActivity.class);
                    in.putExtra("message","You give enough resource away!");
                }
            }
        });

        ImageView oreButton = (ImageView) findViewById(R.id.right_side_res31);
        oreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addOneMore()) {
                    current.ore++;
                    oreText.setText(current.ore);
                } else {
                    Intent in = new Intent(getApplicationContext(), MessageActivity.class);
                    in.putExtra("message","You give enough resource away!");
                }
            }
        });

        ImageView woolButton = (ImageView) findViewById(R.id.right_side_res41);
        woolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addOneMore()) {
                    current.wool++;
                    woolText.setText(current.wool);
                } else {
                    Intent in = new Intent(getApplicationContext(), MessageActivity.class);
                    in.putExtra("message","You give enough resource away!");
                }
            }
        });

        ImageView woodButton = (ImageView) findViewById(R.id.right_side_res51);
        woodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addOneMore()) {
                    current.lumber++;
                    woodText.setText(current.ore);
                } else {
                    Intent in = new Intent(getApplicationContext(), MessageActivity.class);
                    in.putExtra("message","You give enough resource away!");
                }
            }
        });





    }

    public boolean addOneMore() {
        if (current.getAllResources()<toRelease) {
            return true;}
        return  false ;
    }
}
