package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//STEP 1. Import required packages
import java.sql.*;
import myOverPackage.*;

import myOverPackage001.OverPWriter001;

// Branch HH002

public class Main extends Application {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/EMP";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    static final String JDBC_DRIVER_MARIA = "org.mariadb.jdbc.Driver";
    static final String hostMaria = "localhost";
    static final String dbnameMaria = "db_waehrgs_r";
    // String urlMaria = "jdbc:mariadb://" + hostMaria + "/" + dbnameMaria;
    static final String usernameMaria = "root";
    static final String passwordMaria = "";

    private void connectDB(){
        OverPWriter overPWriter = new OverPWriter();
        overPWriter.writeText("aaaaaa");
        myOverPackage001.OverPWriter001.writeText("bbbbbbb");
        myOverPackage.myPackage.myUnderPackage.UnderPWriter underPWriter
                = new myOverPackage.myPackage.myUnderPackage.UnderPWriter();
        underPWriter.writeText("ccccccccccccc");
        SampleWriter sampleWriter = new SampleWriter();
        sampleWriter.writeText("samW, samW, samW");




        System.out.println("Begin connecting to DB!!!\r\n");
        System.out.println("----------------------------\r\n\r\n");

        // Connection conn = null;
        Connection connMaria = null;
        Statement stmt = null;
        try{

            //STEP 3Maria: Open a connection
            System.out.println("Connecting to database MariaDB ...");
            String urlMaria = "jdbc:mariadb://" + hostMaria + "/" + dbnameMaria;
            Class.forName(JDBC_DRIVER_MARIA);
            connMaria = DriverManager.getConnection(urlMaria, usernameMaria, passwordMaria);
            System.out.println("Connected to database MariaDB ...");



            //STEP 3: Open a connection
           //  System.out.println("Connecting to database MySQL ...");
             // conn = DriverManager.getConnection(DB_URL,USER,PASS);

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
            }// nothing we can do
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
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        System.out.println("Hallo World GUI-Console!");

        this.connectDB();
    }


    public static void main(String[] args) {
        launch(args);
    }
}