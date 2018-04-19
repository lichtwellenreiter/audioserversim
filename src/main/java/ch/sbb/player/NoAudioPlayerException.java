package ch.sbb.player;

public class NoAudioPlayerException extends Exception {

    public NoAudioPlayerException() {
    }

    public NoAudioPlayerException(String message) {
        super(message);
    }

    public NoAudioPlayerException(Throwable cause) {
        super(cause);
    }

    public NoAudioPlayerException(String message, Throwable cause) {
        super(message, cause);
    }
}
