package ch.sbb.dispatcher;

public class AudioFile {
    private int pause;
    private int pathid;
    private String filename;

    public int getPause() {
        return pause;
    }

    public void setPause(int pause) {
        this.pause = pause;
    }

    public int getPathid() {
        return pathid;
    }

    public void setPathid(int pathid) {
        this.pathid = pathid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
