import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChildComment implements Comparable<ChildComment>{
    String comment_id;
    String user_id;
    String parent_id;
    String content;
    Date date;

    ChildComment(String comment_id){
        String q1 = "select * from childcomment where comment_id = \"" + comment_id + "\";";
        ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q1);
        try {
            if(rs.next()){
                comment_id = rs.getString(1);
                user_id = rs.getString(2);
                parent_id = rs.getString(3);
                content = rs.getString(4);
                date = rs.getDate(5);                
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }

        this.comment_id = comment_id;
    }

	@Override
	public int compareTo(ChildComment o) {
		// TODO Auto-generated method stub
		if(date.equals(o.date))
			return 0;
		else if(date.after(o.date))
			return 1;
		else 
			return -1;
	}
}
