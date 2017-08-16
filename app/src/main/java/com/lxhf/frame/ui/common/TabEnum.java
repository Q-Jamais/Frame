package com.lxhf.frame.ui.common;

/**
 * Created by Jamais on 17/8/15.
 * E-mail : liutl@hfvast.com
 */
public enum TabEnum {

    CAMERA("相机"),
    SLIDERS("滑动器"),
    SPINNERS("下/上拉列表"),
    TEXTFIELDS("文本域"),
    SNACKBARS("SnackBar");

    TabEnum(String s) {
    }

    public static TabEnum[] getTabArr() {
        return new TabEnum[]{CAMERA, SLIDERS, SPINNERS, TEXTFIELDS, SNACKBARS};
    }
}
