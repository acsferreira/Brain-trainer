package com.example.and.braintrainer;
// still present error and crashes after a few tries playing
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity {
    GridLayout myGL;
    TextView textViewTime,textViewScore,textViewInfo,textViewQuestion,textViewOption1,textViewOption2,textViewOption3,textViewOption4;
    int sum,option,randNumber2,randNumber1,score=0,games=0,maxNum=21;
    String message;
    Random randN=new Random();
    Button buttonRestart,buttonGo;
    CountDownTimer yourCountDownTimer;
    List<Integer> numbers = new ArrayList<>();
    ConstraintLayout gameLayout;

    public void startGame(View view){
        buttonGo.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(view);
    }

    public void playAgain(View view){
        //buttonRestart.setVisibility(View.INVISIBLE);
        score=0;
        games=0;
        textViewInfo.setText("");
        textViewOption1.setEnabled(true);
        textViewOption2.setEnabled(true);
        textViewOption3.setEnabled(true);
        textViewOption4.setEnabled(true);
        yourCountDownTimer=new CountDownTimer(30000, 1000) {
            public void onTick(long milissecondsUntildone) {
                setClock((int) (milissecondsUntildone / 1000));
            }
            public void onFinish() {
                // unable everything
                textViewOption1.setEnabled(false);
                textViewOption2.setEnabled(false);
                textViewOption3.setEnabled(false);
                textViewOption4.setEnabled(false);
                buttonRestart.setVisibility(View.VISIBLE);
                textViewInfo.setText("Done!");
            }
        }.start();
        setQuestion(view);
    }

    public void setQuestion(View view){
        // check the time
        randNumber1=randN.nextInt(maxNum)+1;
        randNumber2=randN.nextInt(maxNum)+1;
        numbers=generateNumbers(randNumber1,randNumber2);
        message=String.format("%2d + %2d",randNumber1,randNumber2);
        textViewQuestion.setText(message);
        textViewOption1.setText(String.valueOf(numbers.get(0)));
        textViewOption1.setTag(String.valueOf(numbers.get(0)));
        textViewOption2.setText(String.valueOf(numbers.get(1)));
        textViewOption2.setTag(String.valueOf(numbers.get(1)));
        textViewOption3.setTag(String.valueOf(numbers.get(2)));
        textViewOption3.setText(String.valueOf(numbers.get(2)));
        textViewOption4.setText(String.valueOf(numbers.get(3)));
        textViewOption4.setTag(String.valueOf(numbers.get(3)));
    }

    public List generateNumbers(int randNumber1,int randNumber2){
        int element;
        numbers.clear();
        sum=randNumber1+randNumber2;
        numbers.add(sum);
        // make answer in a random textView and avoid option to be repeated, avoid negative
        for (int i = 0; numbers.size() <4; i++) {
            // usually gives numbers lower than the true sum
            element=abs(sum+(randN.nextInt(randNumber1)*(-1)^randN.nextInt(2))+1);
            if (!numbers.contains(element)) {
                numbers.add(element);
            }
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    public void checkAnswer(View view){
        if (sum==Integer.parseInt(view.getTag().toString())){
            score++;
            textViewInfo.setText("Correct!");
        } else {
            textViewInfo.setText("Wrong :(");
        }
        games++;
        message=String.format("%2d/%2d",score,games);
        textViewScore.setText(message);
        setQuestion(view);
    }

    public void setClock(int seconds){
        message=String.format("%02ds", seconds);
        textViewTime.setText(message);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGo=(Button) findViewById(R.id.buttonGo);
        myGL=(GridLayout) findViewById(R.id.gridLayout);
        textViewTime=(TextView) findViewById(R.id.textViewTime);
        textViewScore=(TextView) findViewById(R.id.textViewScore);
        textViewQuestion=(TextView) findViewById(R.id.textViewQuestion);
        textViewOption1=(TextView) findViewById(R.id.textViewOption1);
        textViewOption2=(TextView) findViewById(R.id.textViewOption2);
        textViewOption3=(TextView) findViewById(R.id.textViewOption3);
        textViewOption4=(TextView) findViewById(R.id.textViewOption4);
        textViewInfo=(TextView) findViewById(R.id.textViewInfo);
        buttonRestart=(Button) findViewById(R.id.buttonRestart);
        gameLayout=(ConstraintLayout) findViewById(R.id.gameLayout);
        buttonGo.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}
