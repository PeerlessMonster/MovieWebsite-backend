package cn.edu.scnu.VO;

import cn.edu.scnu.DTO.ErrorResponse;

public class ErrorVO {
    private static final String[] typeToMessage = new String[]{
        "",
        "未登录账号！",
        "账号不存在！",
        "该邮箱已被注册！",
        "密码错误！",
        "新密码与旧密码相同！",
        "没有符合条件的结果！"
    };

    private String message;

    public ErrorVO(ErrorResponse errorType) {
        this.message = typeToMessage[errorType.ordinal()];
    }

    /* 解决前端报406，无法解析服务端返回的内容，自动解析json需要getter方法 */
    public String getMessage() {
        return message;
    }
}
