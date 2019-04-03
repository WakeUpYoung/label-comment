package cn.wakeupeidolon.label.comment.domain.enums;

public enum ErrorCode {
    
    SUCCESS(0, "success"),
    USER_REPEAT(-501, "该用户已注册"),
    INVALID_CODE(-502, "输入的验证信息不正确"),
    UNKNOWN_ERROR(-1, "未知错误")
    ;
    
    private Integer code;
    private String errMsg;
    
    ErrorCode(Integer code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }
    
    public Integer getCode() {
        return code;
    }
    
    public void setCode(Integer code) {
        this.code = code;
    }
    
    public String getErrMsg() {
        return errMsg;
    }
    
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
