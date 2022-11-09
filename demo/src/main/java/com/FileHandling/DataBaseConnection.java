package FileHandling;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection{
    //method used to read and load data from property file which contains the url,username and the password
    Properties propertiesfile() throws IOException {
        Properties properties = null;
        FileReader propertyfile = null;
        try {
            propertyfile = new FileReader("D:\\File\\Connection.properties");
            properties= new Properties();
            properties.load(propertyfile);
        }
        catch (IOException e)
        {
            throw new IOException(e.getMessage());
        }
        finally
        {
            propertyfile.close();
        }
        return properties;
        //* ---- return the properties of the property file to the caller method (getConnection())
    }
    //method to establish the connection to the database
    public Connection getConnection() throws SQLException, IOException, ClassNotFoundException {
        Connection con = null;
        try {
            Properties propertiesFile=propertiesfile();
            String url = propertiesFile.getProperty("url");
            String user = propertiesFile.getProperty("user");
            String password = propertiesFile.getProperty("password");
//            System.out.println("*******"+url+" "+user+" "+password);
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user,password);
        }
        catch (ClassNotFoundException exception)
        {
            throw new ClassNotFoundException(exception.getMessage());
        }
        catch (IOException exception)
        {
            throw new IOException(exception.getMessage());
        }
        catch (SQLException exception)
        {
            throw new SQLException(exception.getMessage());
        }
        return con;
        // * ---- return the connection
    }

    // method to close the connection in database

    public void closeConnection() throws ClassNotFoundException, IOException, SQLException {
        try{
            getConnection().close();
        }
        catch (ClassNotFoundException exception)
        {
            throw new ClassNotFoundException(exception.getMessage());
        }
        catch (IOException exception)
        {
            throw new IOException(exception.getMessage());
        }
        catch (SQLException exception)
        {
            throw new SQLException(exception.getMessage());
        }
    }
}