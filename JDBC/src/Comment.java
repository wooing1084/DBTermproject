import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Comment implements Comparable<Comment>{
    String comment_id;
    String user_id;
    String post_id;
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
                post_id = rs.getString(4);
                date = rs.getDate(5);                
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }

        this.comment_id = comment_id;
    }



    public int GetLikes(){
        return SQLMethods.PostLikers(SQLMethods.GetCon(), user_id).size();
    }
    
    @Override
	public int compareTo(Comment o) {
		// TODO Auto-generated method stub
    	if(date.equals(o.date))
			return 0;
		else if(date.after(o.date))
			return 1;
		else 
			return -1;
	}

}
