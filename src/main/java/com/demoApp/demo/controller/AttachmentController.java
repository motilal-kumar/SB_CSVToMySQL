package com.demoApp.demo.controller;


import com.demoApp.demo.entity.Attachment;
import com.demoApp.demo.model.ResponseData;
import com.demoApp.demo.service.AttachmentService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/api/v1")
public class AttachmentController {

    private AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping("/upload")
    public ResponseData uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
        Attachment image = null;
        image  = attachmentService.uploadImage(file);

        String downloadURI = "";
        downloadURI = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download")
                .path(image.getId())
                .toUriString();
        return new ResponseData(image.getFileName(), downloadURI,
                file.getContentType(),
                file.getSize());
    }

    @GetMapping("/download/{fileId}")
    @Async
    public ResponseEntity<Resource>  downloadFile(@PathVariable String fileId) throws Exception {

        Attachment image = null;

        image  =  attachmentService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "image: fileName=\""+image.getFileName()
                +"\"")
                .body(new ByteArrayResource(image.getData()));
    }
}
