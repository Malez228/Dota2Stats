package com.malec.dota2stats;

class Record
{
    String RecordName, HeroName, Value, Date;

    public Record (String recordName, String heroName, String value, String date)
    {
        this.RecordName = recordName;
        this.HeroName = heroName;
        this.Value = value;
        this.Date = date;
    }
}
