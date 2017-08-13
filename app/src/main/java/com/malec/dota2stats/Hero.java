package com.malec.dota2stats;

public class Hero
{
    public String Name, Matches, Winrate, KDA, Image;

    public String Role, Lane;

    public String Kils, Deaths, Assists;

    public Hero (String name, String image, String kda, String kills, String deaths, String assists)
    {
        this.Image = image;
        this.Name = name;
        this.KDA = kda;
        this.Kils = kills;
        this.Deaths = deaths;
        this.Assists = assists;
    }

    public Hero (String name, String matches, String winrate, String kda, String image)
    {
        this.Image = image;
        this.Name = name;
        this.Matches = matches;
        this.Winrate = winrate;
        this.KDA = kda;
    }

    public Hero (String name, String matches, String winrate, String kda, String image, String role, String lane)
    {
        this.Image = image;
        this.Name = name;
        this.Matches = matches;
        this.Winrate = winrate;
        this.KDA = kda;
        this.Role = role;
        this.Lane = lane;
    }
}
