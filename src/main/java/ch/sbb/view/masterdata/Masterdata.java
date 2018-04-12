package ch.sbb.view.masterdata;

import ch.sbb.view.masterdata.entity.LSGroup;
import ch.sbb.view.masterdata.entity.Station;
import ch.sbb.view.masterdata.entity.Track;

import java.util.ArrayList;
import java.util.List;

public class Masterdata {

    private List<Station> stationlist = new ArrayList<Station>();
    private List<LSGroup> lsgrouplist = new ArrayList<LSGroup>();
    private List<Track> tracklist = new ArrayList<Track>();

    public List<Station> getStationlist() {
        return stationlist;
    }

    public List<LSGroup> getLsgrouplist() {
        return lsgrouplist;
    }

    public List<Track> getTracklist() {
        return tracklist;
    }


}
