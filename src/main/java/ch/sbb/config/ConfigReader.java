package ch.sbb.config;

public class ConfigReader {

    private String audiofilespath;
    private String audiofilesext;
    private long lsgroupnr;
    private int buffersize;

    public String getAudiofilespath() {
        return audiofilespath;
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
}
