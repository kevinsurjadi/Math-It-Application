package au.edu.unsw.infs3634.mathit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class StartActivity extends AppCompatActivity {
    private TextView question;
    private TextView score;
    private ProgressBar progress;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;
    private TextView countDownText;

    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = 16000; // 60seconds
    int points = 0;
    int answer = 0;
    int correct = 0;
    int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // Initialising all the textViews
        progress = (ProgressBar) findViewById(R.id.progressBar);

        score = (TextView) findViewById(R.id.score);
        countDownText = (TextView) findViewById(R.id.timer);
        question = (TextView) findViewById(R.id.operator);

        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);

        startTimer();
        answer = newQuestion();
        // checkTimer(String.valueOf(points));

        View.OnClickListener answerButtonOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button buttonClicked = (Button) v;

                int answerSelected = Integer.valueOf(buttonClicked.getText().toString());

                points = (answer == answerSelected) ? (points + 20) : (points - 10);
                correct = (answer == answerSelected) ? (correct + 1) : correct;
                total++;

                String mark = String.format("%d / %d correct", correct, total);

                String result = (answer == answerSelected) ? "Correct +20" : "Incorrect -10";

                score.setText(String.format("%d pts", points));
                // score.setText(String.valueOf(answer));
                // String check = (newQuestion() == answerSelected) ? "Correct!" : "Incorrect!";
                // checkAnswer(check, stopTimer());
                Toast.makeText(StartActivity.this, result, Toast.LENGTH_SHORT).show();

                answer = newQuestion();
                checkTimer(String.valueOf(points), mark);
            }
        };

        answer1.setOnClickListener(answerButtonOnClickListener);
        answer2.setOnClickListener(answerButtonOnClickListener);
        answer3.setOnClickListener(answerButtonOnClickListener);
        answer4.setOnClickListener(answerButtonOnClickListener);

    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliseconds = millisUntilFinished;
                updateTimer();
                progress.setProgress((int)timeLeftInMilliseconds/1000);
            }

            @Override
            public void onFinish() {

            }
        }.start();

        //timerRunning = true;
    }

    public String stopTimer() {
        int speed = (int) (60 - timeLeftInMilliseconds/1000);
        return String.format("%s Seconds",speed);
    }

    public void updateTimer() {
        int seconds = (int) timeLeftInMilliseconds/1000;

        String timeLeftText = (seconds<10) ? "0"+String.valueOf(seconds) : String.valueOf(seconds);
        countDownText.setText(timeLeftText);
    }

    public void checkTimer(String x, String y) {
        int seconds = (int) timeLeftInMilliseconds/1000;

        if (seconds == 0) {
            Intent intent = new Intent(StartActivity.this, ResultActivity.class);
            intent.putExtra("result", String.format("%s points", x));
            intent.putExtra("mark", y);
            startActivity(intent);
        }
    }

    public void checkAnswer(String x, String y) {
        Intent intent = new Intent(StartActivity.this, ResultActivity.class);
        intent.putExtra("result", String.format("%s points", x));
        intent.putExtra("time", y);
        startActivity(intent);
    }

    public int newQuestion() {
        Random rand = new Random();
        String[] operators = {"+", "-", "x", "÷"};

        // randomise index for the set equation
        int x = rand.nextInt(100);
        int y = rand.nextInt(100);
        int z = rand.nextInt(4);
        int[] result = {x+y, x-y, x*y, x/y};

        String equation = String.format("%s %s %s", String.valueOf(x), operators[z], String.valueOf(y));
        question.setText(equation);

        int[] answers = {result[z], (result[z] + 1), (result[z] + 10), (result[z] - 5), (result[z] - 2)};
        shuffle(answers);

        answer1.setText(String.valueOf(answers[0]));
        answer2.setText(String.valueOf(answers[1]));
        answer3.setText(String.valueOf(answers[2]));
        answer4.setText(String.valueOf(answers[3]));

        return result[z];
    }

    public int[] shuffle(int[] numbers) {
        int temp;
        int k;
        Random rand = new Random();

        for (int i = 0; i < 4; i++) {
            k = rand.nextInt(4);
            temp = numbers[i];
            numbers[i] = numbers[k];
            numbers[k] = temp;
        }

        return numbers;
    }
}