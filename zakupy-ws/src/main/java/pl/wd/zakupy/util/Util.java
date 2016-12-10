package pl.wd.zakupy.util;

import java.security.MessageDigest;
import java.text.DecimalFormat;

import sun.misc.BASE64Encoder;

public class Util {
	public static final String TRUE = "true";

	public final static String ENCODING_UTF8 = "utf-8";

	private static DecimalFormat _decimal_formatter;

	public final static String file_name_invalid_chars = "\\/:*?\"<>|"; 


	public static String encode_password( String pass ) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA");
		md.update(pass.getBytes(ENCODING_UTF8));
		byte raw[] = md.digest();
		String hash = (new BASE64Encoder()).encode(raw);

		return hash;
	}

	
	public static String getFileExt( String path ) {
		String extension = "";

		int i = path.lastIndexOf('.');
		if (i > 0) {
		    extension = path.substring(i+1).toLowerCase();
		}
		
		return extension;
	}
	

}
