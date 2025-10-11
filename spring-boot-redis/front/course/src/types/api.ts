// 通用 API 响应格式
export interface ApiResponse<T = any> {
  code: number;     // 状态码
  msg: string;  // 提示信息
  data: T;          // 返回的数据泛型
}

// 用户类型定义
export interface User {
  id: number;          // 用户 ID
  name: string;        // 用户姓名
  email: string;       // 用户邮箱
  avatar?: string;     // 用户头像（可选）
  createTime: string;  // 创建时间
}

export interface LoginRequest { 
    phone: string;
    code: string;
}

