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
    	ClientInformation.Logined_id = "hihi";
 
    	//new UserSearch();
    	//new posting("hi");
    	new Login();
    	//new MainFeed();
    	//new Setting("hi");
    	//new Profile("hi");
    }

    

}
