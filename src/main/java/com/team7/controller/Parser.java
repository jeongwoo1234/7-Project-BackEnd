package com.team7.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(
		location = "C:\\attaches",
		maxFileSize = -1,
		maxRequestSize = -1,
		fileSizeThreshold = 1024)
public class Parser {

	// Parse application/json to String
	public static String parseJsonToStr(HttpServletRequest request) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder builder = new StringBuilder();
        
        String buffer;
        while ((buffer = input.readLine()) != null) {
            if (builder.length() > 0) builder.append("\n");
            builder.append(buffer);
        } input.close();

        return builder.toString();
    }
	
	// Parse multipart/form-data to HashMap
	public static HashMap<String, String> parseMultipartToHashMap(HttpServletRequest request, HttpServletResponse response, String dir) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		Collection<Part> parts = null;
		HashMap<String, String> data = new HashMap<String, String>();
		try {
			parts = request.getParts();
		}catch (IllegalStateException e) {
			// 파일 업로드 크기 제한 초과 시 처리
		}
		for (Part part : parts) {
			// file
			if (part.getHeader("Content-Disposition").contains("filename=")) {
                String fileName =  extractFileName(part.getHeader("Content-Disposition"));
                if (part.getSize() > 0) {
                	String path = dir + File.separator  + fileName;
                	data.put(part.getName(), path);
                    part.write(path); part.delete();
                }
            // not file
            } else
            	data.put(part.getName(), request.getParameter(part.getName()));
		}
		return data;
	}
	
	private static String extractFileName(String partHeader) {
	    for (String cd : partHeader.split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            String fileName = cd.substring(cd.indexOf("=") +  1).trim().replace("\"", "");
	            int index = fileName.lastIndexOf(File.separator);
	            return fileName.substring(index + 1);
	        }
	    } return null;
	}
}
