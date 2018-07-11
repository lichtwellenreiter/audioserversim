package ch.sbb.adapter;

import ch.sbb.config.Config;
import ch.sbb.dispatcher.AudioOut;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class AgsbReader extends AgsbAdapter {

    private Socket clientSocket;
    private BufferedReader br;
    private PrintStream os;
    private PrintWriter pw;
    private final Logger logger = LogManager.getLogger(AgsbReader.class);

    public AgsbReader(Config config, BlockingQueue<String> handlequeue, BlockingQueue<AudioOut> audioplayerqueue) {
        super(config, handlequeue, audioplayerqueue);
        initialize();
    }

    private void initialize() {
        try {
            clientSocket = getServerSocket().accept();
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os = new PrintStream(clientSocket.getOutputStream());
            pw = new PrintWriter(os);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void readMessage(){

    }
}
