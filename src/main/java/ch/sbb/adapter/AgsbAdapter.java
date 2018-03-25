package ch.sbb.adapter;

import ch.sbb.dispatcher.MessageDispatcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;


public class AgsbAdapter {

    final static Logger logger = LogManager.getLogger(AgsbAdapter.class);
    private static ServerSocket echoServer = null;
    private static String line;
    private static BufferedReader br;
    private static PrintStream os;
    private static Socket clientSocket = null;
    private static MessageDispatcher md = new MessageDispatcher();


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
            }

        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
