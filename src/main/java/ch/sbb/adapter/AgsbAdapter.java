package ch.sbb.adapter;

import ch.sbb.config.Config;
import ch.sbb.dispatcher.AudioOut;
import ch.sbb.dispatcher.MessageDispatcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.BlockingQueue;

public class AgsbAdapter {

    private ServerSocket serverSocket = null;
    private Config config;
    private MessageDispatcher messageDispatcher;
    private boolean running = true;
    private final Logger logger = LogManager.getLogger(AgsbAdapter.class);
    private MessageDispatcher md;


    public AgsbAdapter(Config config, BlockingQueue<String> handlequeue, BlockingQueue<AudioOut> audioplayerqueue) {
        this.config = config;
        this.messageDispatcher = new MessageDispatcher(handlequeue, audioplayerqueue);

        try {
            this.serverSocket = new ServerSocket(config.getServerport());
        } catch (IOException e) {
            logger.error(e.getStackTrace());
        }

        md = new MessageDispatcher(handlequeue, audioplayerqueue);

    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public MessageDispatcher getMessageDispatcher() {
        return messageDispatcher;
    }

    /*
    public static void main(String[] args, BlockingQueue<String> handlequeue, BlockingQueue<AudioOut> audioplayerqueue) {
        md = new MessageDispatcher(handlequeue, audioplayerqueue);

        try {
            echoServer = new ServerSocket(8001);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Socket clientSocket = echoServer.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintStream os = new PrintStream(clientSocket.getOutputStream());
            PrintWriter pw = new PrintWriter(os);

            while (running) {

                logger.debug("AGSBOutQueue Size {}", handlequeue.size());

                if (!handlequeue.isEmpty()) {
                    String handle = handlequeue.poll();
                    logger.info("Send back {}", handle);
                    pw.write(String.format("<?xml version='1.0'?><audioreply><handle>%s</handle><returncode>0</returncode></audioreply>", handle));
                } else {
                    String line = br.readLine();
                    md.enqueueMessage(line);
                }

                if( handlequeue.size() < 0){
                    running = false;
                    System.exit(0);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }*/
}
