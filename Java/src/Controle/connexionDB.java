package Controle;
import java.sql.*;


public class connexionDB {


    /**
     * Parameters required for the connection to our database
     */
    public static final String USERNAME = "y7SwdYH3eQ";
    public static final String PASSWORD = "fTjwRyLaj4";
    public static final String CONN_STRING = "jdbc:mysql://remotemysql.com:3306/y7SwdYH3eQ";
    
    
	/**
     * Connects to our online database
     */
    public static Connection start(){
        

        try {
           Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            System.out.print("Connected");
            return conn;
        }catch (SQLException e){
            System.err.print(e);
            return null;
        }
		

    }

}
