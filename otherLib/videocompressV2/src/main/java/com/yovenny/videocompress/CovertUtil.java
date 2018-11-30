package com.yovenny.videocompress;

import java.nio.ByteBuffer;

public class CovertUtil {

    public native static int convertVideoFrame(ByteBuffer src, ByteBuffer dest, int destFormat, int width, int height, int padding, int swap);

    static {
        System.loadLibrary("compress");
    }
}
