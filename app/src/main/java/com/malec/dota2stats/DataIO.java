package com.malec.dota2stats;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.malec.dota2stats.MainActivity.c;

public class DataIO
{
    public static String FileName;

    public DataIO (String fileName)
    {
        this.FileName = fileName;
    }

    public void Write(List<Hero> heroes, Context c)
    {
        try
        {
            FileOutputStream outputStream;
            outputStream = c.openFileOutput(FileName, Context.MODE_PRIVATE);
            for (int i = 0; i < heroes.size(); i++)
            {
                String s = heroes.get(i).Name + "♀" + heroes.get(i).Image + "♀" + heroes.get(i).Matches + "♀" + heroes.get(i).Winrate + "♀" + heroes.get(i).KDA;
                s += "♀" + heroes.get(i).Kils + "♀" + heroes.get(i).Deaths + "♀" + heroes.get(i).Assists + "♀" + heroes.get(i).Role + "♀" + heroes.get(i).Lane;
                s += "♀" + heroes.get(i).Gold + "♀" + heroes.get(i).Exp + "♀" + heroes.get(i).LastMatch + "▲";
                outputStream.write(s.getBytes());
            }
            outputStream.close();
        }
        catch (Exception e) { }
    }

    public static List<Hero> Read(Context c)
    {
        List<Hero> h = new ArrayList<>();
        
        try
        {
            FileInputStream stream = c.openFileInput(FileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

            List<String> s = new ArrayList<>();

            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                String[] heroes = line.split("▲");

                for (int i = 0; i < heroes.length; i++)
                {
                    String[] a = heroes[i].split("♀");

                    Hero her = new Hero(a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], a[8], a[9], a[10], a[11], a[12]);

                    h.add(her);
                }
            }
        }
        catch (Exception e) { }

        return h;
    }
}
