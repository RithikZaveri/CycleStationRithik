package com.example.cyclestationrithik.Admin.cycle;

public class Cycle
{
    public String cid;
    public String station;
    public String status;
    public int cycleregno;
    public String imageUrl;

    public Cycle()
    {}

    public Cycle(String cid,String station, String status, int cycleregno, String imageUrl)
    {
        this.cid=cid;
        this.station=station;
        this.status=status;
        this.cycleregno=cycleregno;
        this.imageUrl=imageUrl;
    }

    public String toString()
    {
        return "STATION : "+this.station+"  , STATUS : "+this.status;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCycleregno() {
        return cycleregno;
    }

    public void setCycleregno(int cycleregno) {
        this.cycleregno = cycleregno;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
