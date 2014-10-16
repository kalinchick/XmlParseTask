package com.company;

/**
 * Created by dima on 15.10.14.
 */
public class Artist {
    private String name;


    Artist(String name)
    {
        this.name = name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getName ()
    {
        return this.name;
    }
}
