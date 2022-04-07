package com.team7.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.team7.model.MemberDAO;
import com.team7.model.MemberDTO;

@WebServlet("/members")
public class ServletSample extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
        MemberDAO memberDAO = new MemberDAO();
        list = memberDAO.getMembers();

        Gson gson = new Gson();
        String memberJson = gson.toJson(list);
        out.write(memberJson);
        out.flush();
        out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
