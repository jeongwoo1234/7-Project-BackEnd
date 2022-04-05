package com.team7.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team7.classes.Member;
import com.google.gson.Gson;

@WebServlet("/members")
public class ServletSample extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        ArrayList<Member> list = new ArrayList<Member>();
        list.add(new Member(1, "https://placeimg.com/64/64/1", "이태민", "991230", "남자", "대학생"));
        list.add(new Member(2, "https://placeimg.com/64/64/2", "이순신", "381004", "남자", "대학생"));
        list.add(new Member(3, "https://placeimg.com/64/64/3", "홍길동", "520712", "남자", "대학생"));
        
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
