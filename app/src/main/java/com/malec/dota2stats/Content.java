package com.malec.dota2stats;

import java.util.ArrayList;
import java.util.List;

public class Content
{
    static String PlayerUrl;

    public Content(String playerUrl)
    {
        this.PlayerUrl = playerUrl;
    }

    public static String GetHeroesHTML(int index)
    {
        String PlayerHeroesHTML = null;

        InternetRequest htm = new InternetRequest();

        switch (index)
        {
            case 0: htm.execute(PlayerUrl + "/heroes"); break;
            case 1: htm.execute(PlayerUrl + "/heroes?metric=impact"); break;
            case 2: htm.execute(PlayerUrl + "/heroes?metric=economy"); break;
            case 3: htm.execute(PlayerUrl + "/records"); break;

            default: htm.execute(PlayerUrl + "/heroes");
        }

        try
        {
            PlayerHeroesHTML = htm.get().toString();
        }
        catch (Exception e){ }
        htm.cancel(true);

        return PlayerHeroesHTML;
    }

    public static PlayerData GetPlayerData()
    {
        String html = GetHeroesHTML(0);

        String solo = html.split("<dd class=\\\"rating-expired\\\">")[1].split(" ")[0];
        String party = html.split("<dd class=\\\"rating-expired\\\">")[2].split(" ")[0];
        String wins = html.split("<span class=\\\"wins\\\">")[1].split("<")[0];
        String losses = html.split("<span class=\\\"losses\\\">")[1].split("<")[0];
        String abandons = html.split("<span class=\\\"abandons\\\">")[1].split("<")[0];
        String winrate = html.split("<dt>Record</dt></dl><dl><dd>")[1].split("</dd>")[0];
        String nick = html.split("<div class=\\\"header-content-title\\\"><h1>")[1].split("<small>")[0];

        return new PlayerData(nick, solo, party, wins, losses, abandons, winrate);
    }

    public static Hero GetHero(String heroName)
    {
        //Общаяя страница героя
        String s = GetHeroesHTML(0);
        s = s.split("<td class=\"cell-icon\" data-value=\"" + heroName )[1];

        String Image = "https://www.dotabuff.com" + s.split(" src=\"")[1].split("\"")[0];

        String Matches = s.split("<td data-value=\"")[1].split(">")[1].split("<")[0];

        String Winrate = s.split("<td data-value=\"")[2].split("\">")[1].split("<div")[0];

        String KDA = s.split("<td data-value=\"")[3].split("\">")[1].split("<div")[0];

        String Role;
        try { Role = s.split("<i rel=\"\" title=\"\" class=\"fa fa-role-")[1].split("</i>")[1].split(" <")[0]; }
        catch (Exception e) { Role = "Not analyzed"; }

        String Lane;
        try { Lane = s.split("<i rel=\"\" title=\"\" class=\"fa fa-lane-")[1].split("</i>")[1].split(" <")[0]; }
        catch (Exception e) { Lane = "Not analyzed"; }

        String LastMatch = s.split("<td class=\"cell-xlar")[1].split(",")[1];
        String[] a = LastMatch.split(" ");
        LastMatch = a[1] + " " + a[2] + " " + a[3];

        //Страница импакта героя
        s = GetHeroesHTML(1);
        s = s.split("<td class=\"cell-icon\" data-value=\"" + heroName)[1];

        String Kills = s.split("<td data-value=\"")[2].split("\">")[0];
        String Deaths = s.split("<td data-value=\"")[3].split("\">")[0];
        String Assists = s.split("<td data-value=\"")[4].split("\">")[0];

        //Страница экономики героя
        s = GetHeroesHTML(2);
        s = s.split("<td class=\"cell-icon\" data-value=\"" + heroName)[1];

        String Gold = s.split("<td data-value=\"")[1].split("\">")[0];

        String Exp = s.split("<td data-value=\"")[2].split("\">")[0];

        return new Hero(heroName, Image, Matches, Winrate, KDA, Kills, Deaths, Assists, Role, Lane, Gold, Exp, LastMatch);
    }

    public static List<Hero> GetHeroes(int Count)
    {
        List<Hero> hs = new ArrayList<Hero>();

        String s0 = GetHeroesHTML(0);
        String s1 = GetHeroesHTML(1);
        String s2 = GetHeroesHTML(2);

        for (int i = 1; i < Count; i++)
        {
            //Общаяя страница героя
            String s = s0.split("<td class=\"cell-icon\" data-value=\"")[i];

            String Name = s.split("\"")[0];

            String Image = "https://www.dotabuff.com" + s.split(" src=\"")[1].split("\"")[0];

            String Matches = s.split("<td data-value=\"")[1].split(">")[1].split("<")[0];

            String Winrate = s.split("<td data-value=\"")[2].split("\">")[1].split("<div")[0];

            String KDA = s.split("<td data-value=\"")[3].split("\">")[1].split("<div")[0];

            String Role;
            try { Role = s.split("<i rel=\"\" title=\"\" class=\"fa fa-role-")[1].split("</i>")[1].split(" <")[0]; }
            catch (Exception e) { Role = "No data"; }

            String Lane;
            try { Lane = s.split("<i rel=\"\" title=\"\" class=\"fa fa-lane-")[1].split("</i>")[1].split(" <")[0]; }
            catch (Exception e) { Lane = "No data"; }

            String LastMatch = s.split("<td class=\"cell-xlar")[1].split(",")[1];
            String[] a = LastMatch.split(" ");
            LastMatch = a[1] + " " + a[2] + " " + a[3];

            //Страница импакта героя
            s = s1.split("<td class=\"cell-icon\" data-value=\"" + Name)[1];

            String Kills = s.split("<td data-value=\"")[2].split("\">")[0];
            String Deaths = s.split("<td data-value=\"")[3].split("\">")[0];
            String Assists = s.split("<td data-value=\"")[4].split("\">")[0];

            //Страница экономики героя
            s = s2.split("<td class=\"cell-icon\" data-value=\"" + Name)[1];

            String Gold = s.split("<td data-value=\"")[1].split("\">")[0];

            String Exp = s.split("<td data-value=\"")[2].split("\">")[0];

            Hero h = new Hero(Name, Image, Matches, Winrate, KDA, Kills, Deaths, Assists, Role, Lane, Gold, Exp, LastMatch);
            hs.add(h);
        }

        return hs;
    }

    public static List<Record> GetRecords()
    {
        List<Record> Records = new ArrayList<>();

        String s0 = GetHeroesHTML(3);
        s0 = s0.split("<div class=\\\"player-records\\\">")[1];

        for (int i = 1; i < 15; i++)
        {
            String s = s0.split("<div class=\\\"title\\\">")[i];

            String recordName = s.split("</div>")[0];
            String heroName = s.split("<div class=\\\"hero\\\">")[1].split("</div>")[0];
            heroName = heroName.substring(0, heroName.lastIndexOf(' ') - 1);
            String value = s.split("<div class=\\\"value\\\">")[1].split("</div>")[0];
            String date = s.split("<time datetime")[1].split(",")[1];
            String[] a = date.split(" ");
            date = a[1] + " " + a[2] + " " + a[3];

            Records.add(new Record(recordName, heroName, value, date));
        }

        return Records;
    }

    static List<String> GetHeroesNames()
    {
        List<String> a = new ArrayList<String>();

        a.add("Abaddon");
        a.add("Alchemist");
        a.add("Ancient Apparition");
        a.add("Anti-Mage");
        a.add("Arc Warden");
        a.add("Axe");
        a.add("Bane");
        a.add("Batrider");
        a.add("Beastmaster");
        a.add("Bloodseeker");
        a.add("Bounty Hunter");
        a.add("Brewmaster");
        a.add("Bristleback");
        a.add("Broodmother");
        a.add("Centaur Warrunner");
        a.add("Chaos Knight");
        a.add("Chen");
        a.add("Clinkz");
        a.add("Clockwerk");
        a.add("Crystal Maiden");
        a.add("Dark Seer");
        a.add("Dazzle");
        a.add("Death Prophet");
        a.add("Disruptor");
        a.add("Doom");
        a.add("Dragon Knight");
        a.add("Drow Ranger");
        a.add("Earth Spirit");
        a.add("Earthshaker");
        a.add("Elder Titan");
        a.add("Ember Spirit");
        a.add("Enchantress");
        a.add("Enigma");
        a.add("Faceless Void");
        a.add("Gyrocopter");
        a.add("Huskar");
        a.add("Invoker");
        a.add("Io");
        a.add("Jakiro");
        a.add("Juggernaut");
        a.add("Keeper of the Light");
        a.add("Kunkka");
        a.add("Legion Commander");
        a.add("Leshrac");
        a.add("Lich");
        a.add("Lifestealer");
        a.add("Lina");
        a.add("Lion");
        a.add("Lone Druid");
        a.add("Luna");
        a.add("Lycan");
        a.add("Magnus");
        a.add("Medusa");
        a.add("Meepo");
        a.add("Mirana");
        a.add("Monkey King");
        a.add("Morphling");
        a.add("Naga Siren");
        a.add("Nature's Prophet");
        a.add("Necrophos");
        a.add("Night Stalker");
        a.add("Nyx Assassin");
        a.add("Ogre Magi");
        a.add("Omniknight");
        a.add("Oracle");
        a.add("Outworld Devourer");
        a.add("Phantom Assassin");
        a.add("Phantom Lancer");
        a.add("Phoenix");
        a.add("Puck");
        a.add("Pudge");
        a.add("Pugna");
        a.add("Queen of Pain");
        a.add("Razor");
        a.add("Riki");
        a.add("Rubick");
        a.add("Sand King");
        a.add("Shadow Demon");
        a.add("Shadow Fiend");
        a.add("Shadow Shaman");
        a.add("Silencer");
        a.add("Skywrath Mage");
        a.add("Slardar");
        a.add("Slark");
        a.add("Sniper");
        a.add("Spectre");
        a.add("Spirit Breaker");
        a.add("Storm Spirit");
        a.add("Sven");
        a.add("Techies");
        a.add("Templar Assassin");
        a.add("Terrorblade");
        a.add("Tidehunter");
        a.add("Timbersaw");
        a.add("Tinker");
        a.add("Tiny");
        a.add("Treant Protector");
        a.add("Troll Warlord");
        a.add("Tusk");
        a.add("Underlord");
        a.add("Undying");
        a.add("Ursa");
        a.add("Vengeful Spirit");
        a.add("Venomancer");
        a.add("Viper");
        a.add("Visage");
        a.add("Warlock");
        a.add("Weaver");
        a.add("Windranger");
        a.add("Winter Wyvern");
        a.add("Witch Doctor");
        a.add("Wraith King");
        a.add("Zeus");

        return a;
    }

    static int GetHeroImage(String heroName)
    {
        switch (heroName)
        {
            case "Abaddon": return R.drawable.abaddon;
            case "Alchemist": return R.drawable.alchemist;
            case "Ancient Apparition": return R.drawable.ancient_apparition;
            case "Anti-Mage": return R.drawable.anti_mage;
            case "Arc Warden": return R.drawable.arc_warden;
            case "Axe": return R.drawable.axe;
            case "Bane": return R.drawable.bane;
            case "Batrider": return R.drawable.batrider;
            case "Beastmaster": return R.drawable.beastmaster;
            case "Bloodseeker": return R.drawable.bloodseeker;
            case "Bounty Hunter": return R.drawable.bounty_hunter;
            case "Brewmaster": return R.drawable.brewmaster;
            case "Bristleback": return R.drawable.bristleback;
            case "Broodmother": return R.drawable.broodmother;
            case "Centaur Warrunner": return R.drawable.centaur_warrunner;
            case "Chaos Knight": return R.drawable.chaos_knight;
            case "Chen": return R.drawable.chen;
            case "Clinkz": return R.drawable.clinkz;
            case "Clockwerk": return R.drawable.clockwerk;
            case "Crystal Maiden": return R.drawable.crystal_maiden;
            case "Dark Seer": return R.drawable.dark_seer;
            case "Dazzle": return R.drawable.dazzle;
            case "Death Prophet": return R.drawable.death_prophet;
            case "Disruptor": return R.drawable.disruptor;
            case "Doom": return R.drawable.doom;
            case "Dragon Knight": return R.drawable.dragon_knight;
            case "Drow Ranger": return R.drawable.drow_ranger;
            case "Earth Spirit": return R.drawable.earth_spirit;
            case "Earthshaker": return R.drawable.earthshaker;
            case "Elder Titan": return R.drawable.elder_titan;
            case "Ember Spirit": return R.drawable.ember_spirit;
            case "Enchantress": return R.drawable.enchantress;
            case "Enigma": return R.drawable.enigma;
            case "Faceless Void": return R.drawable.faceless_void;
            case "Gyrocopter": return R.drawable.gyrocopter;
            case "Huskar": return R.drawable.huskar;
            case "Invoker": return R.drawable.invoker;
            case "Io": return R.drawable.io;
            case "Jakiro": return R.drawable.jakiro;
            case "Juggernaut": return R.drawable.juggernaut;
            case "Keeper of the Light": return R.drawable.keeper_of_the_light;
            case "Kunkka": return R.drawable.kunkka;
            case "Legion Commander": return R.drawable.legion_commander;
            case "Leshrac": return R.drawable.leshrac;
            case "Lich": return R.drawable.lich;
            case "Lifestealer": return R.drawable.lifestealer;
            case "Lina": return R.drawable.lina;
            case "Lion": return R.drawable.lion;
            case "Lone Druid": return R.drawable.lone_druid;
            case "Luna": return R.drawable.luna;
            case "Lycan": return R.drawable.lycan;
            case "Magnus": return R.drawable.magnus;
            case "Medusa": return R.drawable.medusa;
            case "Meepo": return R.drawable.meepo;
            case "Mirana": return R.drawable.mirana;
            case "Monkey King": return R.drawable.monkey_king;
            case "Morphling": return R.drawable.morphling;
            case "Naga Siren": return R.drawable.naga_siren;
            case "Nature's Prophet": return R.drawable.natures_prophet;
            case "Necrophos": return R.drawable.necrophos;
            case "Night Stalker": return R.drawable.night_stalker;
            case "Nyx Assassin": return R.drawable.nyx_assassin;
            case "Ogre Magi": return R.drawable.ogre_magi;
            case "Omniknight": return R.drawable.omniknight;
            case "Oracle": return R.drawable.oracle;
            case "Outworld Devourer": return R.drawable.outworld_devourer;
            case "Phantom Assassin": return R.drawable.phantom_assassin;
            case "Phantom Lancer": return R.drawable.phantom_lancer;
            case "Phoenix": return R.drawable.phoenix;
            case "Puck": return R.drawable.puck;
            case "Pudge": return R.drawable.pudge;
            case "Pugna": return R.drawable.pugna;
            case "Queen of Pain": return R.drawable.queen_of_pain;
            case "Razor": return R.drawable.razor;
            case "Riki": return R.drawable.riki;
            case "Rubick": return R.drawable.rubick;
            case "Sand King": return R.drawable.sand_king;
            case "Shadow Demon": return R.drawable.shadow_demon;
            case "Shadow Fiend": return R.drawable.shadow_fiend;
            case "Shadow Shaman": return R.drawable.shadow_shaman;
            case "Silencer": return R.drawable.silencer;
            case "Skywrath Mage": return R.drawable.skywrath_mage;
            case "Slardar": return R.drawable.slardar;
            case "Slark": return R.drawable.slark;
            case "Sniper": return R.drawable.sniper;
            case "Spectre": return R.drawable.spectre;
            case "Spirit Breaker": return R.drawable.spirit_breaker;
            case "Storm Spirit": return R.drawable.storm_spirit;
            case "Sven": return R.drawable.sven;
            case "Techies": return R.drawable.techies;
            case "Templar Assassin": return R.drawable.templar_assassin;
            case "Terrorblade": return R.drawable.terrorblade;
            case "Tidehunter": return R.drawable.tidehunter;
            case "Timbersaw": return R.drawable.timbersaw;
            case "Tinker": return R.drawable.tinker;
            case "Tiny": return R.drawable.tiny;
            case "Treant Protector": return R.drawable.treant_protector;
            case "Troll Warlord": return R.drawable.troll_warlord;
            case "Tusk": return R.drawable.tusk;
            case "Underlord": return R.drawable.underlord;
            case "Undying": return R.drawable.undying;
            case "Ursa": return R.drawable.ursa;
            case "Vengeful Spirit": return R.drawable.vengeful_spirit;
            case "Venomancer": return R.drawable.venomancer;
            case "Viper": return R.drawable.viper;
            case "Visage": return R.drawable.visage;
            case "Warlock": return R.drawable.warlock;
            case "Weaver": return R.drawable.weaver;
            case "Windranger": return R.drawable.windranger;
            case "Winter Wyvern": return R.drawable.winter_wyvern;
            case "Witch Doctor": return R.drawable.witch_doctor;
            case "Wraith King": return R.drawable.wraith_king;
            case "Zeus": return R.drawable.zeus;

            default: return R.drawable.abaddon;
        }
    }
}
