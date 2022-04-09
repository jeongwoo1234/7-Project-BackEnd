package com.team7.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.team7.model.jsonParser;
import com.team7.model.member.MemberDAO;
import com.team7.model.member.MemberDTO;

@WebServlet("/CreateMember")
public class CreateMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request json
		request.setCharacterEncoding("UTF-8");
		String jsonStr = jsonParser.parseJson(request);
		Gson gson = new GsonBuilder().create();
		MemberDTO member = gson.fromJson(jsonStr, MemberDTO.class);
		
		MemberDAO memberDAO = new MemberDAO();
		memberDAO.insertMember(member.getImage(), member.getName(), member.getBirthday(), member.getGender(), member.getJob());

	}

}
