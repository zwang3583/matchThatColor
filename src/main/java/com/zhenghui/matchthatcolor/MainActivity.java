package com.zhenghui.matchthatcolor;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    Button startButton;
    Button redButton;
    Button greenButton;
    Button blueButton;
    Button yellowButton;
    TextView puzzle;
    TextView time;
    TextView result;
    TextView score;
    String[] colorString = {"red", "blue", "green", "yellow"};
    CountDownTimer timer;
    Random rand = new Random();
    int wins = 0;
    int color = 0;
    int[] colorInt = {0xffff4444, 0xff33b5e5, 0xff99cc00, 0xffffbb33};

    public void turnInvisible(){
        redButton.setVisibility(View.INVISIBLE);
        greenButton.setVisibility(View.INVISIBLE);
        blueButton.setVisibility(View.INVISIBLE);
        yellowButton.setVisibility(View.INVISIBLE);
        puzzle.setVisibility(View.INVISIBLE);
        time.setVisibility(View.INVISIBLE);
        score.setVisibility(View.INVISIBLE);
    }

    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        redButton.setVisibility(View.VISIBLE);
        greenButton.setVisibility(View.VISIBLE);
        blueButton.setVisibility(View.VISIBLE);
        yellowButton.setVisibility(View.VISIBLE);
        puzzle.setVisibility(View.VISIBLE);
        time.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        result.setVisibility(View.VISIBLE);

        generateQuestion();

        timer = new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time.setText(String.valueOf(millisUntilFinished/1000+"s"));

            }

            @Override
            public void onFinish() {
                endGame();
            }
        }.start();
    }

    public void generateQuestion(){
        color = rand.nextInt(4);
        puzzle.setText(colorString[color]);
        puzzle.setTextColor(colorInt[rand.nextInt(4)]);
    }

    public void chooseAnswer(View view){
        if(Integer.parseInt(view.getTag().toString()) == color) {
            wins++;
            result.setText("CORRECT");
            if(wins == 8)
                winGame();
            timer.start();
        }
        else
            endGame();
        score.setText(""+wins+"/"+8);
        generateQuestion();

    }

    public void endGame(){
        turnInvisible();
        result.setText("YOU LOSE!");
    }

    public void winGame(){
        turnInvisible();
        result.setText("YOU WIN!");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        puzzle = findViewById(R.id.puzzleTextView);
        result = findViewById(R.id.resultTextView);
        score = findViewById(R.id.wonGamesTextView);
        time = findViewById(R.id.timerTextView);
        greenButton = findViewById(R.id.greenButton);
        blueButton = findViewById(R.id.blueButton);
        redButton = findViewById(R.id.redButton);
        yellowButton = findViewById(R.id.yellowButton);
    }
}
