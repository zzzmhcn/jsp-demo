package com.zzzmh.jsp2023.utils;

import java.io.*;
import java.nio.charset.Charset;

/**
 * 这里照抄一下Springboot
 * org.springframework:spring-core:6.0.13
 * utils目录下StreamUtils方法
 * 实现Request.InputSteam转String
 */
public abstract class StreamUtils {

    public static final int BUFFER_SIZE = 8192;
    private static final byte[] EMPTY_CONTENT = new byte[0];

    public StreamUtils() {
    }

    public static byte[] copyToByteArray(InputStream in) throws IOException {
        return in == null ? EMPTY_CONTENT : in.readAllBytes();
    }

    public static String copyToString(InputStream in, Charset charset) throws IOException {
        if (in == null) {
            return "";
        } else {
            StringBuilder out = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(in, charset);
            char[] buffer = new char[8192];

            int charsRead;
            while((charsRead = reader.read(buffer)) != -1) {
                out.append(buffer, 0, charsRead);
            }
            return out.toString();
        }
    }

}