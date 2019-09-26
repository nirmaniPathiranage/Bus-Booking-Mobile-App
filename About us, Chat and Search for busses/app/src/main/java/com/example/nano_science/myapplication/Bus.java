package com.example.nano_science.myapplication;

public class Bus {

    private int busid;
    private String source, destination, date, time, facilities, routenumber, travelclass, expressway, bustype, duration, price;
    private int busimage, facilityimage;

    public Bus(int busid, String source, String destination, String date, String time, String facilities, String routenumber, String travelclass, String expressway, String bustype, String duration, String price, int busimage, int facilityimage) {
        this.busid = busid;
        this.source = source;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.facilities = facilities;
        this.routenumber = routenumber;
        this.travelclass = travelclass;
        this.expressway = expressway;
        this.bustype = bustype;
        this.duration = duration;
        this.price = price;
        this.busimage = busimage;
        this.facilityimage = facilityimage;
    }

    public int getBusid() {
        return busid;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getFacilities() {
        return facilities;
    }

    public String getRoutenumber() {
        return routenumber;
    }

    public String getTravelclass() {
        return travelclass;
    }

    public String getExpressway() {
        return expressway;
    }

    public String getBustype() {
        return bustype;
    }

    public String getDuration() {
        return duration;
    }

    public String getPrice() {
        return price;
    }

    public int getBusimage() {
        return busimage;
    }

    public int getFacilityimage() {
        return facilityimage;
    }
}