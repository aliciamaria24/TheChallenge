package Grafiek;

import javafx.collections.FXCollections;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.RowConstraints;
import jdk.internal.org.xml.sax.Attributes;
import javafx.scene.layout.RowConstraints;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class GrafiekController {

    private static String Object = "";

    public GrafiekController(String object) {
        Object = object;
    }

    public static class AccountController<E> {


        private Object Override;

        {
            Object override = Override;
            (WorkerStateEvent)Object Object event = new Object();

            java.lang.Object event1 = event; {
            Attributes getCanvasImagesFromServer;
            List<E> imageObservableList = (List<E>) FXCollections.observableList(getCanvasImagesFromServer.getValue("String"));

            String version = "1.0";
            Object encoding = "UTF-8";


            Object GridPane;
            Object maxHeight;
            maxHeight ="-Infinity";
            String maxWidth = "-Infinity";
            String minHeight = "-Infinity";
            String minWidth = "-Infinity";
            String prefHeight = "400.0";
            String prefWidth = "600.0";
            String fx;
            xmlns:fx="http://javafx.com/fxml/1";
            Object controller;
            fx:controller = "Controller.AccountController";
            Object ColumnConstraints;
            java.lang.Object columnConstraints = ColumnConstraints;
            int String;
            java.lang.Object columnConstraints1 = columnConstraints;
            java.lang.Object columnConstraints12 = columnConstraints1;
            java.lang.Object columnConstraints11 = columnConstraints12;
            columnConstraints11 = String;
            String hgrow = "SOMETIMES";
            minWidth="10.0";
            prefWidth="100.0";
            hgrow = "SOMETIMES";
            minWidth="10.0";
            prefWidth="100.0";

            Object RowConstraints = new Object();
            java.lang.Object rowConstraints = RowConstraints;
            RowConstraints minHeight="10.0";
            prefHeight="30.0";
            String vgrow = "SOMETIMES";
            RowConstraints minHeight="10.0";
            prefHeight="30.0";
            vgrow="SOMETIMES";
            RowConstraints minHeight="10.0";
            prefHeight="30.0";
            vgrow="SOMETIMES";
</rowConstraints>;
<GridPane>;


            for(RestImage image : imageObservableList){
                if(!(image == null)) {
                    if (!(null == image.getCanvasImage())) {
                        try {
                            InputStream in = new ByteArrayInputStream(image.getCanvasImage());
                            BufferedImage bImageFromConvert = ImageIO.read(in);
                            Object SwingFXUtils = new Object();
                            Image fxImage = SwingFXUtils.toFXImage(bImageFromConvert,null);

                            if(!(fxImage == null)){
                                System.out.println("Fx image is not null");
                            }
                        }catch (Exception ignored){}


                    }
                }
            }
        }
        }

        public AccountController(java.lang.Object override) {
            Override = override;
        }

        public AccountController() {

        }
    }
}