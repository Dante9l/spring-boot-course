import request from "../utils/request";
import type { LoginRequest, ApiResponse } from "../types/api";

/**
 * 认证相关 API
 * 只包含后端实际提供的接口
 */
export const authApi = {
  /**
   * 发送短信验证码
   * @param phone 手机号
   */
  sendSms(phone: string): Promise<ApiResponse> {
    return request
      .get(`/sms/send?phone=${phone}`)
      .then((res) => res.data);
  },

  /**
   * 用户登录（短信验证码登录）
   * @param data 登录请求参数
   */
  login(data: LoginRequest): Promise<ApiResponse> {
    return request
      .post("/login", data)
      .then((res) => res.data);
  },
};
