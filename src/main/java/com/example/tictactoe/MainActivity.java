package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String X_Score="com.example.tic-tac-toe.X_Score";
    public static final String O_Score="com.example.tic-tac-toe.O_Score";
    public static final String Draw_Score="com.example.tic-tac-toe.Draw_Score";

    static int x_wins=0,o_wins=0,draws=0;

    boolean gameActive=true;
    //state meanings
    //0-x
    //1-0
    //2-Null
    int[] gameState={2,2,2,2,2,2,2,2,2};
    //player representation
    //0-x
    //1-0
    int activePlayer=0;
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}};
    public void playerTap(View view)
    {
        TextView status=findViewById(R.id.status);
        ImageView img=(ImageView)view;
        int tappedImage=Integer.parseInt(img.getTag().toString());
        if(!gameActive)
            gameReset(view);
        else
        if(gameState[tappedImage]==2)
        {
            gameState[tappedImage]=activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer==0)
            {
                img.setImageResource(R.drawable.x);
                activePlayer=1;
                status.setText("O's Turn");
            }
            else
            {
                img.setImageResource(R.drawable.o);
                activePlayer=0;
                status.setText("X's Turn");
            }
            img.animate().translationYBy(1000f).setDuration(100);
        }
        //check win status
        int found=0;
        for(int[] winpos:winningPositions)
        {
            if(gameState[winpos[0]]==gameState[winpos[1]] && gameState[winpos[1]]==gameState[winpos[2]]
                    && gameState[winpos[0]]!=2)
            {
                //someone has won--find who
                String winstr;
                gameActive=false;
                if(gameState[winpos[0]]==0)
                {
                    winstr="X has won";
                    x_wins++;
                }
                else
                {
                    winstr="O has won";
                    o_wins++;
                }
                //update status
                status.setText(winstr);
                found=1;
                //Toast.makeText(this, "Tap the grid to Play again...", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        if(found==0 && gameState[0]!=2 && gameState[1]!=2 && gameState[2]!=2 && gameState[3]!=2 && gameState[4]!=2 &&
                gameState[5]!=2 && gameState[6]!=2&& gameState[7]!=2 && gameState[8]!=2)
        {
            status.setText("Match Draw");
            gameActive=false;
            //Toast.makeText(this, "Tap the grid to Play again...", Toast.LENGTH_SHORT).show();
            draws++;
        }

    }
    public void gameReset(View view)
    {
        gameActive=true;
        for(int i=0;i<gameState.length;i++)
            gameState[i]=2;
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        TextView status = findViewById(R.id.status);
        if(activePlayer==0)
        {
            //activePlayer = 1;
            status.setText("X's Turn");
        }
        else
        {
            //activePlayer=0;
            status.setText("O's Turn");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sPrefs = getSharedPreferences("Results",MODE_PRIVATE);
        SharedPreferences.Editor editor = sPrefs.edit();
        x_wins+=sPrefs.getInt("X_Wins",0);
        o_wins+=sPrefs.getInt("O_Wins",0);
        draws+=sPrefs.getInt("Draws",0);
    }

    public void declareResults(View view)
    {
        SharedPreferences sPrefs = getSharedPreferences("Results",MODE_PRIVATE);
        SharedPreferences.Editor editor = sPrefs.edit();
        //x_wins+=sPrefs.getInt("X_Wins",0);
        //o_wins+=sPrefs.getInt("O_Wins",0);
        //draws+=sPrefs.getInt("Draws",0);
        editor.putInt("X_Wins",x_wins);
        editor.putInt("O_Wins",o_wins);
        editor.putInt("Draws",draws);
        editor.commit();

        Intent intent=new Intent(this,ResultsActivity.class);
        /*
        intent.putExtra(X_Score,x_wins);
        intent.putExtra(O_Score,o_wins);
        intent.putExtra(Draw_Score,draws);*/
        startActivity(intent);
    }
}