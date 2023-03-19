package com.petmeeting.mvc.common.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
	public static String SHA512(String password) {
		
		String hex = null;
		MessageDigest msg;
		
		try {
			
			msg = MessageDigest.getInstance("SHA-512");
			msg.update(password.getBytes());
			hex = String.format("%128x", new BigInteger(1, msg.digest()));
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return hex;
	}
	
	
	
	
}