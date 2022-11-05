import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class main {
    static Connection con =  null;
    public static void main(String[] args)
    {
        con =  null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/twitter";
            String user = "root", passwd = "dong1084@";
            con = DriverManager.getConnection(url, user, passwd);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        Scanner keyboard = new Scanner(System.in);
        String login_id = null;

        while(true){
            System.out.println("[0: Exit,  1: Search users, 2: log in,  3:Sign in,  4:write post,  5:follow,  6: followers]");
            System.out.println("[7: like,  8: likers  9:comment  10: comments  11: comment like  12: comment likers]");
            System.out.println("Enter a method number");
            int method = keyboard.nextInt();
            keyboard.nextLine();

            if(method == 0)
                break;
            else if(method == 1)
                SearchUser();
            else if(method == 2)
                login_id = Login();
            else if(method == 3)
                Signin();
            else if(method == 4)
            {
                if(login_id == null)
                    System.out.println("Login first!");
                else
                    WritePost(login_id);
            }
            else if(method == 5)
            {
                if(login_id == null)
                    System.out.println("Login first!");
                else
                    Follow(login_id);
            }
            else if(method == 6)
            {
                if(login_id == null)
                    System.out.println("Login first!");
                else
                    Followers(login_id);
            }
            else if(method == 7)
            {
                if(login_id == null)
                    System.out.println("Login first!");
                else
                    Like(login_id);
            }
            else if(method == 8)
            {
                System.out.println("Enter a post id:");
                String str = keyboard.nextLine();
                Likers(str);
            }
            else if(method == 9)
            {
                if(login_id == null)
                    System.out.println("Login first!");
                else
                    WriteComment(login_id);
            }
            else if(method == 10)
            {
                System.out.println("Enter a post id:");
                String str = keyboard.nextLine();
                Comments(str);
            }
            else if(method == 11)
            {
                if(login_id == null)
                    System.out.println("Login first!");
                else
                    CommentLike(login_id);
            }
            else if(method == 12)
            {
                System.out.println("Enter a comment id:");
                String str = keyboard.nextLine();
                CommentLikers(str);
            }
        }

        try {
            if(con != null && !con.isClosed())
                con.close();;
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static String SearchUser(){
        Statement stmt = null;
        ResultSet rs = null;
        try{
            stmt = con.createStatement();
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
    public static String Login() {
        Scanner keyboard = new Scanner(System.in);
        String id, pwd;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            while (true) {
                System.out.println("Enter a id(If you want quit enter 0):");
                id = keyboard.nextLine();

                if (id == "0")
                    break;

                System.out.println("Enter a password:");
                pwd = keyboard.nextLine();

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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }
    public static void Signin() {
        Scanner keyboard = new Scanner(System.in);
        String id, pwd;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            while (true) {
                System.out.println("Enter a id(If you want quit enter 0):");
                id = keyboard.nextLine();

                if (id == "0")
                    break;

                String q1 = "select user_id from user where user_id = \"" + id + "\"";
                stmt = con.createStatement();
                rs = stmt.executeQuery(q1);

                if(rs.next())
                {
                    System.out.println("Id is already exists!");
                    continue;
                }

                System.out.println("Enter a password:");
                pwd = keyboard.nextLine();

                System.out.println("Enter your name: ");
                String name = keyboard.nextLine();

                q1 = "insert into user values(\"" + id + "\", \"" + pwd + "\", \"" + name+ "\", \"\");";
                stmt.executeUpdate(q1);
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return;
    }
    public static void WritePost(String user_id)
    {
        Scanner keyboard = new Scanner(System.in);
        String post_id;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            stmt = con.createStatement();
            String q1 = "select count(user_id) from posts where user_id = \"" + user_id + "\";";
            rs = stmt.executeQuery(q1);
            if(!rs.next())
                return;

            String temp =  rs.getString(1);
            int counts = Integer.parseInt(temp);
            counts++;

            post_id = user_id + counts;

            System.out.println("Enter a content:");
            String content = keyboard.nextLine();

            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            q1 = "insert into posts values(\"" + post_id + "\", \"" + content + "\", \"" + user_id + "\", Date(\"" +sqlDate+"\"));";
            stmt.executeUpdate(q1);

            System.out.println("Do you input images(Y/N)?");
            String input = keyboard.nextLine();

            if(input.compareTo("Y") == 0)
            {
                int cnt = 0;
                while(true){
                    System.out.println("Enter a image directory(0 : exit):");
                    String imDir = keyboard.nextLine();

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
    public static void Follow(String user_id)
    {
        Scanner keyboard = new Scanner(System.in);
        Statement stmt = null;
        ResultSet rs = null;

        System.out.println("Enter a id who you want follow: ");
        String follow_id = keyboard.nextLine();

        String q1 = "select user_id from user where user_id = \"" + follow_id + "\";";

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(q1);

            if(rs.next())
            {
                String q2 = "select user_id from follow where user_id = \"" + follow_id+ "\" and follower_id = \"" + user_id + "\";";
                ResultSet rs2 = stmt.executeQuery(q2);
                if(rs2.next())
                {
                   // delete from follow where user_id = "jungsh0228" and follower_id = "abcd";
                    String q4 = "delete from follow where user_id = \"" + follow_id + "\" and follower_id = \""+user_id+"\";";
                    stmt.executeUpdate(q4);
                    System.out.println("Already following. Unfollow " + follow_id);
                }
                else{
                String q3 = "insert into follow values(\"" + follow_id + "\", \"" + user_id + "\");";
                stmt.executeUpdate(q3);
                }
            }
            else{
                System.out.println("User cannot found.");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }


    }
    public static void Followers(String user_id)
    {
        Statement stmt = null;
        String q1 = "select follower_id from follow where user_id = \"" + user_id + "\";";

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(q1);

            System.out.println("Followers");
            while (rs.next())
            {
                System.out.println(rs.getString(1));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void Like(String user_id)
    {
        System.out.println("Enter a post_id what you like:");
        Scanner input = new Scanner(System.in);
        String post_id = input.nextLine();

        Statement stmt =null;
        ResultSet rs = null;

        try {
            String q1 = "select * from posts where post_id = \"" + post_id + "\";";
            stmt = con.createStatement();
            rs = stmt.executeQuery(q1);

            if(!rs.next())
            {
                System.out.println("Enter a wrong post id!");
                return;
            }

            q1 = "select * from post_like where liker_id = \"" + user_id + "\" and posts_post_id = \""  + post_id + "\";";
            rs = stmt.executeQuery(q1);
            if(rs.next())
            {
                System.out.println("Already liked. Unlike.");
                String q2 = "delete from post_like where liker_id = \"" + user_id +"\" and posts_post_id = \"" + post_id + "\";";
                stmt.executeUpdate(q2);
            }
            else{
                String q2 = "insert into post_like values(\""+post_id + "\", \"" + user_id + "\");";
                stmt.executeUpdate(q2);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void Likers(String post_id)
    {
        Statement stmt = null;
        String q1 = "select liker_id from post_like where posts_post_id = \"" + post_id + "\";";

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(q1);

            System.out.println("Likers");
            while (rs.next())
            {
                System.out.println(rs.getString(1));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void WriteComment(String user_id)
    {
        Scanner keyboard = new Scanner(System.in);
        String post_id;
        Statement stmt = null;
        ResultSet rs = null;

        System.out.println("Enter a post id");
        post_id = keyboard.nextLine();

        try{
            stmt = con.createStatement();

            String q1 = "select * from posts where post_id = \"" + post_id + "\";";
            rs = stmt.executeQuery(q1);

            if(!rs.next())
            {
                System.out.println("Enter wrong post id!");
                return;
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

            System.out.println("Enter a content:");
            String content = keyboard.nextLine();

            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            q1 = "insert into comment values(\"" + comment_id + "\", \"" + content + "\", \"" + user_id + "\", \"" + post_id + "\", Date(\"" +sqlDate+"\"));";
            stmt.executeUpdate(q1);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void Comments(String post_id)
    {
        Statement stmt = null;
        String q1 = "select * from comment where post_id = \"" + post_id + "\";";

        try {
            stmt = con.createStatement();
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
    }
    public static void CommentLike(String user_id){
        System.out.println("Enter a Comment what you like:");
        Scanner input = new Scanner(System.in);
        String comment_id = input.nextLine();

        Statement stmt =null;
        ResultSet rs = null;

        try {
            String q1 = "select * from comment where comment_id = \"" + comment_id + "\";";
            stmt = con.createStatement();
            rs = stmt.executeQuery(q1);

            if(!rs.next())
            {
                System.out.println("Enter a wrong comment id!");
                return;
            }

            q1 = "select * from comment_like where userr_id = \"" + user_id + "\";";
            rs = stmt.executeQuery(q1);
            if(rs.next())
            {
                System.out.println("Already liked. Unlike.");
                String q2 = "delete from comment_like where userr_id = \"" + user_id +"\" and comment_id = \"" + comment_id + "\";";
                stmt.executeUpdate(q2);
            }
            else{
                String q2 = "insert into comment_like values(\""+comment_id + "\", \"" + user_id + "\");";
                stmt.executeUpdate(q2);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public static void CommentLikers(String comment_id)
    {
        Statement stmt = null;
        String q1 = "select userr_id from comment_like where comment_id = \"" + comment_id + "\";";

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(q1);

            System.out.println("Likers");
            while (rs.next())
            {
                System.out.println(rs.getString(1));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}
