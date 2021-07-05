package com.example.cyclestationrithik.Admin.station;

public class Station
{
    public String sid;
    public String stationname;
    public double latitude;
    public double longitude;
    public String description;
    public String openingTime;
    public String closingTime;
    public String conductedBy;
    public int noOfcycle;
    public int availableCycle;

    public Station()
    { }

    public Station(String sid,String stationname,double latitude,double longitude,String description,String openingTime,String closingTime,
                   String conductedBy,int noOfcycle,int availableCycle)
    {
        this.sid=sid;
        this.stationname=stationname;
        this.latitude=latitude;
        this.longitude=longitude;
        this.description=description;
        this.openingTime=openingTime;
        this.closingTime=closingTime;
        this.conductedBy=conductedBy;
        this.noOfcycle=noOfcycle;
        this.availableCycle=availableCycle;
    }


    public String toString()
    {
        return " SID : "+sid+" NAME : "+stationname;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public String getConductedBy() {
        return conductedBy;
    }

    public void setConductedBy(String conductedBy) {
        this.conductedBy = conductedBy;
    }

    public int getNoOfcycle() {
        return noOfcycle;
    }

    public void setNoOfcycle(int noOfcycle) {
        this.noOfcycle = noOfcycle;
    }

    public int getAvailableCycle() {
        return availableCycle;
    }

    public void setAvailableCycle(int availableCycle) {
        this.availableCycle = availableCycle;
    }
}
