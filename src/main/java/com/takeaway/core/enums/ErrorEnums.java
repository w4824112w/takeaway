package com.takeaway.core.enums;


import com.alibaba.fastjson.JSONObject;

/**
 * @author Administrator
 * @Description: 错误代码枚举
 */
public enum ErrorEnums {
    SUCCESS(200, "成功"),
    SERVER_ERROR(-1, "服务器异常"),
    PARAM_ERROR(99999, "参数错误"),
    ILLEGAL_REQUEST(99998, "非法请求"),
	USERORPASSWORD_ERROR(20001, "用户名或密码错误"),
	NOT_LOGIN(20002, "未登录"),
	MENU_EXISTS(30001, "菜单已存在"),
	MENU_NOT_EXISTS(30002, "菜单不存在"),
	ROLE_EXISTS(30003, "角色已存在"),
	MENU_NOT_EDIT(30004, "菜单不能编辑"),
	MENU_NOT_DELETE(30005, "菜单不能删除"),
	USER_EXISTS(10004, "用户已存在"),
	NAMEORPASW_ISNULL(10005, "用户名或密码不能为空"),
	NAMEORPASW_ERROR(10006, "用户名或密码错误"),
    IMPORT_FILE_FORMAT_ERROR(70001, "导入的文件格式错误"),
    IMPORT_FILE_NOT_EXIST(70002, "导入的文件不存在"),
    EXPORT_FILE_FAIL(70009, "导出失败");


    private int code;
    private String msg;


    private ErrorEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static String getResponseMsg(String code) {
        for (ErrorEnums responseInfo : ErrorEnums.values()) {
            if (code.equals(responseInfo.getCode())) {
                return responseInfo.getMsg();
            }
        }
        return SERVER_ERROR.getMsg();
    }

    public static JSONObject getResult(ErrorEnums error, String additionalMsg, Object data) {
        String msg = (additionalMsg == null ? "" : additionalMsg) + error.getMsg();
        JSONObject result = new JSONObject();
        result.put("code", error.getCode());
        result.put("msg", msg);
        if (data == null) {
            data = new JSONObject();
        }
        result.put("data", data);
        return result;
    }

}
