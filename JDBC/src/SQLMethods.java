import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class SQLMethods {	
	//Connection 占쌨댐옙 占쌉쇽옙(SQLMethods占쏙옙 占쌉쇽옙占쏙옙 占쏙옙載� Connection 占쏙옙占쌘곤옙占쏙옙 占쏙옙占�)
	//MYSQL占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙占쏙옙 connection占쏙옙체占쏙옙 占쏙옙환占싼댐옙.
	public static Connection con;
	
	public static void init() {
		Connection connection =  null;
		connection =  null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/twitter";
            //paswd占싸븝옙 占쏙옙占싸븝옙占쏙옙 占쏙옙占쏙옙 占십울옙
            String user = "root", passwd = "";
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
	
	public static User[] GetUsers(Connection connection){
        Statement stmt = null;
        ResultSet rs = null;
        
        List<User> list = new ArrayList<User>();
        try{
            stmt = connection.createStatement();
            String sql = "Select * from user";
            rs = stmt.executeQuery(sql);

            
           
            while(rs.next())
            {
                String id = rs.getString(1);
                User u = new User(id);
                list.add(u);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return list.toArray(new User[0]);
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
        		new CustomDialog("Login Failed!", "Wrong ID/Password!");
        		}
        
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }
    
    // return 1 : Sign in Success
    // return 0 : ID already exists
    // return -1 : error
    public static int Signin(Connection connection, String id, String pwd, String name, String email) {
        Statement stmt = null;
        ResultSet rs = null;

        try {


                String q1 = "select user_id from user where user_id = \"" + id + "\"";
                stmt = connection.createStatement();
                rs = stmt.executeQuery(q1);

                if(rs.next())
                    return 0;

                q1 = "insert into user values(\"" + id + "\", \"" + pwd + "\", \"" + name+ "\", \"\", \"" + email+"\", \"\", \"\");";
                stmt.executeUpdate(q1);
                return 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return - 1;
    }
    
    //user_id 의 게시글 불러오기
    public static List<Post> GetPosts(String user_id){
    	List<Post> list = new ArrayList<Post>();
    	List<String> taggedList = new ArrayList<String>();    
    	
    	String q1 = "select post_id from mention where user_id = \"" + user_id +"\";";
    	ResultSet rs = SQLMethods.ExecuteQuery(con, q1);
    	
    	try {
			while(rs.next()) {
				String s = rs.getString(1);
				if(s.compareTo("") != 0) {
					taggedList.add(s);
				}
				
				if(taggedList.size() >= 15)
					break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	q1 = "select * from posts where user_id = \"" + user_id + "\"";
    	
    	if(taggedList.size() > 0) {
    		q1 += " or post_id in(\"" + taggedList.get(0) + "\"";
    		
    		for(int i =0;i < taggedList.size(); i++) {
    			q1 += ", \"" + taggedList.get(i) + "\"";
    		}
    		q1 += ")";
    	}
    	
    	q1+= " order by date desc";
    	
    	rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q1);
    	
    	try {
			while(rs.next()) {
				Post p1 = new Post(rs.getString(1));
				
				list.add(p1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	return list;    	
    }
    

    public static List<Post> GetPosts(List<String> userList){
    	
    	List<String> pIdList = new ArrayList<String>();
    	List<String> taggedList = new ArrayList<String>();    	
    	
    	String q1 = "select post_id from mention where user_id = \"" + ClientInformation.Logined_id +"\";";
    	ResultSet rs = SQLMethods.ExecuteQuery(con, q1);
    	
    	try {
			while(rs.next()) {
				String s = rs.getString(1);
				if(s.compareTo("") != 0) {
					taggedList.add(s);
				}
				
				if(taggedList.size() >= 15)
					break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	if(userList.size() == 0 && taggedList.size() == 0)
    		return null;
    	
    	
    	if(userList.size() > 0)
    	{
    		q1 = "select post_id from posts where user_id in (\"" + userList.get(0)+"\"";
    	
    		for(int i =1;i<userList.size();i++) {
    			q1 += ", \""+ userList.get(i) + "\"";
    		}
    		q1 += ")";
    	}
    	else {
    		q1 = "select post_id from posts where ";
    	}
    	
    	if(taggedList.size() > 0) {
    		if(userList.size() > 0)
    			q1 += " or ";
    		
    		q1 += "post_id in(\"" + taggedList.get(0) + "\"";
    		
    		for(int i =1; i<taggedList.size(); i++) {
    			q1 += ", \"" + taggedList.get(i) + "\"";
    		}
    		
    		q1+= ")";
    		
    	}
    		
    	q1 += " order by date desc;";
    	
    	
    	
    	
    	rs = SQLMethods.ExecuteQuery(con, q1);
    	
    	try {
			while(rs.next()) {
				String s = rs.getString(1);
				if(s.compareTo("") != 0) {
					pIdList.add(s);
				}
				
				if(pIdList.size() >= 15)
					break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	List<Post> result = new ArrayList<Post>();
    	
    	for(int i =0;i<pIdList.size();i++) {
    		Post p = new Post(pIdList.get(i));
    		
    		result.add(p);
    	}
    	
    	return result; 	
    	
    }
   
    
    
    public static void WritePost(Connection connection, String user_id, String content, String[] imgs, String[] tags)
    {
        Statement stmt = null;
        ResultSet rs = null;

        try{
            stmt = connection.createStatement();
            String q1 = "select count(user_id) from posts where user_id = \"" + user_id + "\";";
            rs = stmt.executeQuery(q1);
            if(!rs.next())
                return;


            q1 = "insert into posts values(null, \"" + content + "\", \"" + user_id + "\", now());";
            stmt.executeUpdate(q1);

            int imgCount = imgs.length;

            if(imgCount > 0)
            {
                int cnt = 0;
                while(true){
                	if(cnt == imgCount)
                		break;
                    String imDir = imgs[cnt];

                    if(imDir.compareTo("") == 0)
                        break;
                    
                    String q2 = "select MAX(post_id) from posts";
                    ResultSet rs2 = stmt.executeQuery(q2);
                    int id = 0;
                    if(rs2.next())
                    	id = rs2.getInt(1);
                    
                    q2 = "insert into images values(null, \"" + imDir + "\", \"" + id + "\");";
                    stmt.executeUpdate(q2);
                    cnt++;
                }
            }
            
            int tagCount = tags.length;
            
            if(tagCount > 0) {
            	while(tagCount > 0) {
            		tagCount--;
            		String q2 = "select MAX(post_id) from posts";
                    ResultSet rs2 = stmt.executeQuery(q2);
                    int id = 0;
                    if(rs2.next())
                    	id = rs2.getInt(1);
                    
                    q2 = "insert into mention values(\"" + id + "\", \"" + tags[tagCount]+ "\");";
                    stmt.executeUpdate(q2);
                                       
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
    
    public static List<String> Followings(Connection connection, String user_id)
    {
        Statement stmt = null;
        String q1 = "select user_id from follow where follower_id = \"" + user_id + "\";";
        
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
    public static int PostLike(Connection connection, String user_id, String post_id)
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
            	if(rs.getString(1).compareTo("") == 0)
            	{
            		String q2 = "insert into post_like values(\""+post_id + "\", \"" + user_id + "\");";
                    stmt.executeUpdate(q2);
                    return 1;
            	}
            	else {
            		System.out.println("Already liked. Unlike.");
            		String q2 = "delete from post_like where liker_id = \"" + user_id +"\" and post_id = \"" + post_id + "\";";
            		stmt.executeUpdate(q2);
            		return 0;
            	}
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
    
    
    public static List<String> PostLikers(Connection connection, String post_id)
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



            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            q1 = "insert into comment values(null, \"" + content + "\", \"" + user_id + "\", \"" + post_id + "\", now());";
           
            
            stmt.executeUpdate(q1);

            return 1;
        }catch (SQLException e){
            e.printStackTrace();

            return -1;
        }
    }
    
    public static int WriteChildComment(Connection connection, String user_id, String parent_id, String content)
    {
        Statement stmt = null;
        ResultSet rs = null;
        
        System.out.println("write child comment");


        try{
            stmt = connection.createStatement();

            String q1 = "select * from comment where comment_id = \"" + parent_id + "\";";
            rs = stmt.executeQuery(q1);

            if(!rs.next())
            {
                return 0;
            }


            q1 = "insert into childcomment values(null, \"" + user_id + "\", \"" + parent_id + "\", \""+ content+ "\", now());";
           
            
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
        String q1 = "select * from comment where post_id = \"" + post_id + "\" order by date desc;";

        List<Comment> result = new ArrayList<Comment>();

        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(q1);

            while (rs.next())
            {
            	Comment cmt = new Comment(rs.getString(1));
            	result.add(cmt);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }
    
    public static List<ChildComment> ChildComments(Connection connection, String parent_id){
    	String q1 = "select comment_id from childcomment where parent_comment_id = \"" + parent_id + "\" order by date desc;";
    	
    	List<ChildComment> list = new ArrayList<ChildComment>();
    	
    	ResultSet rs = SQLMethods.ExecuteQuery(connection, q1);
    	
    	try {
			while(rs.next()) {
				ChildComment c1 = new ChildComment(rs.getString(1));
				
				list.add(c1);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	return list;    	
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
            

    
            String q2 = "select * from comment_like where comment_id = \"" + comment_id + "\" and user_id = \"" + user_id + "\";";
            ResultSet tRs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q2);
            if(tRs.next()) {
            	if(tRs.getString(1).compareTo("") != 0)
            	{
            		q2 = "delete from comment_like where user_id = \"" + user_id +"\" and comment_id = \"" + comment_id + "\";";
            		stmt.executeUpdate(q2);            			
            	}
            	else {
            		q2 = "insert into comment_like values(\"" +comment_id + "\", \"" + user_id + "\");";
                    stmt.executeUpdate(q2);
                    return 1;
            	}
            
                       
                return 0;
            }
        
            else{
            	
                 q2 = "insert into comment_like values(\"" +comment_id + "\", \"" + user_id + "\");";
                stmt.executeUpdate(q2);
                return 1;
            }
            
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }

    }
    
  //return 0 unlike
    //return 1 like
    //return -1 error
    public static int ChildCommentLike(Connection connection, String user_id, String comment_id){
        Statement stmt =null;
        ResultSet rs = null;

        try {
            String q1 = "select * from childcomment where comment_id = \"" + comment_id + "\";";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(q1);

            if(!rs.next())
                return -1;
            

            String q2 = "select * from childcomment_like where childcomment_id = \"" + comment_id + "\" and user_id = \"" + user_id + "\";";
            ResultSet tRs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q2);
            if(tRs.next()) {
            	if(tRs.getString(1).compareTo("") != 0)
            	{
            		q2 = "delete from childcomment_like where user_id = \"" + user_id +"\" and childcomment_id = \"" + comment_id + "\";";
            			stmt.executeUpdate(q2);            			
            	}
            	else {
            		q2 = "insert into childcomment_like values(\"" +comment_id + "\", \"" + user_id + "\");";
            		stmt.executeUpdate(q2);
            		return 1;
            	}

                       
                return 0;
            }
            else{
            	
                q2 = "insert into childcomment_like values(\"" +comment_id + "\", \"" + user_id + "\");";
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
    
    public static List<String> ChildCommentLikers(Connection connection, String comment_id)
    {
        Statement stmt = null;
        String q1 = "select user_id from childcomment_like where childcomment_id = \"" + comment_id + "\";";
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
