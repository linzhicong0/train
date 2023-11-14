package org.jack.common.exception;


public enum BusinessExceptionEnum {
    MEMBER_MOBILE_EXIST("手机号已存在"),
    MEMBER_MOBILE_NOT_EXIST("手机号不存在"),

    MEMBER_VERIFICATION_CODE_INCORRECT("验证码错误"),

    ;

    private String desc;

    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    BusinessExceptionEnum(String desc) {
        this.desc = desc;
    }
    @Override
    public String toString() {
        return "BusinessExceptionEnum{" +
                "desc='" + desc + '\'' +
                '}';
    }
}
