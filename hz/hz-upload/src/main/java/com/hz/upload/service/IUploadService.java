package com.hz.upload.service;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {
    public String upload(MultipartFile file);
}
