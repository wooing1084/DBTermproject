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


  /*	JDBC과거 사용 예제(참고용)
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
     */

        try {
            if(con != null && !con.isClosed())
                con.close();;
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    

}
