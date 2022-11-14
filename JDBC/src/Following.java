
import java.sql.ResultSet;
import java.sql.SQLException;

public class Following {
	public String following_id;
	public String user_id;
	
	
	Following() {
		following_id="";
		user_id="";
		
	}
	
	Following(String f_id) {
		following_id=f_id;
		user_id="";
		
		
		String q1 = "select * from comment_like where user_id = \"" + f_id + "\";";
		
		ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(),q1);
		
		try {
			if(rs.next()) {
				following_id = rs.getString(1);
				user_id = rs.getString(2);
			}
			
			while(rs.next()) {
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
