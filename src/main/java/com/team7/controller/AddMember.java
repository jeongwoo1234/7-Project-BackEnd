package com.team7.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team7.model.member.MemberDAO;

@WebServlet("/addMember")
public class AddMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	private static final String ATTACHES_DIR = "C:\\attaches";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// json type request
//		String jsonStr = Parser.parseStr(request);
//		Gson gson = new GsonBuilder().create();
//		MemberDTO member = gson.fromJson(jsonStr, MemberDTO.class);
		
		HashMap<String, String> member = Parser.parseMultipartToHashMap(request, response, ATTACHES_DIR);
		MemberDAO memberDAO = new MemberDAO();
		memberDAO.insertMember(
				member.get("image"), 
				member.get("name"), 
				member.get("birthday"), 
				member.get("gender"), 
				member.get("job")
		);
		
		
	}
}

