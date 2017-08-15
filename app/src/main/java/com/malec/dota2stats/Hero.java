package com.malec.dota2stats;

public class Hero
{
    public String Name, Image, Matches, Winrate, KDA, Kils, Deaths, Assists, Role, Lane, Gold, Exp, LastMatch;

    public Hero (String name, String image, String matches, String winrate, String kda, String kills, String deaths, String assists, String role, String lane, String gold, String exp, String lastmatch)
    {
        this.Name = name;
        this.Image = image;
        this.Matches = matches;
        this.Winrate = winrate;
        this.KDA = kda;
        this.Kils = kills;
        this.Deaths = deaths;
        this.Assists = assists;
        this.Role = role;
        this.Lane = lane;
        this.Gold = gold;
        this.Exp = exp;
        this.LastMatch = lastmatch;
    }
}
