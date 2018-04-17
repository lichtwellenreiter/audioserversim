package ch.sbb.config;

import ch.sbb.helpers.Helper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Config {

    private ConfigReader configreader;
    private String configPath;
    private Helper helper = new Helper();
    private final String LG4JFILENAME = "log4j2.xml";

    public Config(String configPath) {
        this.configPath = configPath;
    }

    public void readConfig() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        File lg4jc = new File(helper.getJarPath() + this.LG4JFILENAME);

        if (!lg4jc.exists()) {
            this.createDefaultLog4jFile();
        }

        try {
            configreader = mapper.readValue(new File(configPath), ConfigReader.class);
        } catch (FileNotFoundException e) {
            this.createDefaultLogFile();
            this.readConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write the default Config File
     */
    private void createDefaultLogFile() {

        File configfile = new File(helper.getConfigFileWithPath());
        try {
            FileWriter fw = new FileWriter(configfile);
            fw.write("#Configfile for Audioserversimulator\n");
            fw.write("audiofilespath: \\\\bn-infra-03\\CusBas\\System-Wave\t# Share where AudioFragments are stored\n");
            fw.write("audiofilesext: .wav\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t# Extension for the AudioFragments\n");
            fw.write("lsgroupnr: 9690430005001\t\t\t\t\t\t\t\t\t\t\t\t\t\t# LS Group Number\n");
            fw.write("buffersize: 128000\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t# Buffersize for Audioplayer\n");
            fw.write("waitafteraudioout: 10000\t\t\t\t\t\t\t\t\t\t\t\t\t# Waittime in ms\n");
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write default log4j Config
     */
    private void createDefaultLog4jFile() {

        File lg4jproperties = new File(helper.getJarPath() + this.LG4JFILENAME);
        try {
            FileWriter fw = new FileWriter(lg4jproperties);
            fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            fw.write("<Configuration status=\"DEBUG\">\n");
            fw.write("    <Appenders>\n");
            fw.write("        <Console name=\"Console\" target=\"SYSTEM_OUT\">\n");
            fw.write("            <PatternLayout pattern=\"%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n\" />\n");
            fw.write("        </Console>\n");
            fw.write("        <File name=\"MyFile\" fileName=\"logs/app.log\" immediateFlush=\"false\" append=\"false\">\n");
            fw.write("            <PatternLayout pattern=\"%d{yyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n\"/>\n");
            fw.write("        </File>\n");
            fw.write("    </Appenders>\n");
            fw.write("    <Loggers>\n");
            fw.write("          <logger name=\"filelogger\" level=\"error\">\n");
            fw.write("              <appender-ref ref=\"MyFile\"/>\n");
            fw.write("          </logger>\n");
            fw.write("        <Root level=\"debug\">\n");
            fw.write("            <AppenderRef ref=\"Console\" />\n");
            fw.write("            <AppenderRef ref=\"MyFile\"/>\n");
            fw.write("        </Root>\n");
            fw.write("    </Loggers>\n");
            fw.write("</Configuration>\n");
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getAudiofilesPath() {
        return configreader.getAudiofilespath();
    }

    public String getAudiofilesExt() {
        return configreader.getAudiofilesext();
    }

    public long getLsgroupnr() {
        return configreader.getLsgroupnr();
    }

    public int getBuffersize() {
        return configreader.getBuffersize();
    }

    public int getWaitAfterAudioOut() {
        return configreader.getWaitafteraudioout();
    }
}
