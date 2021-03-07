package au.edu.unsw.infs3634.mathit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity2 extends AppCompatActivity {
    private ImageButton button1;
    private ImageButton button2;
    private ImageButton button3;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button1 = (ImageButton) findViewById(R.id.imageButton1);
        button2 = (ImageButton) findViewById(R.id.imageButton2);
        button3 = (ImageButton) findViewById(R.id.imageButton3);
        back = (ImageButton) findViewById(R.id.back);

        // creating buttons for each time mode, that associates with a specific value
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame1();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame2();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame3();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    public void startGame1() {
        Intent intent = new Intent(MainActivity2.this, StartActivity.class);
        intent.putExtra("seconds", "16000");
        intent.putExtra("difficulty", getIntent().getStringExtra("difficulty"));
        startActivity(intent);
    }

    public void startGame2() {
        Intent intent = new Intent(MainActivity2.this, StartActivity.class);
        intent.putExtra("seconds", "31000");
        intent.putExtra("difficulty", getIntent().getStringExtra("difficulty"));
        startActivity(intent);
    }

    public void startGame3() {
        Intent intent = new Intent(MainActivity2.this, StartActivity.class);
        intent.putExtra("seconds", "61000");
        intent.putExtra("difficulty", getIntent().getStringExtra("difficulty"));
        startActivity(intent);
    }

    public void back() {
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intent);
    }
}