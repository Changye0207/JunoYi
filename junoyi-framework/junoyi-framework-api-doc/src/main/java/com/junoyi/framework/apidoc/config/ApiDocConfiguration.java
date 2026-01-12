package com.junoyi.framework.apidoc.config;

import com.junoyi.framework.apidoc.properties.ApiDocProperties;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * API 文档自动配置类
 * 基于 Knife4j + SpringDoc OpenAPI 3
 *
 * @author Fan
 */
@Configuration
@EnableConfigurationProperties(ApiDocProperties.class)
@ConditionalOnProperty(prefix = "junoyi.api-doc", name = "enable", havingValue = "true", matchIfMissing = true)
public class ApiDocConfiguration {

    private static final String SECURITY_SCHEME_NAME = "Authorization";

    /**
     * 创建 OpenAPI 配置
     */
    @Bean
    public OpenAPI openAPI(ApiDocProperties properties) {
        return new OpenAPI()
                .info(buildInfo(properties))
                .externalDocs(buildExternalDocs(properties))
                // 添加全局安全认证
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .schemaRequirement(SECURITY_SCHEME_NAME, new SecurityScheme()
                        .name(SECURITY_SCHEME_NAME)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .in(SecurityScheme.In.HEADER)
                        .description("JWT Token 认证，格式: Bearer {token}"));
    }

    /**
     * 构建文档基本信息
     */
    private Info buildInfo(ApiDocProperties properties) {
        Info info = new Info()
                .title(properties.getTitle())
                .description(properties.getDescription())
                .version(properties.getVersion());

        // 设置服务条款
        if (properties.getTermsOfService() != null) {
            info.termsOfService(properties.getTermsOfService());
        }

        // 设置联系人信息
        ApiDocProperties.Contact contactProps = properties.getContact();
        if (contactProps != null) {
            Contact contact = new Contact()
                    .name(contactProps.getName())
                    .email(contactProps.getEmail())
                    .url(contactProps.getUrl());
            info.contact(contact);
        }

        // 设置许可证信息
        ApiDocProperties.License licenseProps = properties.getLicense();
        if (licenseProps != null) {
            License license = new License()
                    .name(licenseProps.getName())
                    .url(licenseProps.getUrl());
            info.license(license);
        }

        return info;
    }

    /**
     * 构建外部文档
     */
    private ExternalDocumentation buildExternalDocs(ApiDocProperties properties) {
        ApiDocProperties.ExternalDocs externalDocsProps = properties.getExternalDocs();
        if (externalDocsProps == null || externalDocsProps.getUrl() == null) {
            return null;
        }
        return new ExternalDocumentation()
                .description(externalDocsProps.getDescription())
                .url(externalDocsProps.getUrl());
    }

    /**
     * 创建默认分组（全部接口）
     */
    @Bean
    public GroupedOpenApi defaultGroup() {
        return GroupedOpenApi.builder()
                .group("default")
                .displayName("全部接口")
                .pathsToMatch("/**")
                .build();
    }

    /**
     * 根据配置创建自定义分组
     */
    @Bean
    public List<GroupedOpenApi> customGroups(ApiDocProperties properties) {
        List<GroupedOpenApi> groups = new ArrayList<>();
        
        for (ApiDocProperties.Group groupConfig : properties.getGroups()) {
            GroupedOpenApi.Builder builder = GroupedOpenApi.builder()
                    .group(groupConfig.getName())
                    .displayName(groupConfig.getDisplayName() != null 
                            ? groupConfig.getDisplayName() 
                            : groupConfig.getName());

            // 设置路径匹配
            if (!groupConfig.getPathsToMatch().isEmpty()) {
                builder.pathsToMatch(groupConfig.getPathsToMatch().toArray(new String[0]));
            }

            // 设置包扫描
            if (!groupConfig.getPackagesToScan().isEmpty()) {
                builder.packagesToScan(groupConfig.getPackagesToScan().toArray(new String[0]));
            }

            groups.add(builder.build());
        }
        
        return groups;
    }
}
