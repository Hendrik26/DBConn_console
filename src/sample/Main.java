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

import myOverPackage.*;

// Branch HH005

public class Main extends Application {

    // JDBC driver name and database URL
    //  Database credentials


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

    private static void testPackages() {
        OverPWriter overPWriter = new OverPWriter();
        overPWriter.writeText("aaaaaa");
        OverPWriter.writeTextStatic("bbbbbbb");
        myOverPackage.myPackage.myUnderPackage.UnderPWriter underPWriter
                = new myOverPackage.myPackage.myUnderPackage.UnderPWriter();
        underPWriter.writeText("ccccccccccccc");
        myOverPackage.myPackage.myUnderPackage.UnderPWriter.writeTextStatic("ddddddddddddddd");
        SampleWriter sampleWriter = new SampleWriter();
        sampleWriter.writeText("samW, samW, samW");

        myExtOverPackage.OverPWriter overExtPWriter = new myExtOverPackage.OverPWriter();
        overExtPWriter.writeText("extAaaaaa");
        myExtOverPackage.myPackage.myUnderPackage.UnderPWriter underExtPWriter
                = new myExtOverPackage.myPackage.myUnderPackage.UnderPWriter();
        underExtPWriter.writeText("extCccccccc");
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        System.out.println("Hallo World GUI-Console!");

        this.testPackages();
        this.connectDB();
        System.out.println(this.createStringObjectViaClassForName());
        // testException();
        testStringFormat();
        testArrayAdding();
    }

    private static String stringArrayToString(String[] array){
        String retString = "";
        for (String s : array){
            retString += s + "; ";
        }
        return retString;
    }

    private static String stringArrayToString(ArrayList<String> arrayList){
        /** Overrides method for String[] */
        String retString = "";
        retString = arrayList.stream().reduce("", (String s1, String s2) -> {
            return s1 +  s2 + "; ";
        });
        return retString;
    }

    private static ArrayList<String> sortStringArrayList(ArrayList<String> arrayList){
        ArrayList<String> retStringList = (ArrayList<String>)arrayList.clone();
        retStringList.sort((s1, s2) -> {
            return (-1) * s1.compareToIgnoreCase(s2);
        });
        return retStringList;
    }

    private static ArrayList<String> sortModifyStringArrayList(ArrayList<String> arrayList){
        ArrayList<String> retStringList = (ArrayList<String>)arrayList.clone();
        retStringList.sort((s1, s2) -> {
            s1 = s1 + "1x";
            s2 = s2 + "2y";
            return (-1) * s1.compareToIgnoreCase(s2);
        });
        return retStringList;
    }

    public static void testArrayAdding(){
        System.out.println("\r\n\r\nMethod testArrayAdding()!!!");
        String[] basicArray = {"aaaaa", "bbbbb", "ccccccc", "dddddddd"};
        System.out.println("basicArray== " + stringArrayToString(basicArray));
        String stringToAdd = "This is the String to add.";
        // ArrayList<String> basicArrayList = new ArrayList<String>(basicArray); // wrong syntax
        // ArrayList<String> basicArrayList = Arrays.asList(basicArray); // wrong syntax
        ArrayList<String> basicArrayList = new ArrayList<String>();
        Collections.addAll(basicArrayList, basicArray);
        System.out.println("basicArrayList== " + stringArrayToString(basicArrayList));
        System.out.println("sortedArrayList== " + stringArrayToString(sortStringArrayList(basicArrayList)));
        System.out.println("sortedModifiedArrayList== "
                + stringArrayToString(sortModifyStringArrayList(basicArrayList)));
        basicArrayList.add(stringToAdd);
        System.out.println("basicArrayListAdded== " + stringArrayToString(basicArrayList));
        basicArrayList.add(0,stringToAdd);
        System.out.println("basicArrayListAdded== " + stringArrayToString(basicArrayList));
        basicArrayList.add(basicArrayList.size() / 2,stringToAdd);
        System.out.println("basicArrayListAdded== " + stringArrayToString(basicArrayList));
    }

    public static void testException(){
        System.out.println("\r\n\r\nTest Exception-Properties now!!!\r\n");
        // String testString = "StringFormatTestString";
        // System.out.println(String.format("Test String.format(...) with %m!\r\n", testString));
        try {
            int i = -2;
            throwTestException();
            System.out.println("Test Exception-Properties, end of try-block!\r\n");
        } catch(Exception e) {
            System.out.println("The message of the exception is " + e.getMessage() + "!\r\n");
            System.out.println("The StackTrace of the exception is: \r\n");
            e.printStackTrace();
            System.out.println("\r\nThe StackTrace of the exception has been printed! \r\n");
        } finally {
            int i = -1;
            System.out.println("Exception-Properties have been tested!!!\r\n\r\n\r\n");
        }
    }

    public static void testStringFormat(){
        // uses parameterized Strings
        System.out.println("\r\n\r\nTest method String.format(...) now!\r\n");
        double floatVar = 3.1416;
        int intVar = 42;
        String stringVar000 = "Kaesebroetchen";
        String stringVar001 = "Quarkkuchen";
        String stringVar002 = "Jogurtbecher";
        String fs;
        fs = String.format("The value of the float " +
                        "variable is %f, while " +
                        "the value of the " +
                        "integer variable is %d, " +
                        " and the string is %s",
                floatVar, intVar, stringVar000);
        System.out.println(fs);;
        fs = String.format("Test String.format(...) with %s, %s and %s!\r\n", stringVar000, stringVar001,
                stringVar002);
        System.out.println(fs);;
        System.out.println("Method String.format(...) has been tested!\r\n");
    }

    public static void throwTestException() throws Exception {
        StackTraceElement stackTraceElement = new StackTraceElement("Main",
                "public static void throwTestException()", "HHs StackTraceElement", Integer.MAX_VALUE);
        StackTraceElement[] stackTraceElements = {stackTraceElement};
        Exception retException = new Exception("\r\nThis is a Test-Exception!!!\r\n");
        retException.setStackTrace(stackTraceElements);
        throw retException;
    }

    public static void main(String[] args) {
        launch(args);
    }
}