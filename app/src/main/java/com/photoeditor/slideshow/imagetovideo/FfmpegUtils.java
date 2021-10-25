//package com.photoeditor.slideshow.imagetovideo;
//
//import android.content.Context;
//import android.os.Environment;
//import com.arthenica.mobileffmpeg.ExecuteCallback;
//import com.arthenica.mobileffmpeg.FFmpeg;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class FfmpegUtils {
//    private static final String TAG = "TAGG";
//
//    public static void loadLibrary(Context context) {
//    }
//
//    public static String createListFile(List<String> list) {
//        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "list.txt");
//        String str = "";
//        for (int i = 0; i < list.size(); i++) {
//            str = str + "file '" + list.get(i) + "'";
//            if (i != list.size() - 1) {
//                str = str + "\n";
//            }
//        }
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
//            outputStreamWriter.write(str);
//            outputStreamWriter.close();
//            fileOutputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return file.getAbsolutePath();
//    }
//
//    public static String createListToLoop(int i, String str) {
//        File file = new File(GlobalDef.TEMP_FOLDER + File.separator + "listAudio.txt");
//        String str2 = "";
//        for (int i2 = 0; i2 < i; i2++) {
//            str2 = str2 + "file '" + str + "'";
//            if (i2 != i - 1) {
//                str2 = str2 + "\n";
//            }
//        }
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
//            outputStreamWriter.write(str2);
//            outputStreamWriter.close();
//            fileOutputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return file.getAbsolutePath();
//    }
//
//    public static void executeCommand(Context context, String str, FFmpegInterface fFmpegInterface) {
//        FFmpeg.executeAsync(buildCommand(str), new ExecuteCallback() {
//            /* class com.photoeditor.slideshow.imagetovideo.$$Lambda$FfmpegUtils$6OnbDzHafMMZitltckUucvI1RN0 */
//
//            @Override // com.arthenica.mobileffmpeg.ExecuteCallback
//            public final void apply(long j, int i) {
//                FfmpegUtils.lambda$executeCommand$0(FFmpegInterface.this, j, i);
//            }
//        });
//    }
//
//    static /* synthetic */ void lambda$executeCommand$0(FFmpegInterface fFmpegInterface, long j, int i) {
//        if (fFmpegInterface != null) {
//            fFmpegInterface.onFinish();
//        }
//        if (i == 0) {
//            if (fFmpegInterface != null) {
//                fFmpegInterface.onSuccess();
//            }
//        } else if (fFmpegInterface != null) {
//            fFmpegInterface.onFailed();
//        }
//    }
//
//    public static String[] buildCommand(String str) {
//        ArrayList arrayList = new ArrayList();
//        Matcher matcher = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(str);
//        while (matcher.find()) {
//            arrayList.add(matcher.group(1).replace("\"", ""));
//        }
//        String[] strArr = new String[arrayList.size()];
//        for (int i = 0; i < arrayList.size(); i++) {
//            strArr[i] = (String) arrayList.get(i);
//        }
//        return strArr;
//    }
//
//    public static void killProcess(Context context) {
//        FFmpeg.cancel();
//    }
//}