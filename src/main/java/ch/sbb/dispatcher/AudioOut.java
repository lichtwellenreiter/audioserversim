package ch.sbb.dispatcher;

import java.util.ArrayList;
import java.util.List;

public class AudioOut {

    private long handle;
    private int priority;
    private int timeout;
    public List<AudioFile> audiofilelist = new ArrayList<AudioFile>();
    public List<Long> speakerlist = new ArrayList<Long>();


    public long getHandle() {
        return handle;
    }

    public void setHandle(long handle) {
        this.handle = handle;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public List<AudioFile> getAudiofilelist() {
        return audiofilelist;
    }

    public List<Long> getSpeakerlist() {
        return speakerlist;
    }
}
