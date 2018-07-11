package ch.sbb.config;

public class ConfigReader {

    private String audiofilespath;
    private String flexpath;
    private String audiofilesext;
    private long lsgroupnr;
    private int buffersize;
    private int waitafteraudioout;
    private int agsboutqueuedepth;
    private int audioplayerqueuedepth;
    private int serverport;

    public String getAudiofilespath() {
        return audiofilespath;
    }

    public String getFlexpath() {
        return flexpath;
    }

    public String getAudiofilesext() {
        return audiofilesext;
    }

    public long getLsgroupnr() {
        return lsgroupnr;
    }

    public int getBuffersize() {
        return buffersize;
    }

    public int getWaitafteraudioout() {
        return waitafteraudioout;
    }

    public int getAgsboutqueuedepth() {
        return agsboutqueuedepth;
    }

    public int getAudioplayerqueuedepth() {
        return audioplayerqueuedepth;
    }

    public int getServerport() {
        return serverport;
    }
}
