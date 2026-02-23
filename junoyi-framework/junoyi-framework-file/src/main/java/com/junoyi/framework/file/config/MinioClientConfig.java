package com.junoyi.framework.file.config;

import com.junoyi.framework.file.properties.FileStorageProperties;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioClientConfig {

    @Autowired
    private FileStorageProperties fileStorageProperties;

    @Bean
    public MinioClient minioClient() {
        FileStorageProperties.MinioConfig minio = fileStorageProperties.getMinio();
        return MinioClient.builder()
                .endpoint(minio.getEndpoint())
                .credentials(minio.getAccessKey(), minio.getSecretKey())
                .build();
    }
}