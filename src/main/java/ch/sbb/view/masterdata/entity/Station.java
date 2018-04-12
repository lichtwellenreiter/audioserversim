package ch.sbb.view.masterdata.entity;

public class Station {
    private int stationid;
    private int uic;
    private String shortname;
    private String region;

    public int getStationid() {
        return stationid;
    }

    public void setStationid(int stationid) {
        this.stationid = stationid;
    }

    public int getUic() {
        return uic;
    }

    public void setUic(int uic) {
        this.uic = uic;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
