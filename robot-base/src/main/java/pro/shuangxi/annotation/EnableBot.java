package pro.shuangxi.annotation;

import org.springframework.context.annotation.Import;
import pro.shuangxi.config.BotAutoConfig;

import java.lang.annotation.*;

/**
 * @author mengshx
 * @description 额，BotAutoConfig已经注册到自动启动了，这个注解就没用了
 * @date 2022-07-21 14:51
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE}) //接口、类、枚举、注解、方法
@Documented
@Import({
        BotAutoConfig.class,
})
public @interface EnableBot {
}
