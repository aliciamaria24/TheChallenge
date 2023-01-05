package challengep;

import Database.Database;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage view) throws IOException {
        String First = "12:00";
        String Second = "13:00";
        String Third = "14:00";
        String Forth = "15:00";
        String Fifth = "16:00";

        CategoryAxis xaxis = new CategoryAxis();
        NumberAxis yaxis = new NumberAxis(20, 50, 10);
        xaxis.setLabel("Time In Hours");
        yaxis.setLabel("CO2 In Percent");

        BarChart<String, Float> infoBar = new BarChart(xaxis, yaxis);
        infoBar.setTitle("BarChart CO2");

        XYChart.Series<String, Float> data = new XYChart.Series<>();
        data.getData().add(new XYChart.Data(First, 12));
        data.getData().add(new XYChart.Data(Second, 8));
        data.getData().add(new XYChart.Data(Third, 6));
        data.getData().add(new XYChart.Data(Forth, 11));
        data.getData().add(new XYChart.Data(Fifth, 14));

        infoBar.getData().add(data);

        Group root = new Group();
        root.getChildren().add(infoBar);
        Scene scene = new Scene(root, 600, 400);
        view.setScene(scene);
        view.setTitle("BarChart CO2 Example");
        view.show();
    }
        public static void main(String[] args) {
            launch();
    }

}