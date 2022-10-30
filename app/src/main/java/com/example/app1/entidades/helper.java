package com.example.app1.entidades;

public class helper {
    public static boolean isAlpha(String s)
    {
        if (s == null) {
            return false;
        }

        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z')) {
                return false;
            }
        }
        return true;
    }
}
