package ch.sbb.view.masterdata.entity;

public class Track {

    private String trackid;
    private int stationfk;
    private String uistring;

    public String getTrackid() {
        return trackid;
    }

    public void setTrackid(String trackid) {
        this.trackid = trackid;
    }

    public int getStationfk() {
        return stationfk;
    }

    public void setStationfk(int stationfk) {
        this.stationfk = stationfk;
    }

    public String getUistring() {
        return uistring;
    }

    public void setUistring(String uistring) {
        this.uistring = uistring;
    }
}
