import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class main {
    static Connection con =  null;
    public static void main(String[] args)
    {
        con = SQLMethods.GetCon();
        Scanner keyboard = new Scanner(System.in);
        String login_id = null;
        
        new Login();

        List<String> followers = SQLMethods.Likers(con, "1234");
        System.out.println(followers);

        try {
            if(con != null && !con.isClosed())
                con.close();;
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    

}
