package cn.edu.scnu.model;

public class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    // 解决前端报406，无法解析服务端返回的内容，自动解析json需要getter方法
    public String getMessage() {
        return message;
    }
}
