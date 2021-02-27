package com.sanket.safewe;

import android.hardware.usb.UsbRequest;

public class LoginResponse {
    public boolean success;
    public String uid;
    public String msg;
    public Users result;


    public LoginResponse(boolean success, String uid, String msg, Users result) {
        this.success = success;
        this.uid = uid;
        this.msg = msg;
        this.result = result;
    }



    public boolean isSuccess() {
        return success;
    }

    public String getUid() {
        return uid;
    }

    public String getMsg() {
        return msg;
    }

    public Users getUsers() {

        return result;
    }
}
