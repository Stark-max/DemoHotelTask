import models.Hotel;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        try {
            for (int i = 0; i < hotel.getRooms().size(); i++) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.newDocument();
                Element root = document.createElement("hotel");
                Element font = document.createElement("room");
                Text text = document.createTextNode(hotel.getClient().toString());

                //Element font = document.createElement("room");
                document.appendChild(root);
                root.appendChild(font);
                font.appendChild(text);
                font.setAttribute("size", Integer.toString(hotel.getOccupied().getCategory().getAccommodation()));
                Transformer t = TransformerFactory.newInstance().newTransformer();
                t.setOutputProperty(OutputKeys.INDENT, "yes");
                t.transform(new DOMSource(document), new StreamResult(new FileOutputStream("temp.xml")));
            }
            /*DOMImplementation impl = document.getImplementation();
            DOMImplementationLS implLS = (DOMImplementationLS) impl.getFeature("LS","3.0");
            LSSerializer ser = implLS.createLSSerializer();
            ser.getDomConfig().setParameter("format-pretty-print",true);
            String str = ser.writeToString(document);*/

            /*Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(document), new StreamResult(new FileOutputStream("temp.xml")));*/
           /* LSOutput out = implLS.createLSOutput();
            out.setEncoding("UTF-8");
            out.setByteStream(Files.newOutputStream(Path.of("temp.xml")));
            ser.write(document,out);*/
        } catch (ParserConfigurationException | IOException e) {
            throw new RuntimeException(e);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}