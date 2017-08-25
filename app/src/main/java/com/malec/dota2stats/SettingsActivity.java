package com.malec.dota2stats;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

public class SettingsActivity extends Activity
{
    Button SearchButton;
    EditText PlayerName;

    ScrollView sv2 = null;

    public void CalculatePlayers()
    {
        final List<PlayerData> pds = Content.GetPlayers(15, PlayerName.getText().toString());

        for (int i = 0; i < 15; i++)
        {
            ConstraintLayout record = null;
            switch (i)
            {
                case 0:record = (ConstraintLayout) findViewById(R.id.player1); break;
                case 1:record = (ConstraintLayout) findViewById(R.id.player2); break;
                case 2:record = (ConstraintLayout) findViewById(R.id.player3); break;
                case 3:record = (ConstraintLayout) findViewById(R.id.player4); break;
                case 4:record = (ConstraintLayout) findViewById(R.id.player5); break;
                case 5:record = (ConstraintLayout) findViewById(R.id.player6); break;
                case 6:record = (ConstraintLayout) findViewById(R.id.player7); break;
                case 7:record = (ConstraintLayout) findViewById(R.id.player8); break;
                case 8:record = (ConstraintLayout) findViewById(R.id.player9); break;
                case 9:record = (ConstraintLayout) findViewById(R.id.player10); break;
                case 10:record = (ConstraintLayout) findViewById(R.id.player11); break;
                case 11:record = (ConstraintLayout) findViewById(R.id.player12); break;
                case 12:record = (ConstraintLayout) findViewById(R.id.player13); break;
                case 13:record = (ConstraintLayout) findViewById(R.id.player14); break;
                case 14:record = (ConstraintLayout) findViewById(R.id.player15); break;
            }

            ImageView PlayerImage = (ImageView) record.findViewById(R.id.PlayerImage);
            TextView PlayerName = (TextView) record.findViewById(R.id.PlayerName);
            TextView PlayerLastMatch = (TextView) record.findViewById(R.id.PlayerLastMatch);

            PlayerData p = pds.get(i);

            String playername = p.PlayerName;
            String playerimage = p.PlayerImage;
            String playerlastmatch = p.PlayerLastMatch;

            new DownloadImageTask(PlayerImage).execute(playerimage);
            PlayerName.setText(playername);
            PlayerLastMatch.setText(playerlastmatch);
        }

        sv2 = (ScrollView)findViewById(R.id.scrollView2);
        sv2.setVisibility(ScrollView.VISIBLE);

        //region dermo

        final ConstraintLayout Player1 = (ConstraintLayout)findViewById(R.id.player1);
        Player1.setOnClickListener(new View.OnClickListener()
        {@Override public void onClick(View view){MainActivity.PlayerUrl = "https://www.dotabuff.com/players/" + pds.get(0).PlayerID; finish();}});

        final ConstraintLayout Player2 = (ConstraintLayout)findViewById(R.id.player2);
        Player2.setOnClickListener(new View.OnClickListener()
        {@Override public void onClick(View view){MainActivity.PlayerUrl = "https://www.dotabuff.com/players/" + pds.get(1).PlayerID; finish();}});

        final ConstraintLayout Player3 = (ConstraintLayout)findViewById(R.id.player3);
        Player3.setOnClickListener(new View.OnClickListener()
        {@Override public void onClick(View view){MainActivity.PlayerUrl = "https://www.dotabuff.com/players/" + pds.get(2).PlayerID; finish();}});

        final ConstraintLayout Player4 = (ConstraintLayout)findViewById(R.id.player4);
        Player4.setOnClickListener(new View.OnClickListener()
        {@Override public void onClick(View view){MainActivity.PlayerUrl = "https://www.dotabuff.com/players/" + pds.get(3).PlayerID; finish();}});

        final ConstraintLayout Player5 = (ConstraintLayout)findViewById(R.id.player5);
        Player5.setOnClickListener(new View.OnClickListener()
        {@Override public void onClick(View view){MainActivity.PlayerUrl = "https://www.dotabuff.com/players/" + pds.get(4).PlayerID; finish();}});

        final ConstraintLayout Player6 = (ConstraintLayout)findViewById(R.id.player6);
        Player6.setOnClickListener(new View.OnClickListener()
        {@Override public void onClick(View view){MainActivity.PlayerUrl = "https://www.dotabuff.com/players/" + pds.get(5).PlayerID; finish();}});

        final ConstraintLayout Player7 = (ConstraintLayout)findViewById(R.id.player7);
        Player7.setOnClickListener(new View.OnClickListener()
        {@Override public void onClick(View view){MainActivity.PlayerUrl = "https://www.dotabuff.com/players/" + pds.get(6).PlayerID; finish();}});

        final ConstraintLayout Player8 = (ConstraintLayout)findViewById(R.id.player8);
        Player8.setOnClickListener(new View.OnClickListener()
        {@Override public void onClick(View view){MainActivity.PlayerUrl = "https://www.dotabuff.com/players/" + pds.get(7).PlayerID; finish();}});

        final ConstraintLayout Player9 = (ConstraintLayout)findViewById(R.id.player9);
        Player9.setOnClickListener(new View.OnClickListener()
        {@Override public void onClick(View view){MainActivity.PlayerUrl = "https://www.dotabuff.com/players/" + pds.get(8).PlayerID; finish();}});

        final ConstraintLayout Player10 = (ConstraintLayout)findViewById(R.id.player10);
        Player10.setOnClickListener(new View.OnClickListener()
        {@Override public void onClick(View view){MainActivity.PlayerUrl = "https://www.dotabuff.com/players/" + pds.get(9).PlayerID; finish();}});

        final ConstraintLayout Player11 = (ConstraintLayout)findViewById(R.id.player11);
        Player11.setOnClickListener(new View.OnClickListener()
        {@Override public void onClick(View view){MainActivity.PlayerUrl = "https://www.dotabuff.com/players/" + pds.get(10).PlayerID; finish();}});

        final ConstraintLayout Player12 = (ConstraintLayout)findViewById(R.id.player12);
        Player12.setOnClickListener(new View.OnClickListener()
        {@Override public void onClick(View view){MainActivity.PlayerUrl = "https://www.dotabuff.com/players/" + pds.get(11).PlayerID; finish();}});

        final ConstraintLayout Player13 = (ConstraintLayout)findViewById(R.id.player13);
        Player13.setOnClickListener(new View.OnClickListener()
        {@Override public void onClick(View view){MainActivity.PlayerUrl = "https://www.dotabuff.com/players/" + pds.get(12).PlayerID;}});

        final ConstraintLayout Player14 = (ConstraintLayout)findViewById(R.id.player14);
        Player14.setOnClickListener(new View.OnClickListener()
        {@Override public void onClick(View view){MainActivity.PlayerUrl = "https://www.dotabuff.com/players/" + pds.get(13).PlayerID;}});

        final ConstraintLayout Player15 = (ConstraintLayout)findViewById(R.id.player15);
        Player15.setOnClickListener(new View.OnClickListener()
        {@Override public void onClick(View view){MainActivity.PlayerUrl = "https://www.dotabuff.com/players/" + pds.get(14).PlayerID;}});

        //regionend
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        sv2 = (ScrollView)findViewById(R.id.scrollView2);
        sv2.setVisibility(ScrollView.GONE);

        SearchButton = (Button)findViewById(R.id.button);
        PlayerName = (EditText)findViewById(R.id.editText);

        PlayerName.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent)
            {
                CalculatePlayers();

                return false;
            }
        });


        SearchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                CalculatePlayers();
            }
        });
    }
}
