package top.zby.common;

import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

/**
 * @Author: mqxu
 * @Date: 2025/9/24
 * @Description: 通用工具类
 */
public class CommonUtils {

    /**
     * 生成一个四位的数字验证码。
     * @return 四位数字验证码（int类型）
     */
    public static int generateCode() {
        // 生成一个1000到9999之间的随机整数并返回
        return ThreadLocalRandom.current().nextInt(1000, 10000);
    }

    /**
     * 校验手机号格式。
     * @param phone 手机号
     * @return 如果格式正确返回true，否则返回false
     */
    public static boolean checkPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }
        String regex = "^(13|14|15|16|17|18|19)\\d{9}$";
        return Pattern.matches(regex, phone);
    }
}