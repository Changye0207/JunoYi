package com.junoyi.framework.file.controller;

import com.junoyi.framework.core.domain.module.R;
import com.junoyi.framework.file.util.MinioUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "文件管理", description = "MinIO文件上传和删除接口")
@RestController
@RequestMapping("/common/file")
public class FileController {

    @Autowired
    private MinioUtil minioUtil;

    /**
     * 上传文件
     */
    @Operation(summary = "上传文件", description = "上传文件到 MinIO 存储")
    @PostMapping("/upload")
    public R<String> upload(@RequestParam("file") MultipartFile file) {
        String url = minioUtil.uploadFile(file);
        return R.ok(url);
    }

    /**
     * 删除文件
     */
    @Operation(summary = "删除文件", description = "从 MinIO 存储删除文件")
    @DeleteMapping("/delete")
    public R<Void> delete(@RequestParam("fileName") String fileName) {
        minioUtil.deleteFile(fileName);
        return R.ok();
    }
}
