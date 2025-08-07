package com.example;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestPasswordUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    //static->確保該物件只會在所屬的類別被載入，也就是呼叫TestPasswordUtil的靜態方法之時，被new一次，不會用到該物件一次就new一次。
    //final->確保該物件的reference，一定都會指向預期的物件。

    public static String hash(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    public static boolean matches(String rawPassword, String hashedPassword) {
        return encoder.matches(rawPassword, hashedPassword);
    }
}
