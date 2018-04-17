package ch.sbb.audioserversim;

import ch.sbb.adapter.AgsbAdapter;
import ch.sbb.config.Config;
import ch.sbb.helpers.Helper;
import ch.sbb.player.AudioPlayer;
import ch.sbb.ui.AppStarter;
import org.apache.commons.cli.*;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class AudioServerSim {

    private static Helper helper = new Helper();
    private Config config;

    public static void main(final String[] args) throws ParseException {

        AudioServerSim assm = new AudioServerSim();
        Options options = new Options();
        options.addOption("c", true, "path to configfile");
        options.addOption("w", "window", false, "load ui");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        assm.config = new Config(helper.getConfigFileWithPath());
        assm.config.readConfig();
        helper.printHead();
        /***************************************
         * Declaring Queues for Communication between Threads
         **************************************/
        final BlockingQueue<String> agsboutqueue = new ArrayBlockingQueue<String>(100);

        new Thread() {

            public void run() {
                currentThread().setName("AGSBAdapter");
                AgsbAdapter.main(args, agsboutqueue);
            }
        }.start();

        new Thread() {
            public void run() {
                currentThread().setName("AudioPlayer");
                AudioPlayer.main(args);
            }
        }.start();

        if (cmd.hasOption("w")) {
            new Thread() {
                public void run() {
                    currentThread().setName("AudioServerSimUI");
                    AppStarter.main(args);
                }
            }.start();
        }
    }
}
