package com.company;

import com.company.Artist;
import com.company.Venue;

import java.util.Date;

/**
 * Created by dima on 15.10.14.
 */
public class Event {
    private Date dateTime;
    public Artist artist;
    public Venue venue;

    Event(Date dateTime)
    {
        this.dateTime = dateTime;
    }

    public void setDateTime(Date dateTime)
    {
        this.dateTime = dateTime;
    }

    public Date getDateTime()
    {
        return this.dateTime;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("Event Details - ");
        sb.append("Date: " + getDateTime());
        sb.append(", ");
        sb.append("Artist: " + artist.getName());
        sb.append(", ");
        sb.append("Venue url: " + venue.getUrl());
        sb.append(", ");
        sb.append("Venue name: " + venue.getName());
        sb.append(".");
        sb.append("Venue city: " + venue.getCity());
        sb.append(".");
        sb.append("Venue country: " + venue.getCountry());
        sb.append(".");

        return sb.toString();
    }
}
