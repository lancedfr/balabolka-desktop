package com.lance.balabolka.util;

import com.lance.balabolka.controller.ScreensController;
import javafx.fxml.FXMLLoader;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FXMLUtils {

    /**
     * Use DocumentBuilder to find FXMLLoader.CONTROLLER_KEYWORD ("controller") value
     *
     * @param fxmlPath path to .fxml screen
     * @return the fx:controller class name
     */
    public static Class getControllerClass(String fxmlPath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            URL location = FXMLUtils.class.getResource(fxmlPath);
            Document document = builder.parse(location.openStream());
            NamedNodeMap attributes = document.getDocumentElement().getAttributes();
            String fxControllerClassName = null;
            for (int i = 0; i < attributes.getLength(); i++) {
                Node item = attributes.item(i);
                if (item.getNodeName().equals(FXMLLoader.FX_NAMESPACE_PREFIX + ":" + FXMLLoader.CONTROLLER_KEYWORD)) {
                    fxControllerClassName = item.getNodeValue();
                }
            }
            if (fxControllerClassName != null) {
                return ClassLoader.getSystemClassLoader().loadClass(fxControllerClassName);
            }
        } catch (ParserConfigurationException | SAXException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(ScreensController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
