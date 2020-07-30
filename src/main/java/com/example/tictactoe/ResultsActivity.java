package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.example.tictactoe.MainActivity.x_wins;
import static com.example.tictactoe.MainActivity.o_wins;
import static com.example.tictactoe.MainActivity.draws;

public class ResultsActivity extends AppCompatActivity {

    TextView x_t,o_t,s1,s2,d_s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //int x_wins=getIntent().getIntExtra(MainActivity.X_Score,0);
        //int o_wins=getIntent().getIntExtra(MainActivity.O_Score,0);
        //int draws=getIntent().getIntExtra(MainActivity.Draw_Score,0);
        x_t=findViewById(R.id.x_team);
        o_t=findViewById(R.id.o_team);
        s1=findViewById(R.id.score1);
        s2=findViewById(R.id.score2);
        d_s=findViewById(R.id.draw_score);
        SharedPreferences sPrefs= getSharedPreferences("Results", MODE_PRIVATE);
        int x_wins=sPrefs.getInt("X_Wins",0);
        int o_wins=sPrefs.getInt("O_Wins",0);
        int draws=sPrefs.getInt("Draws",0);
        if(x_wins==0 && o_wins==0)
        {
            x_t.setText("X");
            s1.setText(Integer.toString(x_wins));
            o_t.setText("O");
            s2.setText(Integer.toString(o_wins));
        }
        else if(x_wins>=o_wins)
        {
            x_t.setText("X");
            s1.setText(Integer.toString(x_wins));
            o_t.setText("O");
            s2.setText(Integer.toString(o_wins));
        }
        else
        {
            x_t.setText("O");
            s1.setText(Integer.toString(o_wins));
            o_t.setText("X");
            s2.setText(Integer.toString(x_wins));
        }
        d_s.setText(Integer.toString(draws));
    }
    public void resetResults(View view)
    {
        SharedPreferences sPrefs = getSharedPreferences("Results",MODE_PRIVATE);
        SharedPreferences.Editor editor = sPrefs.edit();
        editor.clear();
        editor.commit();
        x_wins=0;
        o_wins=0;
        draws=0;
        x_t.setText("X");
        s1.setText(Integer.toString(x_wins));
        o_t.setText("O");
        s2.setText(Integer.toString(o_wins));
        d_s.setText(Integer.toString(draws));


    }
}