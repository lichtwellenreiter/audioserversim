package ch.sbb.helpers;

import ch.sbb.AudioServerSim;
import ch.sbb.player.AudioPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Helper {

    private final Logger logger = LogManager.getLogger(AudioPlayer.class);
    private final String VERSION = "0.1";



    public String getJarPath() {
        String path = AudioServerSim.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        try {
            return URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getStackTrace());
            System.exit(1);
        }
        return null;
    }

    public String getConfigFileWithPath() {
        return this.getJarPath() + "config.yml";
    }

    /**
     * Print Head in Console
     */
    public void printHead() {
        logger.info("                                                                              ");
        logger.info("   ___            _ _       _____                          _____ _            ");
        logger.info("  / _ \\          | (_)     /  ___|                        /  ___(_)           ");
        logger.info(" / /_\\ \\_   _  __| |_  ___ \\ `--.  ___ _ ____   _____ _ __\\ `--. _ _ __ ___   ");
        logger.info(" |  _  | | | |/ _` | |/ _ \\ `--. \\/ _ \\ '__\\ \\ / / _ \\ '__|`--. \\ | '_ ` _ \\  ");
        logger.info(" | | | | |_| | (_| | | (_) /\\__/ /  __/ |   \\ V /  __/ |  /\\__/ / | | | | | | ");
        logger.info(" \\_| |_/\\__,_|\\__,_|_|\\___/\\____/ \\___|_|    \\_/ \\___|_|  \\____/|_|_| |_| |_| ");
        logger.info("                                                                              ");
        logger.info("==============================================================================");
        logger.info("|                                                                            |");
        logger.info("|                        (c) 2018 Florian Thievent                           |");
        logger.info("|                                                                            |");
        logger.info("==============================================================================");
        logger.info("AudioServerSim "+VERSION+" is starting");
    }

}
