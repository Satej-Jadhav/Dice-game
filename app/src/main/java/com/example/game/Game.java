package com.example.game;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class Game extends AppCompatActivity {
    public String player1;
    public String player2;

    public int score1;

    public int player1_score = 0;
    public int player2_score = 0;
    public int Target = 50;

    public int chance = 0;
    public int common;

    public Button roll;
    public Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //Taking values from previous activity
        TextView textView = (TextView) findViewById(R.id.player01);
        TextView textView1 = (TextView) findViewById(R.id.player02);

        Intent i = getIntent();
        player1 = i.getStringExtra("player1");
        textView.setText(player1);

        player2 = i.getStringExtra("player2");
        textView1.setText(player2);

        TextView final_score = (TextView) findViewById(R.id.common_score);
        common = 0;

        TextView  player1_final=(TextView) findViewById(R.id.Score_01);
        TextView  player2_final=(TextView) findViewById(R.id.Score_02);


        roll = (Button) findViewById(R.id.play);

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random score = new Random();
                score1 = 1 + score.nextInt(6);
                switch (score1) {
                    case 1:
                        ImageView dice1 = (ImageView) findViewById(R.id.imageView);
                        dice1.setImageResource(R.drawable.dice1);
                        final_score.setText(" OUT ");
                        common = common - common;
                        chance = chance + 1;
                        break;
                    case 2:
                        ImageView dice2 = (ImageView) findViewById(R.id.imageView);
                        dice2.setImageResource(R.drawable.dice2);
                        common = common+ 2;
                        final_score.setText(" "+common);
                        break;
                    case 3:
                        ImageView dice3 = (ImageView) findViewById(R.id.imageView);
                        dice3.setImageResource(R.drawable.dice3);
                        common = common + 3;
                        final_score.setText(" "+common);
                        break;
                    case 4:
                        ImageView dice4 = (ImageView) findViewById(R.id.imageView);
                        dice4.setImageResource(R.drawable.dice4);
                        common = common + 4;
                        final_score.setText(" "+common);
                        break;
                    case 5:
                        ImageView dice5 = (ImageView) findViewById(R.id.imageView);
                        dice5.setImageResource(R.drawable.dice5);
                        common = common + 5;
                        final_score.setText(" "+common);
                        break;
                    case 6:
                        ImageView dice6 = (ImageView) findViewById(R.id.imageView);
                        dice6.setImageResource(R.drawable.dice6);
                        common = common + 6;
                        final_score.setText(" "+common);
                        break;

                }
            }
        });

        save = (Button) findViewById(R.id.save_score);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chance % 2 == 0) {
                    player1_score = player1_score + common;
                    chance = chance+1;
                    player1_final.setText("" + player1_score);
                    if (player1_score>=Target) {
                        AlertDialog.Builder builder= new AlertDialog.Builder(Game.this);
                        builder.setMessage(player1 + " WINS ")
                                .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        common -=common;
                                        player1_score -= player1_score;
                                        player2_score -= player2_score;
                                        chance -= chance;

                                        player1_final.setText("" + player1_score);
                                        player2_final.setText("" + player2_score);
                                        final_score.setText(" "+common);
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }
                else {
                    player2_score = player2_score + common;
                    player2_final.setText("" + player2_score);
                    chance = chance+1;
                    if (Target <= player2_score){
                        AlertDialog.Builder builder= new AlertDialog.Builder(Game.this);
                        builder.setMessage(player2 + " WINS ");
                        builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                common -= common;
                                player1_score -= player1_score;
                                player2_score -= player2_score;
                                chance -= chance;

                                player1_final.setText("" + player1_score);
                                player2_final.setText("" + player2_score);
                                final_score.setText(" " + common);

                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }
                common = common-common;


            }
        });
    }

}