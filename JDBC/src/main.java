import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class main {
    static Connection con =  null;
    public static void main(String[] args)
    {
        con = SQLMethods.GetCon();        
        new Login();
  
        //new MainFeed();
       

        try {
            if(con != null && !con.isClosed())
                con.close();;
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    

}
