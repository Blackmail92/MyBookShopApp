package com.example.MyBookShopApp.data;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class BookToAuthorServiceTest {
    static String numeric = "[-+]?\\d+";
    public static void main(String[] args) {
        String str = "1224";
        Integer strInt = doSmth(str);
        System.out.println(String.format("%d, %s", strInt, str));
    }

    private static Integer doSmth(String str) {
        String newStr = str;
        str = "HA-HA PI-PI";
        return newStr.matches(numeric) ? Integer.parseInt(newStr) : 0;
    }
}