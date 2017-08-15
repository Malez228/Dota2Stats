package com.malec.dota2stats;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    public static TabHost tabHost;
    public static TabHost.TabSpec tabStats, tabHeroes, tabItems;

    public static TextView MmrSolo, MmrParty, WinGames, LoseGames, AbandonGames, Winrate;
    public static TextView HeroName, HeroMatches, HeroWinrate, HeroKDA;
    public static TextView sHeroName, sHeroMatches, sHeroWinrate, sHeroKDA, sHeroRole, sHeroLane;
    public static TextView sHeroKills, sHeroDeaths, sHeroAssists, sHeroGold, sHeroExp, sHeroLastMatch;
    public static ImageView HeroImage;
    public static ImageView sHeroImage;

    public static Spinner HeroesList;

    public static ConstraintLayout Hero;

    public static String PlayerUrl = "https://www.dotabuff.com/players/123878279";
    public static String PlayerOverviewHTML, PlayerHeroesHTML, PlayerGameImpactHTML, PlayerEconomyHTML;
    public static String PlayerNickname;

    public static Content c = new Content(PlayerUrl);

    public static String SelectedHero = "Abaddon";

    public static Boolean NewDay = true;

    public static DataIO file;

        /* TODO
        * (готово) допилить что осталось в вкладку герои ( средн. голд экспа) мб еще что найти и запилить
        * вкладка итемов все еще пустая - это следущее пойдет
        * можно авторизацию еще добавить но хз для себя любимого же пишу
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

        tabItems = tabHost.newTabSpec("tag3");
        tabItems.setContent(R.id.tabItems);
        tabItems.setIndicator("Items");
        tabHost.addTab(tabItems);

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
        try
        {
            String s = Content.GetHeroesHTML(0);
            String mmrSolo = HeaderContent.GetSoloMmr(s);
            String mmrParty = HeaderContent.GetPartyMmr(s);
            String winGames = HeaderContent.GetWinGames(s);
            String loseGames = HeaderContent.GetLoseGames(s);
            String adandonGames = HeaderContent.GetAbandonGames(s);
            String percent = HeaderContent.GetPercent(s);

            PlayerNickname = HeaderContent.GetPlayerNickname(s);

            MmrSolo.setText("Solo MMR:  " + mmrSolo);
            MmrParty.setText("Party MMR:  " + mmrParty);
            WinGames.setText(winGames + " W");
            LoseGames.setText(loseGames + " L");
            AbandonGames.setText(adandonGames + " A");
            Winrate.setText(percent);
        }
        catch (Exception e) { }
    }

    void CalculateContent()
    {
        try
        {
            List<Hero> heroes = file.Read(getApplicationContext());

            for (int i = 0; i < heroes.size(); i++)
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

                Hero h = heroes.get(i);
                String heroimage = h.Image;
                String heroname = h.Name;
                String heromatches = h.Matches;
                String herowinrate = h.Winrate;
                String herokda = h.KDA;

                new DownloadImageTask(HeroImage).execute(heroimage);
                HeroName.setText(heroname + "   ");
                HeroMatches.setText(heromatches + " M   ");
                HeroWinrate.setText(herowinrate + "   ");
                HeroKDA.setText(herokda + " KDA   ");
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
        Hero h = c.GetHero(SelectedHero);

        new DownloadImageTask(sHeroImage).execute(h.Image);
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Selected hero").setMessage("Unplayed hero - " + SelectedHero).setCancelable(false).setNegativeButton(getResources().getString(R.string.Ok), new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                    parent.setSelection(parent.getSelectedItemPosition() + 1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    void LoadXML()
    {
        List<Hero> Heroes = c.GetHeroes(11);

        file.Write(Heroes, getApplicationContext());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        long date = System.currentTimeMillis();

        //TODO
        //Если дата с прошлого старта отличается нужно заново загрузить данные
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        //String dateString = sdf.format(date).split("T")[1];

        InitializeComponents();

        toolbar.setTitle("Dota2 Stats  -  " + PlayerNickname);
        setSupportActionBar(toolbar);

        if (NewDay)
        {
            file = new DataIO("test.txt");
            LoadXML();

            CalculateHeader();
            CalculateContent();

            InitializeHeroesSpinner();
        }
        else
        {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            setContentView(R.layout.settings);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
