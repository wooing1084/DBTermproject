
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentLike {
	public String like_id;
	public String comment_id;
	public String user_id;
	public int num_of_like;
	public int likecheck;
	
	CommentLike() {
		like_id="";
		comment_id="";
		user_id="";
		num_of_like=0;
		likecheck=0;
	}
	
	CommentLike(String l_id) {
		like_id=l_id;
		comment_id="";
		user_id="";
		num_of_like=0;
		likecheck=0;
		
		String q1 = "select * from comment_like where user_id = \"" + l_id + "\";";
		
		ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(),q1);
		
		try {
			if(rs.next()) {
				like_id = rs.getString(1);
				comment_id = rs.getString(2);
				user_id = rs.getString(3);
			}
			
			while(rs.next()) {
				if(likecheck==0) {
					num_of_like=num_of_like+1;
					likecheck=1;
				}
				if(likecheck==1) {
					num_of_like=num_of_like-1;
					likecheck=0;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
