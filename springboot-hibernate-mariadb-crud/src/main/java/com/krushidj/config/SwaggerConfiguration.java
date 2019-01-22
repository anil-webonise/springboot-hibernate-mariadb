package com.krushidj.config;

import com.krushidj.constants.PackageConstatnt;
import com.krushidj.constants.SwaggerMetaDataInfoConstant;
import com.krushidj.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile(SwaggerMetaDataInfoConstant.swaggerProfile)
public class SwaggerConfiguration {


    @Value("${api.version}")
    private String version;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(CommonUtil.getAllControllerPackage()))
                .build()
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(SwaggerMetaDataInfoConstant.swaggerTitle)
                .description(SwaggerMetaDataInfoConstant.swaggerDesciption)
                .version(version)
                .build();
    }
}
