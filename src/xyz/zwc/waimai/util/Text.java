package xyz.zwc.waimai.util;

import java.util.Random;
import java.util.regex.Pattern;

public class Text {
	/**
	 * 判断字符串是否为整数
	 * 
	 * @param str
	 *            传入的字符串
	 * 
	 * @return 是整数返回true,否则返回false
	 */

	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[0-9]+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是否为密码。长度不超过40，数字或字母
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isPassword(String str) {
		if (str.length() > 40) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[0-9a-zA-Z]+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是否为email。长度不超过50
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmail(String str) {
		if (str.length() > 50) {
			return false;
		}
		Pattern pattern = Pattern
				.compile("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
		return pattern.matcher(str).matches();
	}

	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static boolean isImage(String filename) {
		boolean result = false;
		if (filename.toLowerCase().endsWith(".png") || filename.toLowerCase().endsWith(".gif")
				|| filename.toLowerCase().endsWith(".jpg") || filename.toLowerCase().endsWith(".bmp")
				|| filename.toLowerCase().endsWith(".jpeg")) {
			result = true;
		}
		return result;
	}

	public static String getSuffix(String filename) {
		return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
	}
}
