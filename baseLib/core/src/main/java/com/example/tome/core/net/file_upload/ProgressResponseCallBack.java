/*
 * Copyright (C) 2017 zhouyou(478319399@qq.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.tome.core.net.file_upload;

/**
 * Created by XQ Yang on 2017-9-25 11:19:44.
 * Description : 上传下载进度回调
 */
public interface ProgressResponseCallBack {
    /**
     * 回调进度
     *
     * @param bytesWritten  当前读取响应体字节长度
     * @param contentLength 总长度
     * @param done          是否读取完成
     */
    void onResponseProgress(long bytesWritten, long contentLength, boolean done);
}
