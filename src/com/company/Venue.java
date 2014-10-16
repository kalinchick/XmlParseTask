package com.company;

/**
 * Created by dima on 15.10.14.
 */
public class Venue {
    private String url;
    private String name;
    private String city;
    private String country;


    Venue(String url, String name, String city, String country)
    {
        this.url = url;
        this.name = name;
        this.city = city;
        this.country = country;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return this.url;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCity()
    {
        return this.city;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCountry()
    {
        return this.country;
    }
}
