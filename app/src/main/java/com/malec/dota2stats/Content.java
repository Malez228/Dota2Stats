package com.malec.dota2stats;

import java.security.PublicKey;
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

    static List<Hero> GetHeroes(int Count)
    {
        List<Hero> hs = new ArrayList<Hero>();

        for (int i = 1; i < Count; i++)
        {
            //Общаяя страница героя
            String s = GetHeroesHTML(0);
            s = s.split("<td class=\"cell-icon\" data-value=\"")[i];

            String Name = s.split("\"")[0];

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
            s = s.split("<td class=\"cell-icon\" data-value=\"")[i];

            String Kills = s.split("<td data-value=\"")[2].split("\">")[0];
            String Deaths = s.split("<td data-value=\"")[3].split("\">")[0];
            String Assists = s.split("<td data-value=\"")[4].split("\">")[0];

            //Страница экономики героя
            s = GetHeroesHTML(2);
            s = s.split("<td class=\"cell-icon\" data-value=\"")[i];

            String Gold = s.split("<td data-value=\"")[1].split("\">")[0];

            String Exp = s.split("<td data-value=\"")[2].split("\">")[0];

            Hero h = new Hero(Name, Image, Matches, Winrate, KDA, Kills, Deaths, Assists, Role, Lane, Gold, Exp, LastMatch);
            hs.add(h);
        }

        return hs;
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
}
