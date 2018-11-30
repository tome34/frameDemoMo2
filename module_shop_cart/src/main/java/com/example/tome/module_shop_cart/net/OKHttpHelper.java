package com.example.tome.module_shop_cart.net;

import com.example.tome.projectCore.constant.CurrentLanguage;
import com.example.tome.core.util.L;
import com.example.tome.projectCore.bean.ObjectBean;
import com.example.tome.module_shop_cart.base.BaseObjs;
import com.example.tome.module_shop_cart.base.INetResult;
import com.example.tome.module_shop_cart.bean.ObjectBeans;
import com.example.tome.module_shop_cart.utils.BasicTool;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.base.Request;
import com.orhanobut.logger.Logger;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 类描述    : 网络请求工具类
 * 包名      : com.fecmobile.baselib.utils
 * 类名称    : OKHttpHelper
 * 创建人    : ghy
 * 创建时间  : 2017/9/11 15:51
 * 修改人    :
 * 修改时间  :
 * 修改备注  :
 */
public class OKHttpHelper {
    public static int GET = 1;
    private static Gson gson = new Gson();
    public static String token = "f15824685fdde5479474f723770391dd";
    private static String APPID = "101605";
    private static String VERSION = "1.0.0";
    private static String code;

    public static void execute(String url, Object params, Type clsType, int qType, final INetResult iNetResult, final int flag) {

        //回调
        JsonCallback jsonCall = new JsonCallback(clsType) {
            @Override
            public void onSuccess(Response response) {
                try {
                    BaseObjs<ObjectBeans> baseBean = (BaseObjs<ObjectBeans>) response.body();
                    L.d("OKHttpHelper","state:"+baseBean.getCode());
                    if ("000".equals(baseBean.getCode())) {
                        if (BasicTool.isEmpty(baseBean.getData().getState())) {
                            iNetResult.onSuccess(flag, baseBean);
                        } else if ("0".equals(baseBean.getData().getState())) {
                            iNetResult.onSuccess(flag, baseBean);
                        } else {
                            iNetResult.onError(baseBean.getData().getMsg(), baseBean.getData().getState(), flag);
                        }
                    } else {
                        iNetResult.onError(baseBean.getMessage(), baseBean.getCode(), flag);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    //L.d("OKHttpHelper","onError_e:");
                    setLanguage(iNetResult,"-1", flag);
                }
            }

            @Override
            public void onStart(Request request) {
                super.onStart(request);
                iNetResult.requestBefore(flag);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                iNetResult.complete(flag);
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                setLanguage(iNetResult,"-1", flag);
                //L.d("OKHttpHelper","onError:");
            }
        };

        //构建请求
        PostRequest<Object> getRequest = OkGo.post(url);


        JSONObject bodyObj = null;
        try {
            bodyObj = new JSONObject(gson.toJson(params));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        getRequest.params("version", VERSION);
        getRequest.params("token", token);
        getRequest.params("appId", APPID);

        if (bodyObj != null) {
            L.i("bodyObj================" + bodyObj);
            Iterator bodyIterator = bodyObj.keys();
            String paramsStr = "{";
            //添加参数-
            while (bodyIterator.hasNext()) {
                try {
                    String key = (String) bodyIterator.next();
                    String val = bodyObj.get(key) + "";
                    paramsStr += (key + "='" + val + "',");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (paramsStr.contains(",")) {
                paramsStr.substring(0, paramsStr.lastIndexOf(","));
            }
            paramsStr += "}";
            getRequest.params("param", paramsStr);
            L.i("++++++++++++++"+paramsStr);
        }

        print(url, token, bodyObj.toString());
        getRequest.tag(iNetResult).execute(jsonCall);
    }



    public static void uploadFile(String url, File file, Type clsType, int qType, final INetResult iNetResult, final int flag) {
        //回调
        JsonCallback jsonCall = new JsonCallback(clsType) {
            @Override
            public void onSuccess(Response response) {
                BaseObjs<ObjectBean> baseBean = (BaseObjs<ObjectBean>) response.body();
                if ("000".equals(baseBean.getCode())) {
                    if (BasicTool.isEmpty(baseBean.getData().getState())) {
                        iNetResult.onSuccess(flag, baseBean);
                    } else if ("0".equals(baseBean.getData().getState())) {
                        iNetResult.onSuccess(flag, baseBean);
                    } else {
                        iNetResult.onError(baseBean.getData().getMsg(), baseBean.getCode(), flag);
                    }
                } else {
                    iNetResult.onError(baseBean.getMessage(), baseBean.getCode(), flag);
                }
            }

            @Override
            public void onStart(Request request) {
                super.onStart(request);
                iNetResult.requestBefore(flag);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                iNetResult.complete(flag);
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                setLanguage(iNetResult,"-1", flag);
                // BaseData.UNIT = "EGP ";
            }
        };

        //构建请求
        PostRequest<Object> getRequest = OkGo.post(url);


//        JSONObject bodyObj = null;
//        try {
//            bodyObj = new JSONObject();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        getRequest.params("version", VERSION);
        getRequest.params("token", token);
        getRequest.params("appId", APPID);

//        getRequest.upFile(file,MediaType.parse("image"));
        String param = null;
//        try {
////            param = "{'fileType'='jpg','file'='"+new String(file2byte(file),"GB2312")+"'}";
//            getRequest.params("param", param);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }



        getRequest.params("param","{'fileType'='jpg'}");
        getRequest.params("file",file);

//        getRequest.upFile(file,MediaType.parse("image"));

        print(url, token, param);
        getRequest.tag(iNetResult).execute(jsonCall);
    }

    public static void uploadFile2(String url, File file,Object params, Type clsType, int qType, final INetResult iNetResult, final int flag) {
        //回调
        JsonCallback jsonCall = new JsonCallback(clsType) {
            @Override
            public void onSuccess(Response response) {
                BaseObjs<ObjectBean> baseBean = (BaseObjs<ObjectBean>) response.body();
                if ("000".equals(baseBean.getCode())) {
                    if (BasicTool.isEmpty(baseBean.getData().getState())) {
                        iNetResult.onSuccess(flag, baseBean);
                    } else if ("0".equals(baseBean.getData().getState())) {
                        iNetResult.onSuccess(flag, baseBean);
                    } else {
                        iNetResult.onError(baseBean.getData().getMsg(), baseBean.getCode(), flag);
                    }
                } else {
                    iNetResult.onError(baseBean.getMessage(), baseBean.getCode(), flag);
                }
            }

            @Override
            public void onStart(Request request) {
                super.onStart(request);
                iNetResult.requestBefore(flag);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                iNetResult.complete(flag);
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                setLanguage(iNetResult,"-1", flag);
            }
        };

        //构建请求
        PostRequest<Object> getRequest = OkGo.post(url);



        JSONObject bodyObj = null;
        try {
            bodyObj = new JSONObject(gson.toJson(params));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        getRequest.params("version", VERSION);
        getRequest.params("token", token);
        getRequest.params("appId", APPID);

//        getRequest.upFile(file,MediaType.parse("image"));
        String param = null;
//        try {
////            param = "{'fileType'='jpg','file'='"+new String(file2byte(file),"GB2312")+"'}";
//            getRequest.params("param", param);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        if (bodyObj != null) {
            L.i("================" + bodyObj);
            Iterator bodyIterator = bodyObj.keys();
            String paramsStr = "{";
            //添加参数-
            while (bodyIterator.hasNext()) {
                try {
                    String key = (String) bodyIterator.next();
                    String val = bodyObj.get(key) + "";
                    paramsStr += (key + "='" + val + "',");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (paramsStr.contains(",")) {
                paramsStr.substring(0, paramsStr.lastIndexOf(","));
            }
            paramsStr += "}";
            getRequest.params("param", paramsStr);
        }



        getRequest.params("file",file);

//        getRequest.upFile(file,MediaType.parse("image"));

        print(url, token, param);
        getRequest.tag(iNetResult).execute(jsonCall);
    }







    //请求数据没有loading
    public static void execute2(String url, Object params, Type clsType, int qType, final INetResult iNetResult, final int flag) {
        //回调
        JsonCallback jsonCall = new JsonCallback(clsType) {
            @Override
            public void onSuccess(Response response) {
                BaseObjs<ObjectBean> baseBean = (BaseObjs<ObjectBean>) response.body();
                if ("000".equals(baseBean.getCode())) {
                    if (BasicTool.isEmpty(baseBean.getData().getState())) {
                        iNetResult.onSuccess(flag, baseBean);
                    } else if ("0".equals(baseBean.getData().getState())) {
                        iNetResult.onSuccess(flag, baseBean);
                    } else {
                        iNetResult.onError(baseBean.getData().getMsg(), baseBean.getCode(), flag);
                    }
                } else {
                    iNetResult.onError(baseBean.getMessage(), baseBean.getCode(), flag);
                }
            }

            @Override
            public void onStart(Request request) {
                super.onStart(request);
//                iNetResult.requestBefore(flag);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                iNetResult.complete(flag);
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                setLanguage(iNetResult,"-1", flag);
            }
        };

        //构建请求
        PostRequest<Object> getRequest = OkGo.post(url);


        JSONObject bodyObj = null;
        try {
            bodyObj = new JSONObject(gson.toJson(params));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        getRequest.params("version", VERSION);
        getRequest.params("token", token);
        getRequest.params("appId", APPID);

        if (bodyObj != null) {
            L.i("================" + bodyObj);
            Iterator bodyIterator = bodyObj.keys();
            String paramsStr = "{";
            //添加参数-
            while (bodyIterator.hasNext()) {
                try {
                    String key = (String) bodyIterator.next();
                    String val = bodyObj.get(key) + "";
                    paramsStr += (key + "='" + val + "',");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (paramsStr.contains(",")) {
                paramsStr.substring(0, paramsStr.lastIndexOf(","));
            }
            paramsStr += "}";
            getRequest.params("param", paramsStr);
        }
        print(url, token, bodyObj.toString());
        getRequest.tag(iNetResult).execute(jsonCall);
    }




    //打印信息
    private static synchronized void print(String url, String token, String body) {
        L.i("================URL：" + url);
        L.i("================Token：" + token);
        L.i("================appid：" + APPID);
        L.i("================version：" + VERSION);
        L.i("================body：" + body);
        Logger.json(body);
    }

    public static void cancelByTag(Object tag) {
        OkGo.getInstance().cancelTag(tag);
    }


    public static byte[] file2byte(File filePath)
    {
        byte[] buffer = null;
        try
        {
            FileInputStream fis = new FileInputStream(filePath);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return buffer;
    }

    private static void setLanguage(INetResult iNetResult,String code, int flag) {
        iNetResult.onError("连接超时", code, flag);
        if ("zh".equals(CurrentLanguage.language)){
            iNetResult.onError("连接超时", code, flag);
        }else if ("en".equals(CurrentLanguage.language)){
            iNetResult.onError("connection timed out", code, flag);
        }else if ("ar".equals(CurrentLanguage.language)){
            iNetResult.onError(" مهلة الاتصال ", code, flag);
        }

    }

}
