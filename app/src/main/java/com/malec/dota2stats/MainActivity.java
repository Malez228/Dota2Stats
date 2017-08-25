package com.malec.dota2stats;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    public static TabHost tabHost;
    public static TabHost.TabSpec tabStats, tabHeroes, tabRecords;

    public static TextView MmrSolo, MmrParty, WinGames, LoseGames, AbandonGames, Winrate;
    public static TextView HeroName, HeroMatches, HeroWinrate, HeroKDA;
    public static TextView sHeroName, sHeroMatches, sHeroWinrate, sHeroKDA, sHeroRole, sHeroLane;
    public static TextView sHeroKills, sHeroDeaths, sHeroAssists, sHeroGold, sHeroExp, sHeroLastMatch;
    public static ImageView HeroImage;
    public static ImageView sHeroImage;

    public static Spinner HeroesList;

    public static ConstraintLayout Hero;

    public static String PlayerUrl = "https://www.dotabuff.com/players/123878279";
    public static String PlayerNickname;

    public static Content c = new Content(PlayerUrl);

    public static String SelectedHero = "Abaddon";

    public static Boolean NewDay = true;

    public static DataIO file, datetim;

    public static List<Hero> Heroes;

    public static List<Record> Records;

    public static PlayerData playerData;

        /* TODO
        * (готово) допилить что осталось в вкладку герои ( средн. голд экспа) мб еще что найти и запилить
        * (доделать для рекордов в минуту + запись в файл)( нахрен итемы хочу сначала рекорды )вкладка итемов все еще пустая - это следущее пойдет
        * сначала нужно сделать меню настроек
        * можно авторизацию еще добавить но хз для себя любимого же пишу
        * пофиксить смену героя при обновлении
        */

    void InitializeComponents()
    {
        //region Tabs
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        tabStats = tabHost.newTabSpec("tag1");
        tabStats.setContent(R.id.tabStats);
        tabStats.setIndicator("Stats");
        tabHost.addTab(tabStats);

        tabHeroes = tabHost.newTabSpec("tag2");
        tabHeroes.setContent(R.id.tabHeroes);
        tabHeroes.setIndicator("Heroes");
        tabHost.addTab(tabHeroes);

        tabRecords = tabHost.newTabSpec("tag3");
        tabRecords.setContent(R.id.tabRecords);
        tabRecords.setIndicator("Records");
        tabHost.addTab(tabRecords);

        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            View v = tabHost.getTabWidget().getChildAt(i);

            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextSize(14);
            tv.setTextColor(getResources().getColor(R.color.White));
        }
        //endregion

        MmrSolo = (TextView)findViewById(R.id.mmrSolo);
        MmrParty = (TextView)findViewById(R.id.mmrParty);
        WinGames = (TextView)findViewById(R.id.gamesWin);
        LoseGames = (TextView)findViewById(R.id.gamesLose);
        AbandonGames = (TextView)findViewById(R.id.gamesAdandon);
        Winrate = (TextView)findViewById(R.id.winrate);

        sHeroImage = (ImageView)findViewById(R.id.selectedHeroImage);
        sHeroName = (TextView)findViewById(R.id.selectedHeroName);
        sHeroMatches = (TextView)findViewById(R.id.selectedHeroMatches);
        sHeroWinrate = (TextView)findViewById(R.id.selectedHeroWinrate);
        sHeroKDA = (TextView)findViewById(R.id.selectedHeroKDA);
        sHeroRole = (TextView)findViewById(R.id.selectedHeroRole);
        sHeroLane = (TextView)findViewById(R.id.selectedHeroLane);

        sHeroKills = (TextView)findViewById(R.id.selectedHeroKills);
        sHeroDeaths = (TextView)findViewById(R.id.selectedHeroDeaths);
        sHeroAssists = (TextView)findViewById(R.id.selectedHeroAssists);
        sHeroGold = (TextView)findViewById(R.id.selectedHeroGold);
        sHeroExp = (TextView)findViewById(R.id.selectedHeroExp);
        sHeroLastMatch = (TextView)findViewById(R.id.selectedHeroLastMatch);

        HeroesList = (Spinner)findViewById(R.id.HeroesList);
    }

    void CalculateHeader()
    {
        PlayerNickname = playerData.PlayerName;

        MmrSolo.setText("Solo MMR:  " + playerData.SoloMMR);
        MmrParty.setText("Party MMR:  " + playerData.PartyMMR);
        WinGames.setText(playerData.Wins + " W");
        LoseGames.setText(playerData.Losses + " L");
        AbandonGames.setText(playerData.Abandons + " A");
        Winrate.setText(playerData.Winrate);
    }

    void CalculateContent()
    {
        try
        {
            for (int i = 0; i < 10; i++)
            {
                switch (i)
                {
                    case 0: Hero = (ConstraintLayout)findViewById(R.id.hero); break;
                    case 1: Hero = (ConstraintLayout)findViewById(R.id.hero2); break;
                    case 2: Hero = (ConstraintLayout)findViewById(R.id.hero3); break;
                    case 3: Hero = (ConstraintLayout)findViewById(R.id.hero4); break;
                    case 4: Hero = (ConstraintLayout)findViewById(R.id.hero5); break;
                    case 5: Hero = (ConstraintLayout)findViewById(R.id.hero6); break;
                    case 6: Hero = (ConstraintLayout)findViewById(R.id.hero7); break;
                    case 7: Hero = (ConstraintLayout)findViewById(R.id.hero8); break;
                    case 8: Hero = (ConstraintLayout)findViewById(R.id.hero9); break;
                    case 9: Hero = (ConstraintLayout)findViewById(R.id.hero10); break;

                    default: Hero = (ConstraintLayout)findViewById(R.id.hero10);
                }

                HeroImage = (ImageView)Hero.findViewById(R.id.heroImage);
                HeroName = (TextView)Hero.findViewById(R.id.heroName);
                HeroMatches = (TextView)Hero.findViewById(R.id.heroMatches);
                HeroWinrate = (TextView)Hero.findViewById(R.id.winrate);
                HeroKDA = (TextView)Hero.findViewById(R.id.heroKDA);

                Hero h = Heroes.get(i);
                String heroname = h.Name;
                String heromatches = h.Matches;
                String herowinrate = h.Winrate;
                String herokda = h.KDA;

                Bitmap icon = BitmapFactory.decodeResource(getApplicationContext().getResources(), Content.GetHeroImage(heroname));
                HeroImage.setImageBitmap(icon);
                HeroName.setText(heroname);
                HeroMatches.setText(heromatches + " M   ");
                HeroWinrate.setText(herowinrate + "   ");
                HeroKDA.setText(herokda + " KDA");
            }
        }
        catch (Exception e)
        {
            Hero = (ConstraintLayout)findViewById(R.id.hero10);
            HeroName = (TextView)Hero.findViewById(R.id.heroName);
            HeroName.setText(e.toString());
        }

        try
        {
            GetSpinnerContent();
        }
        catch (Exception e) { }
    }

    void CalculateRecords(boolean sw)
    {
        Records = c.GetRecords(sw);

        for (int i = 0; i < 14; i++)
        {
            ConstraintLayout record = null;
            switch (i)
            {
                case 0:record = (ConstraintLayout) findViewById(R.id.heroRec); break;
                case 1:record = (ConstraintLayout) findViewById(R.id.hero2Rec); break;
                case 2:record = (ConstraintLayout) findViewById(R.id.hero3Rec); break;
                case 3:record = (ConstraintLayout) findViewById(R.id.hero4Rec); break;
                case 4:record = (ConstraintLayout) findViewById(R.id.hero5Rec); break;
                case 5:record = (ConstraintLayout) findViewById(R.id.hero6Rec); break;
                case 6:record = (ConstraintLayout) findViewById(R.id.hero7Rec); break;
                case 7:record = (ConstraintLayout) findViewById(R.id.hero8Rec); break;
                case 8:record = (ConstraintLayout) findViewById(R.id.hero9Rec); break;
                case 9:record = (ConstraintLayout) findViewById(R.id.hero10Rec); break;
                case 10:record = (ConstraintLayout) findViewById(R.id.hero11Rec); break;
                case 11:record = (ConstraintLayout) findViewById(R.id.hero12Rec); break;
                case 12:record = (ConstraintLayout) findViewById(R.id.hero13Rec); break;
                case 13:record = (ConstraintLayout) findViewById(R.id.hero14Rec); break;
            }

            ImageView HeroImage = (ImageView) record.findViewById(R.id.heroImage);
            TextView HeroName = (TextView) record.findViewById(R.id.heroName);
            TextView RecordName = (TextView) record.findViewById(R.id.record);
            TextView RecordValue = (TextView) record.findViewById(R.id.heroRecord);

            Record r = Records.get(i);

            String heroname = r.HeroName;
            if (heroname == "")
            {
                HeroImage.setVisibility(ImageView.GONE);
                HeroName.setVisibility(TextView.GONE);
                RecordName.setVisibility(TextView.GONE);
                RecordValue.setVisibility(TextView.GONE);
            }
            else
            {
                HeroImage.setVisibility(ImageView.VISIBLE);
                HeroName.setVisibility(TextView.VISIBLE);
                RecordName.setVisibility(TextView.VISIBLE);
                RecordValue.setVisibility(TextView.VISIBLE);
            }
            String recordname = r.RecordName;
            String recordvalue = r.Value;
            String date = r.Date;

            Bitmap icon = BitmapFactory.decodeResource(getApplicationContext().getResources(), Content.GetHeroImage(heroname));
            HeroImage.setImageBitmap(icon);
            HeroName.setText(heroname);
            RecordName.setText(recordname + "  -  " + recordvalue);
            RecordValue.setText(date);
        }
    }

    int GetColor(String s)
    {
        int color = getResources().getColor(R.color.Gray);

        switch (s)
        {
            case "Support": color = getResources().getColor(R.color.Yellow); break;
            case "Core": color = getResources().getColor(R.color.Red); break;

            case "Mid Lane": color = getResources().getColor(R.color.Purple); break;
            case "Off Lane": color = getResources().getColor(R.color.Orange); break;
            case "Safe Lane": color = getResources().getColor(R.color.BlueGreen); break;
            case "Roaming": color = getResources().getColor(R.color.LightGray); break;
            case "Jungle": color = getResources().getColor(R.color.Green); break;
        }

        return color;
    }

    void GetSpinnerContent()
    {
        Hero h = null;
        for (int i = 0; i < Heroes.size(); i++)
        {
            if (Heroes.get(i).Name.equals(SelectedHero))
            {
                h = Heroes.get(i);
                break;
            }
        }

        Bitmap icon = BitmapFactory.decodeResource(getApplicationContext().getResources(), Content.GetHeroImage(SelectedHero));
        sHeroImage.setImageBitmap(icon);
        sHeroName.setText(SelectedHero);
        sHeroMatches.setText(h.Matches);
        sHeroWinrate.setText(h.Winrate);
        sHeroKDA.setText(h.KDA);
        sHeroKills.setText(h.Kils);
        sHeroDeaths.setText(h.Deaths);
        sHeroAssists.setText(h.Assists);
        sHeroRole.setTextColor(GetColor(h.Role));
        sHeroRole.setText(h.Role);
        sHeroLane.setTextColor(GetColor(h.Lane));
        sHeroLane.setText(h.Lane);
        sHeroGold.setText(h.Gold);
        sHeroExp.setText(h.Exp);
        sHeroLastMatch.setText(h.LastMatch);
    }

    void InitializeHeroesSpinner()
    {
        Spinner spinner = (Spinner) findViewById(R.id.HeroesList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Content.GetHeroesNames());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        HeroesList.setOnItemSelectedListener(new OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String item = (String) parent.getItemAtPosition(position);
                SelectedHero = item;

                try
                {
                    GetSpinnerContent();
                }
                catch (Exception e)
                {
                    Bitmap icon = BitmapFactory.decodeResource(getApplicationContext().getResources(), Content.GetHeroImage(SelectedHero));
                    sHeroImage.setImageBitmap(icon);
                    sHeroName.setText(SelectedHero);
                    sHeroMatches.setText("0");
                    sHeroWinrate.setText("0");
                    sHeroKDA.setText("0");
                    sHeroKills.setText("0");
                    sHeroDeaths.setText("0");
                    sHeroAssists.setText("0");
                    sHeroRole.setText("");
                    sHeroLane.setText("");
                    sHeroGold.setText("0");
                    sHeroExp.setText("0");
                    sHeroLastMatch.setText("Never");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    void LoadFile()
    {
        Heroes = c.GetHeroes(113);
        playerData = c.GetPlayerData();

        file.Write(Heroes, getApplicationContext());

        DataIO file2 = new DataIO("PlayerData.txt");
        file2.WritePlayerData(playerData, getApplicationContext());
    }

    public void SyncData()
    {
        String sssss = PlayerUrl;
        Content ct = new Content(sssss);

        String currentDate = (new Date(System.currentTimeMillis())).toString();
        DataIO datetimNew = new DataIO("Date.txt");
        datetimNew.WriteDate(currentDate, getApplicationContext());

        DataIO fileNew = new DataIO("HeroesData.txt");

        Heroes = ct.GetHeroes(113);
        playerData = ct.GetPlayerData();

        fileNew.Write(Heroes, getApplicationContext());

        DataIO file2 = new DataIO("PlayerData.txt");
        file2.WritePlayerData(playerData, getApplicationContext());

        CalculateHeader();
        CalculateContent();

        InitializeHeroesSpinner();
/*
        Spinner s = (Spinner)findViewById(R.id.HeroesList);
        for (int i = 0; i < Heroes.size() - 1; i++)
        {
            if (Heroes.get(i).Name == SelectedHero)
            {
                s.setSelection(i);
                break;
            }
        }*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //Если дата с прошлого старта отличается нужно заново загрузить данные
        datetim = new DataIO("Date.txt");
        String oldDate = datetim.ReadDate(getApplicationContext());
        String currentDate = (new Date(System.currentTimeMillis())).toString();

        try
        {
            String a = oldDate.split(" ")[1];
            String b = currentDate.split(" ")[1];
            int c = Integer.parseInt(oldDate.split(" ")[2]);
            int d = Integer.parseInt(currentDate.split(" ")[2]);
            /*String a = oldDate.split(" ")[1];
            String b = "lo awd 12";
            String c = oldDate.split(" ")[2];
            String d = "gfw mij 78";*/
            if (a.equals(b) || c == d)
                NewDay = false;
            else
                NewDay = true;
        }
        catch (Exception e)
        {
            NewDay = true;
        }

        if (NewDay)
        {
            datetim.WriteDate(currentDate, getApplicationContext());

            file = new DataIO("HeroesData.txt");
            LoadFile();
        }
        else
        {
            file = new DataIO("HeroesData.txt");
            Heroes = file.Read(getApplicationContext());

            DataIO file2 = new DataIO("PlayerData.txt");
            playerData = file2.ReadPlayerData(getApplicationContext());
        }

        InitializeComponents();

        CalculateHeader();
        CalculateContent();
        CalculateRecords(false);

        InitializeHeroesSpinner();

        toolbar.setTitle("Dota2 Stats  -  " + PlayerNickname);
        setSupportActionBar(toolbar);


        TextView SwitchTotal = (TextView)findViewById(R.id.SwitchTotal);
        TextView SwitchMinute = (TextView)findViewById(R.id.SwitchMinute);
        final Switch Switch = (Switch)findViewById(R.id.switch1);

        SwitchTotal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Switch.setChecked(false);
            }
        });

        SwitchMinute.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Switch.setChecked(true);
            }
        });

        Switch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                CalculateRecords(b);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onResume()
    {
        super.onResume();

        SyncData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            Intent kek = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(kek);

            return true;
        }
        else
        {
            SyncData();
        }
        return super.onOptionsItemSelected(item);
    }
}
