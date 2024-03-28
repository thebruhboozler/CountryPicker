package org.example;


import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) {
        try(var inputStream = Main.class.getResourceAsStream("/config.properties")){

            if(inputStream == null) return;

            var props = new Properties();
            props.load(inputStream);
            Locale.setDefault(new Locale(props.getProperty("locale")));

            ResourceBundle linkAndMessage = ResourceBundle.getBundle("LinkAndMessage");
            System.out.println(linkAndMessage.getString("message"));


            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(linkAndMessage.getString("link")));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}