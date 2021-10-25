package com.photoeditor.slideshow.imagetovideo;

public class ConvertDurationUtils {
    public static String convertDurationText(int i) {
        Object obj;
        Object obj2;
        if (i <= 0) {
            return "00:00";
        }
        int i2 = i / 60;
        int i3 = i % 60;
        StringBuilder sb = new StringBuilder();
        if (i2 >= 10) {
            obj = Integer.valueOf(i2);
        } else {
            obj = "0" + i2;
        }
        sb.append(obj);
        sb.append(":");
        if (i3 >= 10) {
            obj2 = Integer.valueOf(i3);
        } else {
            obj2 = "0" + i3;
        }
        sb.append(obj2);
        return sb.toString();
    }

    public static String convertDurationText2(int i) {
        Object obj;
        if (i <= 0) {
            return "00:00";
        }
        int i2 = i / 60;
        int i3 = i % 60;
        StringBuilder sb = new StringBuilder();
        sb.append(i2);
        sb.append(":");
        if (i3 >= 10) {
            obj = Integer.valueOf(i3);
        } else {
            obj = "0" + i3;
        }
        sb.append(obj);
        return sb.toString();
    }

    public static String convertDurationFormat(int i) {
        String str;
        int i2 = i % 60;
        int i3 = (i % 3600) / 60;
        int i4 = (i % 86400) / 3600;
        int i5 = (i % 2592000) / 86400;
        StringBuilder sb = new StringBuilder();
        String str2 = "0";
        sb.append(i4 < 10 ? str2 : "");
        sb.append(i4);
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        if (i3 < 10) {
            str = str2;
        } else {
            str = "";
        }
        sb3.append(str);
        sb3.append(i3);
        String sb4 = sb3.toString();
        StringBuilder sb5 = new StringBuilder();
        if (i2 >= 10) {
            str2 = "";
        }
        sb5.append(str2);
        sb5.append(i2);
        String sb6 = sb5.toString();
        return sb2 + ":" + sb4 + ":" + sb6;
    }
}
