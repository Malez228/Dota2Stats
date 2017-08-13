package com.malec.dota2stats;

public class HeaderContent
{
    static String GetSoloMmr(String html)
    {
        String mmrSolo;
        mmrSolo = html.split("<div class=\\\"header-content-secondary\\\">")[1];
        mmrSolo = mmrSolo.split("<dd class=\\\"rating-expired\\\">")[1];
        return mmrSolo.split(" ")[0];
    }

    static String GetPartyMmr(String html)
    {
        String mmrParty;
        mmrParty = html.split("<div class=\\\"header-content-secondary\\\">")[1];
        mmrParty = mmrParty.split("<dd class=\\\"rating-expired\\\">")[2];
        return mmrParty.split(" ")[0];
    }

    static String GetWinGames(String html)
    {
        String Wingames;
        Wingames = html.split("<span class=\\\"game-record\\\">")[1];
        return Wingames.split("<span class=\\\"wins\\\">")[1].split("<")[0];
    }

    static String GetLoseGames(String html)
    {
        String Losegames;
        Losegames = html.split("<span class=\\\"game-record\\\">")[1];
        return Losegames.split("<span class=\\\"losses\\\">")[1].split("<")[0];
    }

    static String GetAbandonGames(String html)
    {
        String AbandonGames;
        AbandonGames = html.split("<span class=\\\"game-record\\\">")[1];
        return AbandonGames.split("<span class=\\\"abandons\\\">")[1].split("<")[0];
    }

    static String GetPercent(String html)
    {
        String Percent;
        Percent = html.split("</dd><dt>Win Rate</dt></dl>")[0];
        return Percent.split("</span></span></dd><dt>Record</dt></dl><dl><dd>")[1];
    }

    static String GetPlayerNickname(String html)
    {
        String nick;
        nick = html.split("<div class=\\\"header-content-title\\\"><h1>")[1];
        return nick.split("<small>")[0];
    }

}
