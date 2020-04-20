package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collections;

/**
 * produces connerction to the following Database
 *         private static final String JDBC_DRIVER_MARIA = "org.mariadb.jdbc.Driver";
 *         private static final String HOST_MARIA = "localhost";
 *         private static final String DBNAME_MARIA = "db_waehrgs_r";
 *         private static final String USERNAME_MARIA = "root";
 *         private static final String PW_MARIA = "";
 */
public class ConnectionFactoryMariaDb {

    private static Exception mariaDbConnException(Exception oldException){
        String msg = String.format("Error connecting to MasriaDB %s !!!\r\n" +
                "oldMessage==%s\r\n",
                DbConnectionData.getDbnameMaria(),
                oldException.getMessage());
        StackTraceElement stackTraceElement = new StackTraceElement("createConnectionMariaDb()",
                "mariaDbConnException", "ConnectionFactoryMariaDb", Integer.MAX_VALUE);

        ArrayList<StackTraceElement> retStackTraceList = new ArrayList<StackTraceElement>();
        Collections.addAll(retStackTraceList, oldException.getStackTrace());
        retStackTraceList.add(0,stackTraceElement);

        Exception retException = new Exception(msg, oldException.getCause());
        retException.setStackTrace(retStackTraceList.toArray(oldException.getStackTrace()));
        return retException;
    }

    static Connection createConnectionMariaDb() throws Exception {
        try {
            System.load(DbConnectionData.getJdbcDriverFilePathMaria());
            ////////////////////////////
            System.out.println("Connecting to database MariaDB in ConnFactoryMethod ...");
            Class<?> driverClassMaria = Class.forName(DbConnectionData.getJdbcDriverMaria());
            Connection connMaria = DriverManager.getConnection(DbConnectionData.getUrlMaria(),
                    DbConnectionData.getUsernameMaria(), DbConnectionData.getPwMaria());
            System.out.println("Connected to database MariaDB in ConnFactoryMethod ...");
            return connMaria;
        } catch(Exception e) {
            throw mariaDbConnException(e);
        } finally {
            int i = -1;
        }
    }

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

        public static String getJdbcDriverFilePathMaria() {
            return JDBC_DRIVER_FILE_PATH_MARIA;
        }

        private static final String JDBC_DRIVER_FILE_PATH_MARIA
                = "C:\\Users\\hendr\\.IntelliJIdea2019.3\\config\\jdbc-drivers\\MariaDB Connector J" +
                "\\2.4.1\\mariadb-java-client-2.4.1.jar";
        private static final String HOST_MARIA = "localhost";
        private static final String DBNAME_MARIA = "db_waehrgs_r";
        private static final String USERNAME_MARIA = "root";
        private static final String PW_MARIA = "";
    }
}
