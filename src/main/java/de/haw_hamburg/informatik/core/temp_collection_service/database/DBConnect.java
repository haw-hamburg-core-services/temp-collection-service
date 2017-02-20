package de.haw_hamburg.informatik.core.rain_collection_service.database;

import de.haw_hamburg.informatik.core.rain_collection_service.RainData;

import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.sql.*;
import java.util.Calendar;
import java.util.Properties;

/**
 * Created by TimoHäckel on 30.01.2017.
 */
public class DBConnect {

    private final static String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static String DB_CONNECTION = "jdbc:mysql://localhost:3306/raindata";
    private static String DB_USER = "root";
    private static String DB_PASSWORD = "";
    private static boolean propertiesLoaded = false;

    public static void loadProperties(){
        if(propertiesLoaded){
            return;
        }
        Properties properties = new Properties();
        String propFileName = "database.properties";
        try {
            properties.load(DBConnect.class.getClassLoader().getResourceAsStream(propFileName));
            DB_USER = properties.getProperty("user");
            DB_PASSWORD = properties.getProperty("password");
            DB_CONNECTION = properties.getProperty("connection");
            propertiesLoaded = true;
            System.out.println(DB_USER + ", " + DB_PASSWORD  + ", " + DB_CONNECTION);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Connection getDBConnection() {
        loadProperties();
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            dbConnection = DriverManager.getConnection(
                    DB_CONNECTION, DB_USER,DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return dbConnection;
    }

    public static DataBaseReturns insert(String srcId, boolean raining, int intensity){
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        DataBaseReturns ret = DataBaseReturns.SUCCESS;

        if(intensity>10 || intensity<0){
            return DataBaseReturns.PARAMETER_ERROR;
        }
        String retSrcId = srcId.substring(0, Math.min(srcId.length(), 32));

        try {
            dbConnection = getDBConnection();

            String insertTableSQL = "INSERT INTO raindatacollection"
                    + " (id, timestamp, srcid, raining, intensity) VALUES"
                    + "(NULL, CURRENT_TIMESTAMP, ?, ?, ?)";
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);


            //preparedStatement.setString(1, "NULL");

            //preparedStatement.setString(2, "CURRENT_TIMESTAMP");
            preparedStatement.setString(1, retSrcId);
            preparedStatement.setBoolean(2, raining);
            preparedStatement.setInt(3, intensity);

            // execute insert SQL stetement
            preparedStatement.executeUpdate();

            System.out.println("Record is inserted into raindata table!");

        } catch (SQLException e) {
            ret = DataBaseReturns.CONNECTION_ERROR;
            e.printStackTrace();
        } finally {
            try{
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
            try{
                if (dbConnection != null) {
                    dbConnection.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public static void printRecords(){
        Connection connection = null;

        String query = "SELECT * FROM raindatacollection";
        try {
            connection = getDBConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("Database Records:");
            while(resultSet.next()){
                RainData data = new RainData(resultSet.getLong("id"), resultSet.getTimestamp("timestamp", Calendar.getInstance()),resultSet.getString("srcid"),resultSet.getBoolean("raining"), resultSet.getInt("intensity"));
                System.out.println(data);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if (connection != null) {
                    connection.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
