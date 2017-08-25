package com.malec.dota2stats;

public class PlayerData
{
    String PlayerName, SoloMMR, PartyMMR, Wins, Losses, Abandons, Winrate;

    String PlayerImage, PlayerLastMatch, PlayerID;

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

    public PlayerData(String playerName, String playerImage, String playerLastMatch, String playerID)
    {
        this.PlayerName = playerName;
        this.PlayerImage = playerImage;
        this.PlayerLastMatch = playerLastMatch;
        this.PlayerID = playerID;
    }
}
