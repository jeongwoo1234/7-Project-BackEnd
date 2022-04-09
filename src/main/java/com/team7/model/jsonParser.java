package com.team7.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

public class jsonParser {

	// Parse application/json type to String type
	public static String parseJson(HttpServletRequest request) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder builder = new StringBuilder();
        
        String buffer;
        while ((buffer = input.readLine()) != null) {
            if (builder.length() > 0) builder.append("\n");
            builder.append(buffer);
        } input.close();

        return builder.toString();
    }
	
}
