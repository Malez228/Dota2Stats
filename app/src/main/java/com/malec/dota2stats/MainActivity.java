package com.malec.dota2stats;

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
    private String PlayerNickname;

    public static String SelectedHero = "Abaddon";

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
            String mmrSolo = HeaderContent.GetSoloMmr(PlayerOverviewHTML);
            String mmrParty = HeaderContent.GetPartyMmr(PlayerOverviewHTML);
            String winGames = HeaderContent.GetWinGames(PlayerOverviewHTML);
            String loseGames = HeaderContent.GetLoseGames(PlayerOverviewHTML);
            String adandonGames = HeaderContent.GetAbandonGames(PlayerOverviewHTML);
            String percent = HeaderContent.GetPercent(PlayerOverviewHTML);

            PlayerNickname = HeaderContent.GetPlayerNickname(PlayerOverviewHTML);

            MmrSolo.setText("Solo MMR:  " + mmrSolo);
            MmrParty.setText("Party MMR:  " + mmrParty);
            WinGames.setText(winGames + " W");
            LoseGames.setText(loseGames + " L");
            AbandonGames.setText(adandonGames + " A");
            Winrate.setText(percent);
        }
        catch (Exception e)
        {

        }
    }

    void CalculateContent()
    {
        try
        {
            for (int i = 0; i < Content.GetHeroes(PlayerOverviewHTML).size(); i++)
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

                Hero h = Content.GetHeroes(PlayerOverviewHTML).get(i);
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
                    Hero h = Content.GetHero(PlayerHeroesHTML, SelectedHero);
                    String heroimage = h.Image;
                    String heromatches = h.Matches;
                    String herowinrate = h.Winrate;
                    String herokda = h.KDA;
                    String herorole = h.Role;
                    String herolane = h.Lane;
                    String herolastmatch = h.LastMatch;

                    new DownloadImageTask(sHeroImage).execute(heroimage);
                    sHeroName.setText(SelectedHero);
                    sHeroMatches.setText(heromatches);
                    sHeroWinrate.setText(herowinrate);
                    sHeroKDA.setText(herokda);
                    sHeroRole.setTextColor(GetColor(herorole));
                    sHeroRole.setText(herorole);
                    sHeroLane.setTextColor(GetColor(herolane));
                    sHeroLane.setText(herolane);
                    sHeroLastMatch.setText(herolastmatch);

                    h = Content.GetHeroImpact(PlayerGameImpactHTML, SelectedHero);
                    String herokills = h.Kils;
                    String herodeaths = h.Deaths;
                    String heroassists = h.Assists;

                    sHeroKills.setText(herokills);
                    sHeroDeaths.setText(herodeaths);
                    sHeroAssists.setText(heroassists);

                    h = Content.GetHeroEconomy(PlayerEconomyHTML, SelectedHero);
                    String herogold = h.Kils;
                    String heroexp = h.Deaths;

                    sHeroGold.setText(herogold);
                    sHeroExp.setText(heroexp);
                }
                catch (Exception e)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Selected hero")
                            .setMessage("Unplayed hero - " + SelectedHero)
                            .setCancelable(false)
                            .setNegativeButton(getResources().getString(R.string.Ok),
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                    parent.setSelection(parent.getSelectedItemPosition() + 1);
                }
            }
            /* TODO
            * (готово) допилить что осталось в вкладку герои ( средн. голд экспа) мб еще что найти и запилить
            * вкладка итемов все еще пустая - это следущее пойдет
            * можно авторизацию еще добавить но хз для себя любимого же пишу
            */

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    void LoadHTML()
    {
        InternetRequest htm = new InternetRequest();
        htm.execute(PlayerUrl);
        try
        {
            PlayerOverviewHTML = htm.get().toString();
        }catch (Exception e) { }
        htm.cancel(true);

        htm = new InternetRequest();
        htm.execute(PlayerUrl + "/heroes");
        try
        {
            PlayerHeroesHTML = htm.get().toString();
        }catch (Exception e) { }
        htm.cancel(true);

        htm = new InternetRequest();
        htm.execute(PlayerUrl + "/heroes?metric=impact");
        try
        {
            PlayerGameImpactHTML = htm.get().toString();
        }catch (Exception e) { }
        htm.cancel(true);

        htm = new InternetRequest();
        htm.execute(PlayerUrl + "/heroes?metric=economy");
        try
        {
            PlayerEconomyHTML = htm.get().toString();
        }catch (Exception e) { }
        htm.cancel(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        InitializeComponents();

        LoadHTML();

        CalculateHeader();
        CalculateContent();

        toolbar.setTitle("Dota2 Stats  -  " + PlayerNickname);
        setSupportActionBar(toolbar);

        InitializeHeroesSpinner();
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
