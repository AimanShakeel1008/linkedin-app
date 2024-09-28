package com.devorbit.linkedin.user_service.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

	public static String hashPassword(String plaintextPassword) {
		return BCrypt.hashpw(plaintextPassword,BCrypt.gensalt());
	}

	public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
		return BCrypt.checkpw(plainTextPassword, hashedPassword);
	}
}
