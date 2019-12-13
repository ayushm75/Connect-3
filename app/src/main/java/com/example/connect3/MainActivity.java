package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int state[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int win[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int active = 0;
    boolean gameactive = true;
    public void dropin(View v)
    {

        ImageView counter = (ImageView)v;
        Log.i("Tag", counter.getTag().toString());
        counter.setTranslationY(-3000);
        int tap = Integer.parseInt(counter.getTag().toString());
        if(state[tap] == 2 && gameactive == true) {
            state[tap] = active;
            if (active == 0) {
                counter.setImageResource(R.drawable.yellow);
                active = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                active = 0;
            }
            counter.animate().translationYBy(3000).rotation(720).setDuration(600);

            for (int winning[] : win) {
                if (state[winning[0]] == state[winning[1]] && state[winning[2]] == state[winning[1]] && state[winning[0]] != 2) {
                    String s;
                    if (active == 1)
                        s = "Yellow";
                    else
                        s = "Red";
                    //Toast.makeText(this, s, Toast.LENGTH_LONG).show();
                    gameactive = false;
                    Button b = (Button)findViewById(R.id.button);
                    TextView tv = (TextView)findViewById(R.id.textView);
                    tv.setText(s+" won!!");
                    b.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playagain(View v)
    {
        Button b = (Button)findViewById(R.id.button);
        TextView tv = (TextView)findViewById(R.id.textView);
        //tv.setText(s+" won!!");
        b.setVisibility(View.INVISIBLE);
        tv.setVisibility(View.INVISIBLE);
        androidx.gridlayout.widget.GridLayout grid = (androidx.gridlayout.widget.GridLayout)findViewById(R.id.gridLayout);
        for(int i = 0; i < grid.getChildCount(); i++)
        {
            ImageView im = (ImageView)grid.getChildAt(i);
            im.setImageDrawable(null);
        }
        for(int x = 0 ; x < 9; x++) {
            state[x] = 2;
        }
        //}[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
        //int win[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
        active = 0;
        gameactive = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
