import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SQLMethods {	
	//Connection 받는 함수(SQLMethods의 함수에 들어간 Connection 인자값에 사용)
	//MYSQL연동 후 연동된 connection객체를 반환한다.
	public static Connection con;
	
	public static void init() {
		Connection connection =  null;
		connection =  null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/twitter";
            //paswd부분 개인별로 설정 필요
            String user = "root", passwd = "dong1084@";
            connection = DriverManager.getConnection(url, user, passwd);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        
        con = connection;
	}
	
	public static Connection GetCon()
	{
		return con;
	}

    public static ResultSet ExecuteQuery(Connection con, String q1){
        ResultSet result = null;
        try{
            Statement stmt = con.createStatement();
            result = stmt.executeQuery(q1);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return result;
    }

    public static int ExecuteUpdate(Connection con, String q1){
        int result = 0;
        try{
            Statement stmt = con.createStatement();
            result = stmt.executeUpdate(q1);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
	
	public static String SearchUser(Connection connection){
        Statement stmt = null;
        ResultSet rs = null;
        try{
            stmt = connection.createStatement();
            String sql = "Select * from user";
            rs = stmt.executeQuery(sql);

            System.out.println("ID\t\tname");
            while(rs.next())
            {
                String id = rs.getString(1);
                String name = rs.getString(3);
                if(rs.wasNull()) id = "null";
                if(rs.wasNull()) name = "null";

                System.out.println(id + "\t\t" + name);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return "";
    }
	
	// return 0 : 
    public static String Login(Connection con, String id, String pwd) {
        Statement stmt = null;
        ResultSet rs = null;

        try {
        	String q1 = "select * from user where user_id = \"" + id + "\" and pwd = \"" + pwd + "\"";

        	stmt = con.createStatement();
        	rs = stmt.executeQuery(q1);
        	if(rs.next()){
        		System.out.println("Logged in!");
        		return rs.getString(1);
        		}
        	else {
        		System.out.println("Wrong Id/password.");
        		}
        
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }
    
    // return 1 : Sign in Success
    // return 0 : ID already exists
    // return -1 : error
    public static int Signin(Connection connection, String id, String pwd, String name) {
        Statement stmt = null;
        ResultSet rs = null;

        try {


                String q1 = "select user_id from user where user_id = \"" + id + "\"";
                stmt = connection.createStatement();
                rs = stmt.executeQuery(q1);

                if(rs.next())
                    return 0;

                q1 = "insert into user values(\"" + id + "\", \"" + pwd + "\", \"" + name+ "\", \"\");";
                stmt.executeUpdate(q1);
                return 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return - 1;
    }
    
    
    public static void WritePost(Connection connection, String user_id, String content, String[] imgs)
    {
        String post_id;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            stmt = connection.createStatement();
            String q1 = "select count(user_id) from posts where user_id = \"" + user_id + "\";";
            rs = stmt.executeQuery(q1);
            if(!rs.next())
                return;

            String temp =  rs.getString(1);
            int counts = Integer.parseInt(temp);
            counts++;

            post_id = user_id + counts;


            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            q1 = "insert into posts values(\"" + post_id + "\", \"" + content + "\", \"" + user_id + "\", Date(\"" +sqlDate+"\"));";
            stmt.executeUpdate(q1);

            int imgCount = imgs.length;

            if(imgCount > 0)
            {
                int cnt = 0;
                while(true){
                	if(cnt == imgCount)
                		break;
                    String imDir = imgs[cnt];

                    if(imDir.compareTo("0") == 0)
                        break;
                    String img_id = post_id + "I" + cnt;
                    String q2 = "insert into images values(\"" + img_id + "\", \"" + imDir + "\", \"" + post_id + "\");";
                    stmt.executeUpdate(q2);
                    cnt++;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    // return 0 : unfollow
    // return 1 : follow
    // return 2 : cannot found
    // return -1 : error
    public static int Follow(Connection connection, String user_id, String follow_id)
    {
        Statement stmt = null;
        ResultSet rs = null;
        
        String q1 = "select user_id from user where user_id = \"" + follow_id + "\";";

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(q1);

            if(rs.next())
            {
                String q2 = "select user_id from follow where user_id = \"" + follow_id+ "\" and follower_id = \"" + user_id + "\";";
                ResultSet rs2 = stmt.executeQuery(q2);
                if(rs2.next())
                {
                    String q4 = "delete from follow where user_id = \"" + follow_id + "\" and follower_id = \""+user_id+"\";";
                    stmt.executeUpdate(q4);
                    return 0;
                }
                else{
                String q3 = "insert into follow values(\"" + follow_id + "\", \"" + user_id + "\");";
                stmt.executeUpdate(q3);
                return 1;
                }
            }
                return 2;
            
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        
        return -1;

    }
    
    public static List<String> Followers(Connection connection, String user_id)
    {
        Statement stmt = null;
        String q1 = "select follower_id from follow where user_id = \"" + user_id + "\";";
        
        List<String> result = new ArrayList<String >();

        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(q1);
            while (rs.next())
                result.add(rs.getString(1));
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    //return 0 unlike
    //return 1 like
    //return -1 error
    public static int Like(Connection connection, String user_id, String post_id)
    {
        Statement stmt =null;
        ResultSet rs = null;

        try {
            String q1 = "select * from posts where post_id = \"" + post_id + "\";";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(q1);

            if(!rs.next())
            {
                System.out.println("Enter a wrong post id!");
                return -1;
            }

            q1 = "select * from post_like where liker_id = \"" + user_id + "\" and post_id = \""  + post_id + "\";";
            rs = stmt.executeQuery(q1);
            if(rs.next())
            {
                System.out.println("Already liked. Unlike.");
                String q2 = "delete from post_like where liker_id = \"" + user_id +"\" and post_id = \"" + post_id + "\";";
                stmt.executeUpdate(q2);
                return 0;
            }
            else{
                String q2 = "insert into post_like values(\""+post_id + "\", \"" + user_id + "\");";
                stmt.executeUpdate(q2);
                return 1;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }
    public static List<String> Likers(Connection connection, String post_id)
    {
        Statement stmt = null;
        String q1 = "select liker_id from post_like where post_id = \"" + post_id + "\";";

        List<String> result = new ArrayList<String>();

        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(q1);

            while (rs.next())
               result.add(rs.getString(1));
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    //return 0 cannot write comment
    //return 1 success write
    //return -1 error
    public static int WriteComment(Connection connection, String user_id, String post_id, String content)
    {
        Statement stmt = null;
        ResultSet rs = null;


        try{
            stmt = connection.createStatement();

            String q1 = "select * from posts where post_id = \"" + post_id + "\";";
            rs = stmt.executeQuery(q1);

            if(!rs.next())
            {
                return 0;
            }

            q1 = "select count(user_id) from comment where post_id = \"" + post_id + "\";";
            String comment_id = "";
            rs = stmt.executeQuery(q1);
            if(rs.next())
            {
                int cnt = Integer.parseInt(rs.getString(1));
                cnt++;
                comment_id = post_id+ "C" + cnt;
            }

            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            q1 = "insert into comment values(\"" + comment_id + "\", \"" + content + "\", \"" + user_id + "\", \"" + post_id + "\", Date(\"" +sqlDate+"\"));";
            stmt.executeUpdate(q1);

            return 1;
        }catch (SQLException e){
            e.printStackTrace();

            return -1;
        }
    }

    //
    public static List<Comment> Comments(Connection connection, String post_id)
    {
        Statement stmt = null;
        String q1 = "select * from comment where post_id = \"" + post_id + "\";";

        List<Comment> result = new ArrayList<Comment>();

        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(q1);

            System.out.println("comments");
            while (rs.next())
            {
                System.out.println(rs.getString(3) + ":" + rs.getString(2));
                System.out.println(rs.getString(5));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    //return 0 unlike
    //return 1 like
    //return -1 error
    public static int CommentLike(Connection connection, String user_id, String comment_id){
        Statement stmt =null;
        ResultSet rs = null;

        try {
            String q1 = "select * from comment where comment_id = \"" + comment_id + "\";";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(q1);

            if(!rs.next())
                return -1;


            q1 = "select * from comment_like where user_id = \"" + user_id + "\";";
            rs = stmt.executeQuery(q1);
            if(rs.next())
            {
                String q2 = "delete from comment_like where user_id = \"" + user_id +"\" and comment_id = \"" + comment_id + "\";";
                stmt.executeUpdate(q2);
                return 0;
            }
            else{
                String q2 = "insert into comment_like values(\""+comment_id + "\", \"" + user_id + "\");";
                stmt.executeUpdate(q2);
                return 1;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }

    }
    public static List<String> CommentLikers(Connection connection, String comment_id)
    {
        Statement stmt = null;
        String q1 = "select user_id from comment_like where comment_id = \"" + comment_id + "\";";
        List<String> result = new ArrayList<String>();
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(q1);

            while (rs.next())
            {
               result.add(rs.getString(1));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

}
