package ch.sbb;

import ch.sbb.adapter.AgsbAdapter;
import ch.sbb.config.Config;
import ch.sbb.dispatcher.AudioOut;
import ch.sbb.helpers.Helper;
import ch.sbb.player.AudioPlayer;
import ch.sbb.ui.AppStarter;
import org.apache.commons.cli.*;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static sun.jvm.hotspot.runtime.PerfMemory.start;
import static sun.misc.PostVMInitHook.run;

/**
 * jdk1.8.0_171.jdk
 */

public class AudioServerSim {

    private static Helper helper = new Helper();
    private Config config;

    public static void main(final String[] args) throws ParseException {

        AudioServerSim assm = new AudioServerSim();
        Options options = new Options();
        options.addOption("c", true, "path to configfile");
        options.addOption("ui",  false, "start application ui");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        assm.config = new Config(helper.getConfigFileWithPath());
        assm.config.readConfig();
        helper.printHead();
        /**************************************
         * Declaring Queues for Communication between Threads
         **************************************/
        final BlockingQueue<String> agsboutqueue = new ArrayBlockingQueue<String>(100);             // Queue Back to AGSB
        final BlockingQueue<AudioOut> audioplayerqueue = new ArrayBlockingQueue<AudioOut>(100);     // Queue to player

        new Thread() {

            public void run() {
                currentThread().setName("AGSBAdapter");
                AgsbAdapter.main(args, agsboutqueue, audioplayerqueue);
            }
        }.start();

        new Thread() {
            public void run() {
                currentThread().setName("AudioPlayer");
                AudioPlayer.main(args, agsboutqueue, audioplayerqueue);
            }
        }.start();

        if( cmd.hasOption("ui") ){
            new Thread() {

                public void run() {
                    currentThread().setName("AudioServerUI");
                    this.setName("AudioServerUI");
                    AppStarter.main(args, agsboutqueue, audioplayerqueue);
                }
            }.start();
        }


    }
}
