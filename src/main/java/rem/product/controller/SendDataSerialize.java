package rem.product.controller;

import java.io.BufferedReader;
import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;

public class SendDataSerialize {
	
	public static String ChangeData(HttpServletRequest request) {
		
		StringBuffer strbuf = new StringBuffer();
		String line = null;
		
		BufferedReader reader = null;
		
		try {
			reader = request.getReader();
			
			while((line = reader.readLine()) != null) {
				strbuf.append(line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String reqdata = strbuf.toString();
		System.out.println(reqdata);
		
		return reqdata;
	}

}
