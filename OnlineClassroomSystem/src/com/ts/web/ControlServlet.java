package com.ts.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ts.dao.memberDao;
import com.ts.dto.*;

/**
 * Servlet implementation class ControlServlet
 */
@WebServlet("/ControlServlet")
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		System.out.println(action);
		if("setSubjects".equalsIgnoreCase(action)){
			memberDao memberDao = new memberDao();
			String id = request.getParameter("id");
			request.setAttribute("id",id);
			String[] subjects = request.getParameterValues("select");
			
			List<String> subs = Arrays.asList(subjects);  
			
			for(String i: subs){
				System.out.println(i);
				memberDao.selectSub(id,i);
			}
			RequestDispatcher dispatcher=request.getRequestDispatcher("login.html");	
			dispatcher.forward(request, response);
		}else if("viewForum".equalsIgnoreCase(action)){
			memberDao memberDao = new memberDao();
			String uname = request.getParameter("uname");
			//HttpSession session = request.getSession();
			//session.setAttribute("username", uname);
			String pwd = request.getParameter("pwd");
			String memberType=memberDao.getMember(uname, pwd);
			String memType = request.getParameter("memType");
			String fId = request.getParameter("forumId");
			int forumId = Integer.parseInt(fId);
			//System.out.println(memType);
			System.out.println(uname);
			//System.out.println(forumId);
			List<Chat> messageList = memberDao.getMessages(forumId);
			System.out.println("ControlServlet");
			request.setAttribute("username",uname);
			request.setAttribute("chatList",messageList );
			request.setAttribute("memType", memType);
			request.setAttribute("forumId", fId);
			RequestDispatcher dispatcher=request.getRequestDispatcher("chat.jsp");	
			dispatcher.include(request, response);
		}else if("adminUpdatedView".equalsIgnoreCase(action)){
			memberDao memberDao = new memberDao();
			String uname = request.getParameter("uname");
			String forumId = request.getParameter("forumId");
			String subject = request.getParameter("subject");
			//System.out.println(uname);
			//System.out.println(forumId);
			memberDao.updateSubject(Integer.parseInt(forumId),subject);
			List<subject> subjectList=memberDao.getSubjects(uname);
			//System.out.println(subjectList);
			request.setAttribute("subjectList", subjectList);
			RequestDispatcher dispatcher=request.getRequestDispatcher("adminPanel.jsp");	
			dispatcher.forward(request, response);
		}else if("adminView".equalsIgnoreCase(action)){
			memberDao memberDao = new memberDao();
			String uname = request.getParameter("uname");
			//System.out.println(uname);
			List<subject> subjectList=memberDao.getSubjects(uname);
			//System.out.println(subjectList);
			request.setAttribute("subjectList", subjectList);
			RequestDispatcher dispatcher=request.getRequestDispatcher("adminPanel.jsp");	
			dispatcher.forward(request, response);
		}else if("login".equalsIgnoreCase(action)){
			PrintWriter out=response.getWriter();
			
			String uname=request.getParameter("uname");
			String pwd=request.getParameter("pwd");
			request.setAttribute("uname", uname);
			request.setAttribute("pwd", pwd);
			response.setContentType("text/html");
			out.println("<html>");
			
			memberDao memberDao = new memberDao();
			String memberType=memberDao.getMember(uname, pwd);
			//request.setAttribute("memberType", "memberType");
			out.println("<html>");
			//System.out.println(memberType);
			if("admin".equals(memberType)){
				List<subject> subjectList=memberDao.getSubjects(uname);
				//System.out.println(subjectList);
				request.setAttribute("subjectList", subjectList);
				RequestDispatcher dispatcher=request.getRequestDispatcher("adminPanel.jsp");	
				dispatcher.forward(request, response);
			}else if("teacher".equals(memberType)){
				List<subject> subjectList = memberDao.getTeacherSubjects(uname, pwd);
				//System.out.println(subjectList);
				request.setAttribute("subjectList", subjectList);
				for(subject i : subjectList){
				System.out.println(i.getName());
				System.out.println(i.getforumId());
				}
				RequestDispatcher dispatcher=request.getRequestDispatcher("teacherPanel2.jsp");	
				dispatcher.forward(request, response);			
			}else if(memberType==null){
				out.println("<body><center><p style='color:red;'>INVALID CREDENTIALS!</p></center></body>");
				RequestDispatcher dispatcher=request.getRequestDispatcher("login.html");
				dispatcher.include(request, response);		
			}else{
				List<subject> subjectList=memberDao.getSubjects(memberType);
				System.out.println(subjectList);
				request.setAttribute("subjectList", subjectList);
				RequestDispatcher dispatcher=request.getRequestDispatcher("studPanel.jsp");	
				dispatcher.forward(request, response);
			}
		}else if("studSignup".equalsIgnoreCase(action)){
			PrintWriter out=response.getWriter();
			
			String name=request.getParameter("name");
			String id=request.getParameter("rollNo");
			String code=request.getParameter("ccode");
			String uname=request.getParameter("uname");
			String pwd=request.getParameter("pwd");
			
			Student student=new Student();
			student.setName(name);
			student.setrollno(id);
			student.setCode(code);
			student.setUsername(uname);
			student.setPassword(pwd);
			
			response.setContentType("text/html");
			out.println("<html>");
			
			memberDao memberDao = new memberDao();
			//System.out.println(memberDao.invalidId(id));
			if(memberDao.isMember(id)){
				out.println("<body><center><p style='color:red;'>ID ALREADY REGISTERED!</p></center></body>");
				RequestDispatcher dispatcher=request.getRequestDispatcher("login.html");
				dispatcher.include(request, response);
			}else if(memberDao.getCode(id)==null){
				out.println("<body><center><p style='color:red;'>INVALID CREDENTIALS!</p></center></body>");
				RequestDispatcher dispatcher=request.getRequestDispatcher("Registration.html");
				dispatcher.include(request, response);
			}else if(!(memberDao.getCode(id)).equals(code)){
				out.println("<body><center><p style='color:red;'>INVALID CLASS REGISTRATION!</p></center></body>");
				RequestDispatcher dispatcher=request.getRequestDispatcher("Registration.html");
				dispatcher.include(request, response);
			}else if(memberDao.addStudent(student)!=0){
				//String memberType=memberDao.getMember(uname, pwd);
				//List<subject> subjectList = memberDao.showSubjects(memberType);
				//request.setAttribute("subjectList", subjectList);
				RequestDispatcher dispatcher=request.getRequestDispatcher("login.html");
				dispatcher.include(request, response);
			}else{
				out.println("<body><center><p style='color:red;'>INVALID CREDENTIALS!</p></center></body>");
				RequestDispatcher dispatcher=request.getRequestDispatcher("login.html");
				dispatcher.include(request, response);
			}
		}else if("teacherSignup".equalsIgnoreCase(action)){
			PrintWriter out=response.getWriter();
			
			String name=request.getParameter("name");
			String id=request.getParameter("id");
			String[] dept=request.getParameterValues("departments");
			String uname=request.getParameter("uname");
			String pwd=request.getParameter("pwd"); 
			
			request.setAttribute("departments", dept); 
			/*
			for (String element: dept) {
	            System.out.println(element);
	        }
			*/
			Teacher teacher=new Teacher();
			teacher.setName(name);
			teacher.setId(id);
			teacher.setUsername(uname);
			teacher.setPassword(pwd);
			
			response.setContentType("text/html");
			out.println("<html>");
			
			memberDao memberDao = new memberDao();
			System.out.println(memberDao.isMember(teacher.getId()));
			if(memberDao.isMember(teacher.getId())){
				out.println("<body><center><p style='color:red;'>ID ALREADY REGISTERED!</p></center></body>");
				RequestDispatcher dispatcher=request.getRequestDispatcher("login.html");
				dispatcher.include(request, response);
			}else if(memberDao.addTeacher(teacher)!=0){
				List<subject> subjectList = memberDao.showSubjects(dept);
				/*
				for(subject i : subjectList){
					System.out.println(i.getName());
				}
				*/
				request.setAttribute("subjectList", subjectList);
				RequestDispatcher dispatcher=request.getRequestDispatcher("setSubs.jsp");
				dispatcher.include(request, response);
			}else{
				out.println("<body><center><p style='color:red;'>INVALID CREDENTIALS!</p></center></body>");
				RequestDispatcher dispatcher=request.getRequestDispatcher("Registration.html");
				dispatcher.include(request, response);
			}
		}else if("clear".equalsIgnoreCase(action)){
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			out.println("<html>");
			out.println("<body>");
			memberDao memberDao = new memberDao();
			String uname = request.getParameter("uname");
			//System.out.println(uname);
			request.setAttribute("uname",uname);
			request.setAttribute("forum_id", request.getParameter("forumId"));
			//System.out.println(request.getParameter("forumId"));
			int forumId = Integer.parseInt(request.getParameter("forumId"));
			//System.out.println(forumId);
			out.println("<br>");
			if(memberDao.clearMessages(forumId)!=0){
				out.println("Forum cleared!");
				RequestDispatcher dispatcher=request.getRequestDispatcher("addSub.jsp");
				dispatcher.include(request, response);
			}else{
				out.println("No messages</body>");
				RequestDispatcher dispatcher=request.getRequestDispatcher("addSub.jsp");
				dispatcher.include(request, response);
			}
		}
		else{
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
