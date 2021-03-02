package au.edu.unsw.infs3634.mathit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView result;
    private TextView mark;
    private Button restartButton;
    private Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result = (TextView) findViewById(R.id.result);
        mark = (TextView) findViewById(R.id.mark);
        restartButton = (Button) findViewById(R.id.restart);
        homeButton = (Button) findViewById(R.id.mainMenu);

        result.setText(getIntent().getStringExtra("result"));
        mark.setText(getIntent().getStringExtra("mark"));

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home();
            }
        });
    }

    public void restartGame() {
        Intent intent = new Intent(ResultActivity.this, StartActivity.class);
        startActivity(intent);
    }

    public void home() {
        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
        startActivity(intent);
    }
}