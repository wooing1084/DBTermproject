import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class main {
    static Connection con =  null;
    public static void main(String[] args)
    {
    	SQLMethods.init();
    	ClientInformation.Logined_id = "hhh";
    	//new ViewPost("hhh1");
    	//new Login();
    	//test
    	//new Setting("abcd", 9990);
        //new ViewPost("hi");
        new MainFeed();
    }

    

}
