package lexicalscanner;

import java.io.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.sax.*;

public class ParserHelper {

    BufferedReader in;
    StreamResult out;
    TransformerHandler th;

    public static void helper(String args[]) {
        new ParserHelper().begin();
    }

    public void begin() {
        try { 
            in = new BufferedReader(new FileReader("C:\\Users\\user\\eclipse-workspace\\compiler\\token_file"));
            out = new StreamResult("C:\\Users\\user\\eclipse-workspace\\compiler\\src\\lexicalscanner\\token_xml.xml.xml");
            openXml();
            String str;
            while ((str = in.readLine()) != null) {
                process(str);
            }
            in.close();
            closeXml();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openXml() throws ParserConfigurationException, TransformerConfigurationException, SAXException {

        SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        th = tf.newTransformerHandler();

        // pretty XML output
        Transformer serializer = th.getTransformer();
        serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");

        th.setResult(out);
        th.startDocument();
        th.startElement(null, null, "inserts", null);
    }

    public void process(String s) throws SAXException {
        th.startElement(null, null, "option", null);
        th.characters(s.toCharArray(), 0, s.length());
        th.endElement(null, null, "option");
    }

    public void closeXml() throws SAXException {
        th.endElement(null, null, "inserts");
        th.endDocument();
    }
}