package Controle;
import java.sql.*;

public class connexionDB {

    public static final String USERNAME = "y7SwdYH3eQ";
    public static final String PASSWORD = "fTjwRyLaj4";
    public static final String CONN_STRING = "jdbc:mysql://remotemysql.com:3306/y7SwdYH3eQ";

    public static void main(String[] args){
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            System.out.print("Connected");
        }catch (SQLException e){
            System.err.print(e);
        }

    }

}
