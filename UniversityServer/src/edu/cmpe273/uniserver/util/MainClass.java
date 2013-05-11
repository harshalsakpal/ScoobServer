package edu.cmpe273.uniserver.util;

import java.io.IOException;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class MainClass {
	public static void main(String args[]) throws IOException {
		String toEncode = "admin";

		// encoding byte array into base 64
		String encoded = Base64.encode(toEncode.getBytes());

		System.out.println("Original String: " + toEncode);
		System.out.println("Base64 Encoded String : " + new String(encoded));

		// decoding byte array into base64
		byte[] decoded;
		try {
			decoded = Base64.decode(encoded.getBytes());

			System.out.println("Base 64 Decoded  String : "
					+ new String(decoded));
		} catch (Base64DecodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
