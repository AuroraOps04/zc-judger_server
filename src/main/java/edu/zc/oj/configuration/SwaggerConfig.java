package edu.zc.oj.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author keep-looking
 * @date 2021/3/6 - 14:47
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * Select Swagger's enforceable environment and configure which interfaces to scan
     *
     * @param environment
     * @return
     */

    @Bean
    public Docket docket(Environment environment) {
        Profiles dev = Profiles.of("dev");
        boolean flag = environment.acceptsProfiles(dev);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .groupName("zc-judge_server")
                .enable(flag)
                .select()
                .apis(RequestHandlerSelectors.basePackage("edu.zc.oj.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * author info
     *
     * @return
     */
    private ApiInfo apiInfo() {
        Contact contact = new Contact("陶大锐和王晓玲", "https://www.cnblogs.com/c-aha", "5239604@qq.com");
        return new ApiInfoBuilder()
                .title("zc-judge_server的API文档")
                .version("1.0")
                .contact(contact)
                .build();
    }
}
