<template>
  <div class="login-container">
    <el-card class="login-card">
      <template #header>
        <div class="card-header">
          <el-icon><Iphone /></el-icon>
          <span class="title">短信登录</span>
        </div>
      </template>

      <el-form
        ref="loginFormRef"
        :model="formData"
        :rules="rules"
        @submit.prevent="login"
      >
        <!-- 手机号 -->
        <el-form-item prop="phone">
          <el-input
            v-model="formData.phone"
            placeholder="请输入手机号"
            size="large"
            :prefix-icon="Iphone"
            maxlength="11"
            show-word-limit
          />
        </el-form-item>

        <!-- 发送验证码按钮 -->
        <el-form-item>
          <el-button
            type="primary"
            size="large"
            style="width: 100%"
            :disabled="isCountingDown"
            :loading="isSendingSms"
            @click="sendSms"
          >
            <el-icon><Message /></el-icon>
            {{ isCountingDown ? `${countdown}秒后重发` : "发送验证码" }}
          </el-button>
        </el-form-item>

        <!-- 验证码输入与登录按钮（发送后显示） -->
        <div v-show="isCodeSent">
          <el-form-item prop="code">
            <el-input
              v-model="formData.code"
              placeholder="请输入验证码"
              size="large"
              :prefix-icon="Lock"
              maxlength="4"
              show-word-limit
            />
          </el-form-item>

          <el-form-item>
            <el-button
              type="success"
              size="large"
              style="width: 100%"
              :loading="isLoggingIn"
              @click="login"
            >
              <el-icon><Check /></el-icon>
              登录
            </el-button>
          </el-form-item>
        </div>

        <!-- 提示信息 -->
        <el-alert
          v-if="errorMessage"
          :title="errorMessage"
          type="error"
          :closable="false"
          show-icon
        />
        <el-alert
          v-if="successMessage"
          :title="successMessage"
          type="success"
          :closable="false"
          show-icon
        />
      </el-form>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, watch } from "vue";
import { useRouter } from "vue-router";
import { ElMessage } from "element-plus";
import type { FormRules } from "element-plus/es/components/form";
import type { FormInstance } from "element-plus/es/components/form";
import { Iphone, Message, Lock, Check } from "@element-plus/icons-vue";
import type { LoginRequest } from "../types/api";
import { authApi } from "../api/auth";




// UI / 状态
const router = useRouter();
const phone = ref("13951905171");
const code = ref("");
const errorMessage = ref("");
const successMessage = ref("");
const isCountingDown = ref(false);
const countdown = ref(60);
const isSendingSms = ref(false);
const isLoggingIn = ref(false);
const isCodeSent = ref(false);

// 表单数据
const formData = reactive({
  phone: phone,
  code: code,
});

// 表单与校验
const loginFormRef = ref<FormInstance>();


const rules: FormRules = {
  phone: [
    { required: true, message: "请输入手机号", trigger: "blur" },
    { pattern: /^1[3-9]\d{9}$/, message: "请输入正确的手机号", trigger: "blur" },
  ],
  code: [
    { required: true, message: "请输入验证码", trigger: "blur" },
    { len: 4, message: "验证码长度为4位", trigger: "blur" },
  ],
};

// 发送验证码
const sendSms = async () => {
  if (!loginFormRef.value) return;

  const valid = await loginFormRef.value
    .validateField("phone")
    .then(() => true)
    .catch(() => false);
  if (!valid || isCountingDown.value) return;

  errorMessage.value = "";
  successMessage.value = "";
  isSendingSms.value = true;

  try {
    const response = await authApi.sendSms(formData.phone);
    if (response.code === 0) {
      successMessage.value = "验证码已发送!";
      ElMessage.success("验证码已发送，请查收！");
      isCodeSent.value = true;
      startCountdown();
    } else {
      const msg = response.msg || "发送失败，请重试。";
      errorMessage.value = msg;
      ElMessage.error(msg);
    }
  } catch {
    const errorMsg = "请求失败，请稍后再试。";
    errorMessage.value = errorMsg;
    ElMessage.error(errorMsg);
  } finally {
    isSendingSms.value = false;
  }
};

// 登录
const login = async () => {
  if (!loginFormRef.value) return;

  const validAll = await loginFormRef.value.validate().catch(() => false);
  if (!validAll) return;

  errorMessage.value = "";
  successMessage.value = "";
  isLoggingIn.value = true;

  try {
    const loginData: LoginRequest = {
      phone: formData.phone,
      code: formData.code,
    };

    const response = await authApi.login(loginData);
    const loginResult = response.data;
    console.log(loginResult);

    if (loginResult.token) {
      localStorage.setItem("token", loginResult.token);
      localStorage.setItem("userPhone", loginResult.phone);
      localStorage.setItem("loginTime", new Date().toISOString());

      successMessage.value = loginResult.message || "登录成功!";
      ElMessage.success(loginResult.message || "登录成功！");
      router.push("/home");
    } else {
      const msg = loginResult.message || "登录失败，请重试。";
      errorMessage.value = msg;
      ElMessage.error(msg);
    }
  } catch {
    const errorMsg = "请求失败，请稍后再试。";
    errorMessage.value = errorMsg;
    ElMessage.error(errorMsg);
  } finally {
    isLoggingIn.value = false;
  }
};

// 倒计时
let timer: ReturnType<typeof setInterval>;

const startCountdown = () => {
  isCountingDown.value = true;
  countdown.value = 60;

  timer = setInterval(() => {
    if (countdown.value > 0) {
      countdown.value--;
    } else {
      clearInterval(timer);
      isCountingDown.value = false;
    }
  }, 1000);
};

// 清理定时器
watch(isCountingDown, (newVal) => {
  if (!newVal) clearInterval(timer);
});
</script>

<style scoped>
.login-container {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.login-card {
  width: 100%;
  max-width: 400px;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
  border-radius: 15px;
  overflow: hidden;
}

.card-header {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  font-size: 20px;
  font-weight: 600;
  color: #409eff;
}

.card-header .el-icon {
  font-size: 24px;
}

.title {
  background: linear-gradient(45deg, #409eff, #67c23a);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

:deep(.el-form-item) {
  margin-bottom: 20px;
}

:deep(.el-input__wrapper) {
  border-radius: 8px;
}

:deep(.el-button) {
  border-radius: 8px;
  font-weight: 500;
  transition: all 0.3s ease;
}

:deep(.el-button:hover) {
  transform: translateY(-1px);
  box-shadow: 0 6px 20px rgba(0, 0, 0, 0.15);
}

:deep(.el-alert) {
  border-radius: 8px;
  margin-top: 10px;
}

:deep(.el-card__header) {
  background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
  border-bottom: 1px solid #e4e7ed;
}

:deep(.el-card__body) {
  padding: 30px;
}
</style>
