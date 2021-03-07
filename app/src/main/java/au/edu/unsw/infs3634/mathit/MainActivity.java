package au.edu.unsw.infs3634.mathit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton button1;
    private ImageButton button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (ImageButton) findViewById(R.id.imageButton1);
        button2 = (ImageButton) findViewById(R.id.imageButton2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startEasy();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startHard();
            }
        });
    }

    // sets up difficulty type with a string variable for later use
    public void startEasy() {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtra("difficulty", "easy");
        startActivity(intent);
    }

    public void startHard() {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtra("difficulty", "hard");
        startActivity(intent);
    }
}