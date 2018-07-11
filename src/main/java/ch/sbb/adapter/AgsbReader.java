package ch.sbb.adapter;

import ch.sbb.config.Config;
import ch.sbb.dispatcher.AudioOut;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class AgsbReader extends AgsbAdapter{

    /*private Socket clientSocket = super.serverSocket.accept();
    private BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    private PrintStream os = new PrintStream(clientSocket.getOutputStream());
    private PrintWriter pw = new PrintWriter(os);
*/

   public AgsbReader(Config config, BlockingQueue<String> handlequeue, BlockingQueue<AudioOut> audioplayerqueue){
       super(config, handlequeue, audioplayerqueue);



   }

}
