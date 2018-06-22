package com.ts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ts.dbutility.DBConnection;
import com.ts.dto.*;

public class memberDao {
	
	public int addImage(String fileName,String username,int fId){ 
		final String INSERT_QUERY="insert into hello_message(username,message,forumId,type) values (?,?,?,2);";
		PreparedStatement pst = null;
		int status=0;
		try(Connection con=DBConnection.getConnection();) {
			pst=con.prepareStatement(INSERT_QUERY);
			pst.setString(1, username);
			pst.setString(2, fileName);
			pst.setInt(3, fId);
			status=pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	public String getforumId(String id){
		String forumId = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		Connection con = null;
		final String SELECT_QUERY = "select forumId from subjects where teacherId=?";
		try{
			con = DBConnection.getConnection();
			pst = con.prepareStatement(SELECT_QUERY);
			pst.setString(1,id);
			rst = pst.executeQuery();
			rst.next();
			forumId = rst.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.close(con,pst);
		}
		return forumId;
	}
	public String getUname(String id){
		String uname = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		Connection con = null;
		final String SELECT_QUERY = "select username from member_details where rollno=?";
		try{
			con = DBConnection.getConnection();
			pst = con.prepareStatement(SELECT_QUERY);
			pst.setString(1,id);
			rst = pst.executeQuery();
			rst.next();
			uname = rst.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.close(con,pst);
		}
		return uname;
	}
	public void selectSub(String id,String subject){
		PreparedStatement pst = null;
		Connection con = null;
		final String UPDATE_QUERY = "update subjects set teacherid=? where name=?";
		try{
			con = DBConnection.getConnection();
			pst = con.prepareStatement(UPDATE_QUERY);
			pst.setString(1,id);
			pst.setString(2,subject);
			pst.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.close(con,pst);
		}
	}
	public List<subject> showSubjects(String[] dept){
		List<subject> subjects = new ArrayList<>();
		PreparedStatement pst = null;
		ResultSet rst = null;
		final String SELECT_QUERY = "select name,dept from subjects where dept=? and isNull(teacherId);";
		Connection con = null;
		try{
			con = DBConnection.getConnection();
			pst = con.prepareStatement(SELECT_QUERY);
			for(String i: dept){
				pst.setString(1,i);
				rst= pst.executeQuery(); 
				while(rst.next()){
					subject sub=new subject();
					sub.setName(rst.getString(1));
					sub.setDept(rst.getString(2));
					subjects.add(sub);
					System.out.println(rst.getString(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.close(con,rst,pst);
		}
		return subjects;
	}
	
	public List<subject> getTeacherSubjects(String uname,String pwd){
		List<subject> list=new ArrayList<>();
		PreparedStatement pst = null;
		ResultSet rst = null;
		final String SELECT_QUERY = "select name,forumId from subjects where teacherId = (select rollno from member_details where username = ? and password = ?) ;";
		Connection con = null;
		try{
			con = DBConnection.getConnection();
			pst = con.prepareStatement(SELECT_QUERY);
			pst.setString(1,uname);
			pst.setString(2, pwd);
			rst= pst.executeQuery(); 
			while(rst.next()){
				subject sub=new subject();
				sub.setName(rst.getString(1));
				sub.setforumId(rst.getInt(2));
				list.add(sub);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.close(con,rst,pst);
		}
		return list;
	}
	
	public int updateSubject(int fid, String sub){
		int status=0;
		PreparedStatement pst = null;
		Connection con = null;
		final String UPDATE_QUERY = "update subjects set name = ? where forumId = ?;";
		try{
			con=DBConnection.getConnection();
			pst = con.prepareStatement(UPDATE_QUERY);
			pst.setString(1, sub);
			pst.setInt(2,fid);
			status=pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(con,pst);
		}
		return status;
	}
	
	public int clearMessages(int forumid){
		int status=0;
		PreparedStatement pst = null;
		Connection con = null;
		final String DELETE_QUERY = "delete from hello_message where forumid=?;";
		try{
			con=DBConnection.getConnection();
			pst = con.prepareStatement(DELETE_QUERY);
			pst.setInt(1,forumid);
			status=pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(con,pst);
		}
		return status;
	}
	public List<Chat> getMessages(int forumId){
		List<Chat> list=new ArrayList<>();
		PreparedStatement pst = null;
		Connection con = null;
		ResultSet rst = null;
		final String SELECT_QUERY = "select * from hello_message where forumId=?;";
		try{
			con = DBConnection.getConnection();
			pst = con.prepareStatement(SELECT_QUERY);
			pst.setInt(1, forumId);
			rst= pst.executeQuery();
			while(rst.next()){
				Chat c = new Chat();
				c.setUsername(rst.getString(1));
				c.setMessage(rst.getString(2));
				c.setType(rst.getInt(4));
				c.setforumId(rst.getInt(3));
				list.add(c);
				//System.out.println(ch);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(con,rst,pst);
		}
		return list;
	}
	public int addMessages(String message,String username,int fId){ 
		final String INSERT_QUERY="insert into hello_message(username,message,forumId,type) values (?,?,?,1);";
		PreparedStatement pst = null;
		int status=0;
		try(Connection con=DBConnection.getConnection();) {
			pst=con.prepareStatement(INSERT_QUERY);
			pst.setString(1, username);
			pst.setString(2, message);
			pst.setInt(3, fId);
			status=pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
public List<subject> getSubjects(String uname){
	List<subject> list=new ArrayList<>();
	PreparedStatement pst = null;
	ResultSet rst = null;
	Connection con = null;
	final String SELECT_QUERY = "select * from subjects where dept=?;";
	try{
		con = DBConnection.getConnection();
		pst = con.prepareStatement(SELECT_QUERY);
		pst.setString(1,uname);
		rst= pst.executeQuery(); 
		while(rst.next()){
			subject sub=new subject();
			sub.setName(rst.getString(1));
			sub.setDept(rst.getString(2));
			sub.setforumId(rst.getInt(3));
			sub.setteacherId(rst.getString(4));
			list.add(sub);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		DBConnection.close(con,rst,pst);
	}
	return list;
}
	
public String getMember(String uname,String pwd){
	PreparedStatement pst = null;
	ResultSet rst = null;
	Connection con = null;
	final String SELECT_QUERY = "select mode from member_types t,member_details d where (d.username = ? and d.password = ?) and d.modeid=t.modeid;";
	try{
		con = DBConnection.getConnection();
		pst = con.prepareStatement(SELECT_QUERY);
		pst.setString(1,uname);
		pst.setString(2,pwd);
		rst= pst.executeQuery(); 
		rst.next();
		return rst.getString(1);
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		DBConnection.close(con,rst,pst);
	}
	return null;
}
public String getCode(String rollno){
	PreparedStatement pst = null;
	ResultSet rst = null;
	String code=null;
	Connection con = null;
	final String SELECT_QUERY = "select code from member_types t,member_details d where d.rollno = ? and d.modeid = t.modeid";
	try{
		con = DBConnection.getConnection();
		pst = con.prepareStatement(SELECT_QUERY);
		pst.setString(1,rollno);
		rst= pst.executeQuery();
		rst.next();
		code = rst.getString(1);
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		DBConnection.close(con,rst,pst);
	}
	return code;
}

public boolean invalidId(String id){
	boolean c = false;
	PreparedStatement pst = null;
	ResultSet rst = null;
	Connection con = null;
	final String SELECT_QUERY = "select username from member_details where rollno = ?;";
	try{
		con = DBConnection.getConnection();
		pst = con.prepareStatement(SELECT_QUERY);
		pst.setString(1,id);
		rst= pst.executeQuery();
		rst.next();
		c=(!(rst.getString(1)).equals(null));
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		DBConnection.close(con,rst,pst);
	}
	return c;
}

public boolean isMember(String id){
	boolean c = false;
	PreparedStatement pst = null;
	ResultSet rst = null;
	Connection con = null;
	final String SELECT_QUERY = "select username,password from member_details d where d.rollno = ?;";
	try{
		con = DBConnection.getConnection();
		pst = con.prepareStatement(SELECT_QUERY);
		pst.setString(1,id);
		rst= pst.executeQuery();
		rst.next();
		c=!(rst.getString(1)).equals("");
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		DBConnection.close(con,rst,pst);
	}
	return c;
}

public int addTeacher(Teacher teacher){
	
	final String INSERT_QUERY="update member_details set username=?,password=? where rollno=?";
	PreparedStatement pst = null;
	
	int status=0;
	Connection con=null;
	try {
		con = DBConnection.getConnection();
		pst=con.prepareStatement(INSERT_QUERY);
		pst.setString(3, teacher.getId());
		pst.setString(1, teacher.getUsername());
		pst.setString(2, teacher.getPassword());
		
		status=pst.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		DBConnection.close(con,pst);
	}
	return status;
}

public int addStudent(Student student){
	
	final String INSERT_QUERY="update member_details set username=?,password=? where rollno=?";
	PreparedStatement pst = null;
	
	int status=0;
	Connection con=null;
	try {
		con = DBConnection.getConnection();
		pst=con.prepareStatement(INSERT_QUERY);
		pst.setString(3, student.getrollno());
		pst.setString(1, student.getUsername());
		pst.setString(2, student.getPassword());
		
		status=pst.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		DBConnection.close(con,pst);
	}
	return status;
}
}
