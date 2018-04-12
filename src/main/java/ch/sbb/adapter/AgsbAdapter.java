package ch.sbb.adapter;

import ch.sbb.dispatcher.MessageDispatcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class AgsbAdapter {

    final static Logger logger = LogManager.getLogger(AgsbAdapter.class);
    private static ServerSocket echoServer = null;
    private static String line;
    private static BufferedReader br;
    private static PrintStream os;
    private static Socket clientSocket = null;
    private static MessageDispatcher md = new MessageDispatcher();
    public final static BlockingQueue<String> handlequeue = new ArrayBlockingQueue<String>(100);


    public static void main(String[] args) {
        logger.info("AGSB Adapter started");

        try {
            echoServer = new ServerSocket(8001);
        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            clientSocket = echoServer.accept();
            br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os = new PrintStream(clientSocket.getOutputStream());


            while (true) {
                line = br.readLine();
                md.enqueueMessage(line);

                if( handlequeue.size() < 0 ){

                }

            }

        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
