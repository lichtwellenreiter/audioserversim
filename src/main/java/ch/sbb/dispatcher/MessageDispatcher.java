package ch.sbb.dispatcher;

import ch.sbb.config.Config;
import ch.sbb.helpers.Helper;
import ch.sbb.player.AudioPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class MessageDispatcher {

    final static Logger logger = LogManager.getLogger(MessageDispatcher.class);
    private final AudioPlayer audioplayer;
    private String audiomessage;
    private Helper helper = new Helper();
    private Config config = new Config(helper.getConfigFileWithPath());
    private BlockingQueue<String> handlequeue;

    public MessageDispatcher(BlockingQueue<String> queue) {
        this.config.readConfig();
        this.handlequeue = queue;
        this.audioplayer = new AudioPlayer(this.handlequeue);
    }

    public void enqueueMessage(String message) {
        this.audiomessage = message;
        logger.info(message);
        this.xmlParser();
    }

    /**
     * Read XML Message if is in SpeakerNr then add it to the audiooutqueue
     */

    private void xmlParser() {

        try {
            AudioOut ao = new AudioOut();
            Speakerlist sl = new Speakerlist();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setValidating(false);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            //Document doc = dBuilder.parse(this.audiomessage);

            Document doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(this.audiomessage.getBytes("utf-8"))));

            //Document doc = dBuilder.parse("D:\\01_Projekte\\AudioServerSim\\src\\main\\resources\\KIBAudioTestInterface\\AudioServer(1322)-KIB ComNode (LS1) AGSB 2 BTA-FRQ\\anschluss_biel.xml");

            doc.getDocumentElement().normalize();
            logger.info("ROOT: " + doc.getDocumentElement().getNodeName());

            if (!doc.getDocumentElement().getNodeName().equals("audiokill") || !doc.getDocumentElement().getNodeName().equals("IF_Heartbeat")) {

                ao.setHandle(Long.parseLong(doc.getElementsByTagName("handle").item(0).getTextContent()));

                ao.setPriority(Integer.parseInt(doc.getElementsByTagName("priority").item(0).getTextContent()));
                ao.setTimeout(Integer.parseInt(doc.getElementsByTagName("timeout").item(0).getTextContent()));
                String[] speakerlist = doc.getElementsByTagName("speakerlist").item(0).getTextContent().split(",");

                for (int d = 0; d < speakerlist.length; d++) {
                    ao.speakerlist.add(Long.parseLong(speakerlist[d]));
                }

                if (ao.speakerlist.contains(config.getLsgroupnr())) {


                    NodeList nList = doc.getElementsByTagName("audiofile");

                    for (int i = 0; i < nList.getLength(); i++) {
                        Node nNode = nList.item(i);
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;
                            AudioFile af = new AudioFile();

                            af.setPause(Integer.parseInt(eElement.getElementsByTagName("pause").item(0).getTextContent()));
                            af.setPathid(Integer.parseInt(eElement.getElementsByTagName("pathid").item(0).getTextContent()));
                            af.setFilename(eElement.getElementsByTagName("filename").item(0).getTextContent());

                            ao.audiofilelist.add(af);
                        }
                    }

                    audioplayer.audioplayerqueue.add(ao);
                    logger.info("added " + ao.getHandle() + " to audiooutqueue");

                } else {
                    logger.info("skipped message with handle " + ao.getHandle() + ". Not in SpeakerNr");
                }

            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<String> transformXML() {
        return null;
    }
}
