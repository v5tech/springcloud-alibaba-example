package com.samples.nacos;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.listener.Listener;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * Nacos配置中心
 */
@SpringBootApplication
public class SamplesNacosExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SamplesNacosExampleApplication.class, args);
    }

    @Bean
    public UserConfig userConfig() {
        return new UserConfig();
    }

}

@Data
@ConfigurationProperties(prefix = "user")
class UserConfig {

    private int age;

    private String name;

    private Map<String, Object> map;

    @Override
    public String toString() {
        return "UserConfig{" + "age=" + age + ", name='" + name + '\'' + ", map=" + map + '}';
    }

}

@Component
class SampleRunner implements ApplicationRunner {

    @Value("${user.name}")
    String userName;

    @Value("${user.age:25}")
    int userAge;

    @Autowired
    private NacosConfigManager nacosConfigManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println(String.format("Initial username=%s, userAge=%d", userName, userAge));

        StringBuilder content = new StringBuilder();
        content.append("user.name=lisi");
        content.append(System.lineSeparator());
        content.append("user.age=36");
        content.append(System.lineSeparator());
        content.append("user.map[2]=false");

        // 发布配置文件
        nacosConfigManager.getConfigService().publishConfig("nacos-config-example.properties","DEFAULT_GROUP",content.toString());

        nacosConfigManager.getConfigService().addListener(
                "nacos-config-example.properties", "DEFAULT_GROUP", new Listener() {
                    @Override
                    public void receiveConfigInfo(String configInfo) {
                        Properties properties = new Properties();
                        try {
                            properties.load(new StringReader(configInfo));
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("config changed: " + properties);
                    }
                    @Override
                    public Executor getExecutor() {
                        return null;
                    }
                });
    }

}

@RestController
@RefreshScope
class SampleController {

    @Autowired
    UserConfig userConfig;

    @Autowired
    private NacosConfigManager nacosConfigManager;

    @Value("${user.name}")
    String userName;

    @Value("${user.age:25}")
    Integer age;

    @RequestMapping("/user")
    public String simple() {
        return "Hello Nacos Config!" + "Hello " + userName + " " + age + " [UserConfig]: " + userConfig + "!" + nacosConfigManager.getConfigService();
    }

    @RequestMapping("/bool")
    public boolean bool() {
        return Boolean.parseBoolean(userConfig.getMap().get("2").toString());
    }

}
