package ch.sbb.helpers;

import ch.sbb.audioserversim.AudioServerSim;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Helper {

    public String getJarPath() {
        String path = AudioServerSim.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        try {
            return URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
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
        System.out.println("                                                                              ");
        System.out.println("   ___            _ _       _____                          _____ _            ");
        System.out.println("  / _ \\          | (_)     /  ___|                        /  ___(_)           ");
        System.out.println(" / /_\\ \\_   _  __| |_  ___ \\ `--.  ___ _ ____   _____ _ __\\ `--. _ _ __ ___   ");
        System.out.println(" |  _  | | | |/ _` | |/ _ \\ `--. \\/ _ \\ '__\\ \\ / / _ \\ '__|`--. \\ | '_ ` _ \\  ");
        System.out.println(" | | | | |_| | (_| | | (_) /\\__/ /  __/ |   \\ V /  __/ |  /\\__/ / | | | | | | ");
        System.out.println(" \\_| |_/\\__,_|\\__,_|_|\\___/\\____/ \\___|_|    \\_/ \\___|_|  \\____/|_|_| |_| |_| ");
        System.out.println("                                                                              ");
        System.out.println("==============================================================================");
        System.out.println("|                                                                            |");
        System.out.println("|                        (c) 2018 Florian Thievent                           |");
        System.out.println("|                                                                            |");
        System.out.println("==============================================================================");
    }

}
