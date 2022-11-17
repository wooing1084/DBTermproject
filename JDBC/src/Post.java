import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post implements Comparable<Post>{
	public String post_id;
	public String content;
	public String user_id;
	public Date date;
	List<String> images;
	
	Post(){
		post_id = "";
		content = "";
		user_id = "";
		date = new Date();
		images = new ArrayList<String>();
	}
	
	Post(String p_id){
		post_id = p_id;
		content = "";
		user_id = "";
		date = new Date();
		images = new ArrayList<String>();
		
		String q1 = "select * from posts where post_id = \"" + post_id + "\";";
		
		ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(),q1);
		
		try {
			if(rs.next()) {
				post_id = rs.getString(1);
				content = rs.getString(2);
				user_id = rs.getString(3);
				date = rs.getDate(4);
			}
			
			q1 = "select * from images where post_id = \"" + post_id + "\";";
			
			rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(),q1);
			
			while(rs.next()) {
				images.add(rs.getString(2));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int compareTo(Post o) {
		// TODO Auto-generated method stub
		if(date.equals(o.date))
			return 0;
		else if(date.after(o.date))
			return 1;
		else 
			return -1;
	}
}
