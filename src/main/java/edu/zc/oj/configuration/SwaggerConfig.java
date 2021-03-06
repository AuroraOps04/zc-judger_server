package edu.zc.oj.configuration;

import org.omg.CORBA.Any;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * @author keep-looking
 * @date 2021/3/6 - 14:47
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {
    /**
     * Select Swagger's enforceable environment and configure which interfaces to scan
     * @param environment
     * @return
     */

    @Bean
    public Docket docket(Environment environment){
        Profiles dev = Profiles.of("dev");
        boolean flag = environment.acceptsProfiles(dev);

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiinfo())
                .groupName("zc-judger_server")
                .enable(flag)
                .select()
                .apis(RequestHandlerSelectors.any())
                .build();
    }

    /**
     * author info
     * @return
     */
    private ApiInfo apiinfo() {
        Contact contact = new Contact("陶大锐和王晓玲", "https://www.cnblogs.com/c-aha", "5239604@qq.com");
        return new ApiInfo(
                "zc-judger_server的API文档",
                "什么都是从0到1",
                "v1.0",
                "https://www.cnblogs.com/c-aha",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}
