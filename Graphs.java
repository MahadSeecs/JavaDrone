// refreces : https://github.com/yugasun/x-chart
//  https://stackoverflow.com/questions/1389285/real-time-graphing-in-java
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import java.io.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Creates a simple real-time chart
 */
public class Graphs {
    final static int  valuesToShow=5; // TOTAL VALUES ON X-AXIS OF CHART
    static ArrayList<Double> xData = new ArrayList<Double>(); // DATA ON X-axis
    static ArrayList<Double> yData = new ArrayList<Double>();// data on Y-axis

    public static void makeHumidityGraph() throws Exception{
        getHumidityData();


        // Create Chart
        final XYChart chart = QuickChart.getChart("Humidity data from Sensor", "Observations", "Humidity Value", "humidity",xData.subList(0,valuesToShow), yData.subList(0,valuesToShow));

        // Show it
        final SwingWrapper<XYChart> sw = new SwingWrapper<XYChart>(chart);
        sw.displayChart();
        int count=0;


        while (true) {




            Thread.sleep(500);

            chart.updateXYSeries("humidity", xData.subList(count,count+valuesToShow),yData.subList(count,count+valuesToShow), null);
            count+=1;
            if (count==xData.size()-valuesToShow){
                count=1;
            }
            sw.repaintChart();
        }

    }


    public static void main(String[] args) throws Exception {
        makeHumidityGraph();


    }
    public static ArrayList<Double> getHumidityData(){
        String DB_URL = "jdbc:mysql://localhost/drone_database";
        String USER = "root";
        String PASSWORD = "12345678";
        String QUERY = ("SELECT * FROM humiditysensor");
        //SELECT query to get value from sesor
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);) {

            while (rs.next()) {
                xData.add((double)rs.getInt("observations"));
                yData.add((double)rs.getInt("humiditydata"));
            }
            return xData;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return xData;//will return empty for error
    }

}