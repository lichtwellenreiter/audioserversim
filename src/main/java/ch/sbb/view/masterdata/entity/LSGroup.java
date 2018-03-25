package ch.sbb.view.masterdata.entity;

public class LSGroup {

    private int lsgroupid;
    private int number;
    private int station_fk;
    private String uistring;

    public int getLsgroupid() {
        return lsgroupid;
    }

    public void setLsgroupid(int lsgroupid) {
        this.lsgroupid = lsgroupid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getStation_fk() {
        return station_fk;
    }

    public void setStation_fk(int station_fk) {
        this.station_fk = station_fk;
    }

    public String getUistring() {
        return uistring;
    }

    public void setUistring(String uistring) {
        this.uistring = uistring;
    }
}
