package com.malec.dota2stats;

import java.util.ArrayList;
import java.util.List;

public class Content
{
    static String Name, Matches, Winrate, KDA, Image;

    static List<Hero> GetHeroes(String html)
    {
        List<Hero> hs = new ArrayList<Hero>();

        String s = html;
        for (int i = 1; i < 11; i++)
        {

            s = html.split("<div class=\\\"r-row\\\">")[i];

            try
            {
                Name = s.split("</a></div><div class=\\\"r-none-mobile\\\">")[1].split("</a>")[0];
                Name = Name.split(">")[1].replace("&#39;", "'");
            } catch (Exception e)
            {
                Name = "Error";
            }

            try
            {
                Matches = s.split("Matches</div><div class=\\\"r-body\\\">")[1];
                Matches = Matches.split("<")[0];
            } catch (Exception e)
            {
                Matches = "Error";
            }

            try
            {
                Winrate = s.split("Win %</div><div class=\\\"r-body\\\">")[1];
                Winrate = Winrate.split("<")[0];
            } catch (Exception e)
            {
                Winrate = "Error";
            }

            try
            {
                KDA = s.split("KDA</div><div class=\\\"r-body\\\">")[1];
                KDA = KDA.split("<")[0];
            } catch (Exception e)
            {
                KDA = "Error";
            }

            try
            {
                Image = s.split("<img class=\\\"image-hero image-icon\\\"")[1];
                Image = Image.split("src=\"")[1].split("\"")[0];
                Image = "https://www.dotabuff.com" + Image;
            } catch (Exception e)
            {
                //Error image
                Image = "https://images.duckduckgo.com/iu/?u=http%3A%2F%2Ffiles.softicons.com%2Fdownload%2Ftoolbar-icons%2Fmax-mini-icons-by-ashung%2Fpng%2F16x16%2Fcrossout.png&f=1";
            }

            Hero h = new Hero(Name, Matches, Winrate, KDA, Image);
            hs.add(h);
        }

        return hs;
    }

    static  Hero GetHero(String html, String heroName)
    {
        String s = html;
        s = s.split("<td class=\"cell-icon\" data-value=\"" + heroName )[1];

        String Image = s.split(" src=\"")[1].split("\"")[0];
        Image = "https://www.dotabuff.com" + Image;

        String Matches = s.split("<td data-value=\"")[1].split(">")[1].split("<")[0];

        String Winrate = s.split("<td data-value=\"")[2].split("\">")[1].split("<div")[0];

        String KDA = s.split("<td data-value=\"")[3].split("\">")[1].split("<div")[0];

        String Role;
        try
        {
            Role = s.split("<i rel=\"\" title=\"\" class=\"fa fa-role-")[1].split("</i>")[1].split(" <")[0];
        }
        catch (Exception e)
        {
            Role = "Not analyzed";
        }

        String Lane;
        try
        {
            Lane = s.split("<i rel=\"\" title=\"\" class=\"fa fa-lane-")[1].split("</i>")[1].split(" <")[0];
        }
        catch (Exception e)
        {
            Lane = "Not analyzed";
        }

        Hero h = new Hero(heroName, Matches, Winrate, KDA, Image, Role, Lane);

        return h;
    }

    static Hero GetHeroImpact(String html, String heroName)
    {
        String Kills, Deaths, Assists;

        String s = html;
        s = s.split("<td class=\"cell-icon\" data-value=\"" + heroName)[1];

        Kills = s.split("<td data-value=\"")[2].split("\">")[0];

        Deaths = s.split("<td data-value=\"")[3].split("\">")[0];

        Assists = s.split("<td data-value=\"")[4].split("\">")[0];

        Hero h = new Hero(heroName, "", KDA, Kills, Deaths, Assists);

        return h;
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
