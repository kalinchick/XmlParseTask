package com.company;

import com.company.Event;
import com.company.Artist;
import com.company.Venue;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.print.attribute.DateTimeSyntax;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.xml.sax.SAXException;


/**
 * Created by dima on 15.10.14.
 */
public class XmlParser {
    List eventList;
    Document dom;

    public XmlParser()
    {
        eventList = new ArrayList();
    }

    private void parseXmlFile()
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try
        {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.parse("events.xml");
        }
        catch(ParserConfigurationException pce)
        {
            pce.printStackTrace();
        }
        catch(SAXException se)
        {
            se.printStackTrace();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    private void parseDocument()
    {
        Element docElement = dom.getDocumentElement();
        NodeList eventNodeList = docElement.getElementsByTagName("event");
        if( eventNodeList != null && eventNodeList.getLength() > 0)
        {
            for(int i = 0; i < eventNodeList.getLength(); i++)
            {
                Element eventElement = (Element)eventNodeList.item(i);
                Event event = getEvent(eventElement);
                event.artist = addArtistTag(eventElement, "artist");
                event.venue = addVenueTag(eventElement, "venue");
                eventList.add(event);
            }
        }
    }

    private Artist addArtistTag(Element eventElement, String tagName)
    {
        Artist artist = null;
        NodeList nodeList = eventElement.getElementsByTagName(tagName);
        for(int i = 0; i < nodeList.getLength(); i++)
        {
            Element artistElement = (Element)nodeList.item(i);
            artist = getArtist(artistElement);
        }
        return artist;
    }

    private Venue addVenueTag(Element eventElement, String tagName)
    {
        Venue venue = null;
        NodeList nodeList = eventElement.getElementsByTagName(tagName);
        for(int i = 0; i < nodeList.getLength(); i++)
        {
            Element artistElement = (Element)nodeList.item(i);
            venue = getVenue(artistElement);
        }
        return venue;
    }

    private Event getEvent(Element eventElement)
    {
        Date dateTime = getDateValue(eventElement, "datetime");
        //Date dateTime = new Date();
        Event event = new Event(dateTime);
        return event;
    }

    private Artist getArtist(Element artistElement)
    {
        String artistName = getTextValue(artistElement, "name");
        Artist artist = new Artist(artistName);
        return artist;
    }

    private Venue getVenue(Element venuElement)
    {
        String url = getTextValue(venuElement, "url");
        String venueName = getTextValue(venuElement, "name");
        String city = getTextValue(venuElement, "city");
        String country = getTextValue(venuElement, "country");
        Venue venue = new Venue(url, venueName, city, country);
        return venue;
    }


    private String getTextValue(Element eventElement, String tagName)
    {
        String textValue = null;
        NodeList nodeList = eventElement.getElementsByTagName(tagName);
        if(nodeList != null && nodeList.getLength() > 0)
        {
            Element element = (Element)nodeList.item(0);
            textValue = element.getFirstChild().getNodeValue();
        }

        return textValue;
    }

    private Date getDateValue(Element eventElement, String tagName)
    {
        String stringDate  = getTextValue(eventElement,tagName);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = new Date();

        try
        {
            date = sf.parse(stringDate);
        }
        catch (ParseException ex)
        {
            System.out.println("Exception "+ex);
        }

        return date;
    }

    private void printData()
    {
        System.out.println("Number of events " + eventList.size() + ".");

        Iterator iterator = eventList.iterator();
        while(iterator.hasNext())
        {
            System.out.println(iterator.next().toString());
        }
    }

    public void run() {

        //parse the xml file and get the dom object
        parseXmlFile();

        //get each employee element and create a Employee object
        parseDocument();

        //Iterate through the list and print the data
        printData();

    }
}
