package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//STEP 1. Import required packages
import java.lang.reflect.Constructor;
import java.sql.*;
import myOverPackage.*;

// Branch HH002

public class Main extends Application {

    // JDBC driver name and database URL
    //  Database credentials

    private static class DbConnectionData {
        public static String getJdbcDriverMaria() {
            return JDBC_DRIVER_MARIA;
        }

        public static String getHostMaria() {
            return HOST_MARIA;
        }

        public static String getDbnameMaria() {
            return DBNAME_MARIA;
        }

        public static String getUsernameMaria() {
            return USERNAME_MARIA;
        }

        public static String getPwMaria() {
            return PW_MARIA;
        }

        public static String getUrlMaria(){
            return "jdbc:mariadb://" + DbConnectionData.HOST_MARIA + "/" + DbConnectionData.DBNAME_MARIA;
        }

        private static final String JDBC_DRIVER_MARIA = "org.mariadb.jdbc.Driver";
        private static final String HOST_MARIA = "localhost";
        private static final String DBNAME_MARIA = "db_waehrgs_r";
        // private String urlMaria = "jdbc:mariadb://" + hostMaria + "/" + dbnameMaria;
        private static final String USERNAME_MARIA = "root";
        private static final String PW_MARIA = "";
    }


    private String createStringObjectViaClassForName(){
        try {
            Class<?> stringClass = Class.forName("java.lang.String");
            Constructor<?> stringConstructor = stringClass.getConstructor(stringClass);
            Object retString = stringConstructor.newInstance(
                    "This is a String created by Class.forName(...)!!!"
            );
            return (String) retString;
        } catch(Exception e) {
            e.printStackTrace();
            return "Error creating String by Class.forName(...)!!!";
        } finally {
            int i = -1;
        }
    }

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
            Class<?> driverClassMaria = Class.forName(DbConnectionData.JDBC_DRIVER_MARIA);
            connMaria = DriverManager.getConnection(DbConnectionData.getUrlMaria(), DbConnectionData.USERNAME_MARIA, DbConnectionData.PW_MARIA);
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
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        System.out.println("Hallo World GUI-Console!");

        this.connectDB();
        System.out.println(this.createStringObjectViaClassForName());
    }


    public static void main(String[] args) {
        launch(args);
    }
}