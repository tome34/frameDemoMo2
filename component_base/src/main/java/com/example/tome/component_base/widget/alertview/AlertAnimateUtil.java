//package com.example.tome.component_base.widget.alertview;
//
//import android.view.Gravity;
//
//import com.example.tome.component_base.R;
//
//
///**
// * Created by Sai on 15/8/9.
// */
//public class AlertAnimateUtil {
//    private static final int INVALID = -1;
//    /**
//     * Get default animation resource when not defined by the user
//     *
//     * @param gravity       the gravity of the dialog
//     * @param isInAnimation Confirm if is in or out animation. true when is is
//     * @return the id of the animation resource
//     */
//    static int getAnimationResource(int gravity, boolean isInAnimation) {
//        switch (gravity) {
//            case Gravity.BOTTOM:
//                return isInAnimation ? R.anim.otherui_slide_in_bottom : R.anim.otherui_slide_out_bottom;
//            case Gravity.CENTER:
//                return isInAnimation ? R.anim.otherui_fade_in_center : R.anim.otherui_fade_out_center;
//        }
//        return INVALID;
//    }
//}
