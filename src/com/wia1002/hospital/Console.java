package com.wia1002.hospital;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Console {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String readLine() throws Exception {
        String s = br.readLine();
        return s == null ? "" : s.trim();
    }
}
