package com.petmeeting.mvc.common.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.petmeeting.mvc.common.util.Encrypt;

public class EncryptPasswordWrapper extends HttpServletRequestWrapper {

	public EncryptPasswordWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		String value = "";
		
		if(name.equals("inputPwd")) {
			value = Encrypt.SHA512(super.getParameter(name));
		} else {
			value = super.getParameter(name);
		}
		
		return value;
	}
}