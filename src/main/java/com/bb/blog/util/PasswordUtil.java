package com.bb.blog.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    // jbcrypt会自动加盐，不用在数据库表单开一列👍

    // 加密密码
    public static String encryptPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // 验证密码
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}