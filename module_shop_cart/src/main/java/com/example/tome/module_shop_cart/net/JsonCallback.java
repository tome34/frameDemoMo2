package com.example.tome.module_shop_cart.net;

import com.example.tome.core.util.L;
import com.example.tome.module_shop_cart.base.BaseObjs;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.base.Request;
import com.orhanobut.logger.Logger;
import java.lang.reflect.Type;

/**
 * 类描述    : 转换JSON
 * 包名      : com.fecmobile.baselib.callback
 * 类名称    : JsonCallback
 * 创建人    : ghy
 * 创建时间  : 2017/9/11 17:22
 * 修改人    :
 * 修改时间  :
 * 修改备注  :
 */
public abstract class JsonCallback<T> extends AbsCallback<T> {
    private Type type;
    private Gson gson = new Gson();

    public JsonCallback(Type type) {
        this.type = type;
   /*     GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Integer.class,  new JsonSerializer<Integer>() {
            @Override
            public JsonElement serialize(final Integer src, final Type typeOfSrc, final JsonSerializationContext context) {
                BigDecimal value = BigDecimal.valueOf(src);

                return new JsonPrimitive(value);
            }
        });
        gson = gsonBuilder.create();*/
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
    }

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        //详细自定义的原理和文档，看这里： https://github.com/jeasonlzy/okhttp-OkGo/wiki/JsonCallback
        String resStr = response.body().string();
        String url = response.request().url().toString();
        if (true) {
            Logger.json(resStr);

            L.e("URL==========================", url);
            L.e("DATA=========================", L.format(resStr));
        }
        try {

            return gson.fromJson(resStr, type);
        } catch (Exception e) {
            e.printStackTrace();
            return gson.fromJson(resStr, new TypeToken<BaseObjs>() {
            }.getType());
        }
    }
}
