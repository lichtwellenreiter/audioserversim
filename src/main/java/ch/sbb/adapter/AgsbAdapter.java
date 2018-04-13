package ch.sbb.adapter;

import ch.sbb.dispatcher.MessageDispatcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class AgsbAdapter {

    private final static Logger logger = LogManager.getLogger(AgsbAdapter.class);
    private static ServerSocket echoServer = null;
    private static MessageDispatcher md;
    private static BlockingQueue<String> handlequeue;


    public static void main(String[] args, BlockingQueue<String> queue) {
        logger.info("AGSB Adapter started");
        handlequeue = queue;
        md = new MessageDispatcher(handlequeue);

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
                String line = br.readLine();
                md.enqueueMessage(line);
                if (handlequeue.size() > 0) {
                    pw.write(String.format("<audioreply><handle>%s</handle><returncode>0</returncode></audioreply>", handlequeue.poll()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
