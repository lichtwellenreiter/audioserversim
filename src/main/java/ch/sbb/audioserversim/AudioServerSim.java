package ch.sbb.audioserversim;

import ch.sbb.adapter.AgsbAdapter;
import ch.sbb.config.Config;
import ch.sbb.helpers.Helper;
import ch.sbb.ui.AudioServerSimApp;
import org.apache.commons.cli.*;
import ch.sbb.player.AudioPlayer;

public class AudioServerSim {

    private Config config;
    private static Helper helper = new Helper();

    public static void main(final String[] args) throws ParseException {

        AudioServerSim assm = new AudioServerSim();
        Options options = new Options();
        options.addOption("c", true, "path to configfile");
        options.addOption("w", "window",false, "load ui");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        assm.config = new Config(helper.getConfigFileWithPath());
        assm.config.readConfig();
        helper.printHead();

        new Thread() {

            public void run() {
                currentThread().setName("AGSBAdapter");
                AgsbAdapter.main(args);
            }
        }.start();

        new Thread() {
            public void run() {
                currentThread().setName("AudioPlayer");
                AudioPlayer.main(args);
            }
        }.start();

        if( cmd.hasOption("w") ){
            new Thread(){
                public void run(){
                    currentThread().setName("AudioServerSimUI");
                    AudioServerSimApp.main(args);
                }
            }.start();
        }


    }
}
