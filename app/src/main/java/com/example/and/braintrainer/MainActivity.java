package com.example.and.braintrainer;

import android.os.CountDownTimer;
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
    TextView textViewTime,textViewScore,textViewQuestion,textViewOption1,textViewOption2,textViewOption3,textViewOption4;
    int timeLeft,sum,option,randNumber2,randNumber1,score=0,games=0,maxNum=10;
    String message;
    Random randN=new Random();

    public void startGame(View view){
        Button buttonGo=(Button) findViewById(R.id.buttonGo);
        buttonGo.setTranslationX(-1500);
        myGL.animate().translationXBy(1500);
        textViewTime.animate().translationXBy(1500);
        textViewScore.animate().translationXBy(1500);
        textViewQuestion.animate().translationXBy(1500);
        CountDownTimer yourCountDownTimer=new CountDownTimer(30 * 1000, 1000) {
            public void onTick(long milissecondsUntildone) {
                setClock((int) (milissecondsUntildone / 1000));
                timeLeft=(int) milissecondsUntildone;
            }
            public void onFinish() {
                // unable everything
                //myGL.setEnabled(false);
                Log.i("Acabou","time is up");
            }
        }.start();

        setQuestion();

    }

    public void setQuestion(){
        // check the time
        randNumber1=randN.nextInt(maxNum)+1;
        randNumber2=randN.nextInt(maxNum)+1;
        List<Integer> numbers=generateNumbers(randNumber1,randNumber2);
        String message=String.format("%2d + %2d",randNumber1,randNumber2);
        textViewQuestion.setText(message);
        // make answer in a random textView and avoid option to be repeated, avoid negative
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
        List<Integer> numbers = new ArrayList<>();
        sum=randNumber1+randNumber2;
        numbers.add(sum);
        for (int i = 0; numbers.size() <4; i++) {
            // usually gives numbers lower than the true sum
            element=abs(sum+(randN.nextInt(2*sum)*(-1)^randN.nextInt(3)))+1;
            if (!numbers.contains(element)) {
                numbers.add(element);
            }
        }
        Collections.shuffle(numbers);
        Log.i("Array", randNumber1+" "+randNumber2+" "+numbers.toString());
        return numbers;
    }

    public void checkAnswer(View view){
        TextView clicked=(TextView) view;
        if (sum==Integer.parseInt(view.getTag().toString())){
            score++;
            games++;
            message=String.format("%2d/%2d",score,games);
            textViewScore.setText(message);
            setQuestion();
        } else {
            games++;
            message=String.format("%2d/%2d",score,games);
            textViewScore.setText(message);
            setQuestion();
        }
    }

    public void setClock(int seconds){
        message=String.format("%02ds", seconds);
        textViewTime.setText(message);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myGL=(GridLayout) findViewById(R.id.gridLayout);
        textViewTime=(TextView) findViewById(R.id.textViewTime);
        textViewScore=(TextView) findViewById(R.id.textViewScore);
        textViewQuestion=(TextView) findViewById(R.id.textViewQuestion);
        myGL.setTranslationX(-1500);
        textViewTime.setTranslationX(-1500);
        textViewScore.setTranslationX(-1500);
        textViewQuestion.setTranslationX(-1500);
        textViewOption1=(TextView) findViewById(R.id.textViewOption1);
        textViewOption2=(TextView) findViewById(R.id.textViewOption2);
        textViewOption3=(TextView) findViewById(R.id.textViewOption3);
        textViewOption4=(TextView) findViewById(R.id.textViewOption4);

    }
}
