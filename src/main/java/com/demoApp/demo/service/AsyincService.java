package com.demoApp.demo.service;


import com.demoApp.demo.entity.Attachment;
import com.demoApp.demo.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;



@Service
@RequiredArgsConstructor
public class AsyincService {
    private final AttachmentRepository attachmentRepository;

    @Async
    public Attachment upload(MultipartFile file) throws Exception {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new Exception("Filename contains invalid path sequence" + fileName);
            }
            Attachment image
                    = new Attachment(fileName,
                    file.getContentType(),
                    file.getBytes());
            return attachmentRepository.save(image);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Could not save file" + fileName);
        }


    }
}
