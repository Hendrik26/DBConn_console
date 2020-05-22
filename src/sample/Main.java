package sample;

import java.lang.Integer;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//STEP 1. Import required packages
import java.lang.reflect.Constructor;
import java.sql.*;
import java.util.Collections;

// Branch HH003

public class Main extends Application {

    private static void connectDB(){
        System.out.println("Begin connecting to DB!!!\r\n");
        System.out.println("----------------------------\r\n\r\n");

        Connection connMaria = null;
        Statement stmt = null;
        try{

            //STEP 3Maria: Open a connection
            System.out.println("Connecting to database MariaDB ...");
            connMaria = ConnectionFactoryMariaDb.createConnectionMariaDb();
            System.out.println("Connected to database MariaDB ...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = connMaria.createStatement();
            String sql;
            sql = "SELECT id, Waehrgs_Name FROM tbl_waehrgs_name";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                String waehrgsName = rs.getString("Waehrgs_Name");

                //Display values
                String row = "ID: " + id + "; WaehrungsName: " + waehrgsName + ";\r\n";
                System.out.print(row);

            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            connMaria.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
                se2.printStackTrace();
            }
            try{
                if(connMaria!=null)
                    connMaria.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!!!!!!!!!!!!");
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        /* Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        System.out.println("Hallo World GUI-Console!"); */

        this.connectDB();
    }


    public static void main(String[] args) {
        launch(args);
    }
}