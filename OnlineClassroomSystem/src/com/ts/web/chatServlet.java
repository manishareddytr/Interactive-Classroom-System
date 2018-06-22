package com.ts.web;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import com.ts.dao.memberDao;
import com.ts.dto.Chat;

@WebServlet("/chatServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10,
maxRequestSize = 1024 * 1024 * 100, 
location="C:/Users/Navya/Desktop/OnlineClassroom/OnlineClassroom/WebContent/images")
public class chatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public chatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action = request.getParameter("action");
		memberDao memberDao = new memberDao();	
		//personDao.insertImage(fileName);
		String username = request.getParameter("uname");
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		System.out.println(username);
		//String username = request.getParameter("username");
		//System.out.println(message);
		response.setContentType("text/html");
		System.out.println("chatServlet");
		String forumId = request.getParameter("forumID");
		//System.out.println(forumId);
		int fId = Integer.parseInt(forumId);
		request.setAttribute("forumId", forumId);
		//System.out.println(memberDao.addMessages(message));
		if("refresh".equalsIgnoreCase(action)){
			request.setAttribute("forumId", forumId);
			System.out.println("Entered refresh");
			//System.out.println(fId);
			List<Chat> messageList = memberDao.getMessages(fId);
			request.setAttribute("chatList",messageList);
			request.setAttribute("username", username);
			System.out.println(username);
			RequestDispatcher dispatcher=request.getRequestDispatcher("chat.jsp");	
			dispatcher.include(request, response);
		}else if("chat".equalsIgnoreCase(action)){
			String message=request.getParameter("message");
			System.out.println(username);
			if(message != null && memberDao.addMessages(message,username,fId) != 0){
			//System.out.println("insert has occur");
			List<Chat> messageList = memberDao.getMessages(fId);
			//System.out.println(messageList);
			//int stat = personDao.insertImage(fileName);
			//List<String> photos = memberDao.getImage();
			//request.setAttribute("photos",photos);
			request.setAttribute("chatList",messageList);
			request.setAttribute("username", username);
			RequestDispatcher dispatcher=request.getRequestDispatcher("chat.jsp");	
			dispatcher.include(request, response);
			}else{
				System.out.println("insert didnot occur");
				System.out.println(username);
				//int stat = personDao.insertImage(fileName);
				List<Chat> messageList = memberDao.getMessages(fId);
				//List<String> messageList = memberDao.getMessages();
				//List<String> photos = memberDao.getImage();
				//request.setAttribute("photos",photos);
				request.setAttribute("username", username);
				request.setAttribute("chatList",messageList );
				RequestDispatcher dispatcher=request.getRequestDispatcher("chat.jsp");	
				dispatcher.include(request, response);
			}
		}else{
			Part part = request.getPart("photo");
		    String fileName = getFileName(part);
		    part.write(fileName);
		    if(memberDao.addImage(fileName,username,fId) != 0){
		    	List<Chat> messageList = memberDao.getMessages(fId);
		    	//List<String> photos = memberDao.getImage();
		    	//List<String> messageList = memberDao.getMessages();
		    	//int stat = personDao.insertImage(fileName);
		    	//request.setAttribute("photos",photos);
		    	request.setAttribute("username", username);
		    	request.setAttribute("chatList",messageList);
		    	RequestDispatcher dispatcher=request.getRequestDispatcher("chat.jsp");	
		    	dispatcher.include(request, response);
		    }else{
		    	System.out.println("insert didnot occur");
		    	//int stat = personDao.insertImage(fileName);
		    	List<Chat> messageList = memberDao.getMessages(fId);
		    	//List<String> messageList = memberDao.getMessages();
		    	//List<String> photos = memberDao.getImage();
		    	//request.setAttribute("photos",photos);
		    	request.setAttribute("username", username);
		    	request.setAttribute("chatList",messageList );
		    	RequestDispatcher dispatcher=request.getRequestDispatcher("chat.jsp");	
		    	dispatcher.include(request, response);
		    }
		 }
	}
	
	private String getFileName(Part part) {
		String content = part.getHeader("content-disposition");
		System.out.println("content-disposition :"+content);
		System.out.println(content.indexOf("filename"));
		System.out.println(content.length() - 1);
		String fileName = content.substring(content.indexOf("filename") + 10, content.length() - 1);
		System.out.println("fileName: "+fileName);
	   return fileName;
		//return filePath;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
