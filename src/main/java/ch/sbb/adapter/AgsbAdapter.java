package ch.sbb.adapter;

import ch.sbb.dispatcher.AudioOut;
import ch.sbb.dispatcher.MessageDispatcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;

public class AgsbAdapter {

    private final static Logger logger = LogManager.getLogger(AgsbAdapter.class);
    private static ServerSocket echoServer = null;
    private static MessageDispatcher md;


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

            while (true) {

                logger.info("AGSBOutQueue Size {}", handlequeue.size());

                if (handlequeue.size() > 0) {
                    String handle = handlequeue.poll();
                    logger.info("Send back {}", handle);
                    pw.write(String.format("<?xml version='1.0'?><audioreply><handle>%s</handle><returncode>0</returncode></audioreply>", handle));
                } else {
                    String line = br.readLine();
                    md.enqueueMessage(line);

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
