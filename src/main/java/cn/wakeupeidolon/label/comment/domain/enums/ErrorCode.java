package cn.wakeupeidolon.label.comment.domain.enums;

public enum ErrorCode {
    
    SUCCESS(0, "success"),
    USER_REPEAT(-501, "该用户已注册"),
    NOT_REGISTER(-502, "该邮箱尚未注册"),
    INVALID_CODE(-503, "输入的验证信息不正确"),
    CANNOT_ACCESS(-401, "禁止访问"),
    UNKNOWN_ERROR(-1, "服务器内部错误")
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
