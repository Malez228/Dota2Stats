package com.malec.dota2stats;

public class PlayerData
{
    String PlayerName, SoloMMR, PartyMMR, Wins, Losses, Abandons, Winrate;

    public PlayerData(String playerName, String soloMMR, String partyMMR, String wins, String losses, String abandons, String winrate)
    {
        this.PlayerName = playerName;
        this.SoloMMR = soloMMR;
        this.PartyMMR = partyMMR;
        this.Wins = wins;
        this.Losses = losses;
        this.Abandons = abandons;
        this.Winrate = winrate;
    }
}
