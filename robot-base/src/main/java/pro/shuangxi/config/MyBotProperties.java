package pro.shuangxi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author mengshx
 * @description <p></p>
 * @date 2022-07-21 15:28
 **/
@ConfigurationProperties(prefix = "bot")
public class MyBotProperties {
    /**
     * QQ账号
     */
    Long account;
    /**
     * QQ密码
     */
    String password;

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
