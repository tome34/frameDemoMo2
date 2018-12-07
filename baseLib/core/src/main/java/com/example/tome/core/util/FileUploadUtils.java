package com.example.tome.core.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 *     desc   : Retrofit文件上传工具类
 */

public class FileUploadUtils {

    /**
     * 把File 转成 MultipartBody.Part
     */
    public static List<MultipartBody.Part> filesToMultipartBodyParts(List<String> filePaths) {
        List<MultipartBody.Part> parts = new ArrayList<>(filePaths.size());
        for (String path : filePaths) {

//            RequestBody.create(MediaType.parse("application/otcet-stream"), file);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), new File(path));

            MultipartBody.Part part = MultipartBody.Part.createFormData("images[]", System.currentTimeMillis() + "", requestBody);
            parts.add(part);
        }
        return parts;
    }


    public static Map<String, RequestBody> filesToMap(List<String> filePaths) {
        Map<String, RequestBody> map = new HashMap<>();

        for (String picPath : filePaths
                ) {

            RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), new File(picPath));
            map.put("image\"; filename=\"" + System.currentTimeMillis() + "", fileBody);

        }
        return map;
    }

    public static RequestBody toRequestBody(String infos) {

        return RequestBody.create(MediaType.parse("text/plain"), infos);
    }


    public static MultipartBody.Part picToMultipartBodyPart(String filePath) {

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), new File(filePath));
//        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), new File(filePath));
        return MultipartBody.Part.createFormData("file", System.currentTimeMillis() + "", requestBody);
    }
//    public static MultipartBody.Part picToMultipartBodyPart1(String filePath) {
//        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), new File(filePath));
//        MultipartBody.Part part = MultipartBody.Part.createFormData("file", System.currentTimeMillis() + "", requestBody);
//        return part;
//    }

    public static MultipartBody.Part picToMultipartBodyPart(String tagName, String filePath) {

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), new File(filePath));
        return MultipartBody.Part.createFormData(tagName, System.currentTimeMillis() + "", requestBody);
    }

    public static MultipartBody.Part mp4ToMultipartBodyPart(String tagName, String filePath) {

        RequestBody requestBody = RequestBody.create(MediaType.parse("video/mp4"), new File(filePath));
        return MultipartBody.Part.createFormData(tagName, System.currentTimeMillis() + ".mp4", requestBody);
    }
//
//    public static MultipartBody.Part fileToMultipartBodyPart_(String filePath) {
//
//
//        RequestBody requestFile =
//                RequestBody.create(MediaType.parse("multipart/form-data"), new File(filePath));
//        MultipartBody.Part part = MultipartBody.Part.createFormData("file", System.currentTimeMillis() + "", requestFile);
//        return part;
//    }

    public static MultipartBody filesToMultipartBody(List<String> filePaths) {
        MultipartBody.Builder builder = new MultipartBody.Builder();

        for (String path : filePaths) {
            File file = new File(path);
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            builder.addFormDataPart("file", file.getName(), requestBody);
        }
        builder.setType(MultipartBody.FORM);
        return builder.build();
    }
}
