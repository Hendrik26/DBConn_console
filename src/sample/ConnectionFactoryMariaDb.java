package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactoryMariaDb {
    private static Exception mariaDbConnException(Exception oldException){
        String msg = "Error connecting to MasriaDB " + DbConnectionData.getDbnameMaria() + " !!!\r\n\r\n";
        Exception retException = new Exception(msg, oldException.getCause());
        retException.setStackTrace(oldException.getStackTrace());
        return retException;
    }

    static Connection createConnectionMariaDb() throws Exception {
        try {
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
        private static final String HOST_MARIA = "localhost";
        private static final String DBNAME_MARIA = "db_waehrgs_r";
        // private String urlMaria = "jdbc:mariadb://" + hostMaria + "/" + dbnameMaria;
        private static final String USERNAME_MARIA = "root";
        private static final String PW_MARIA = "";
    }
}
