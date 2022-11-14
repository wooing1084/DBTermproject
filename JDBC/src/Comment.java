import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Comment {
    String comment_id;
    String user_id;
    String parent_post_id;
    String parent_comment_id;
    String content;
    Date date;

    Comment(String comment_id){
        String q1 = "select * from comment where comment_id = \"" + comment_id + "\";";
        ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q1);
        try {
            if(rs.next()){
                comment_id = rs.getString(1);
                content = rs.getString(2);
                user_id = rs.getString(3);
                parent_post_id = rs.getString(4);
                date = rs.getDate(5);
                parent_comment_id = rs.getString(6);
                
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }

        this.comment_id = comment_id;

    }



    public int GetLikes(){
        return SQLMethods.Likers(SQLMethods.GetCon(), user_id).size();
    }

}
