package com.demoApp.demo.service;


import com.demoApp.demo.entity.Attachment;
import com.demoApp.demo.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final AsyincService asyincService;

    public Attachment uploadImage(MultipartFile file) throws Exception {

       // asyincService.upload(file);


        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try{
            if(fileName.contains("..")){
                throw  new Exception("Filename contains invalid path sequence" +fileName);
            }
            Attachment image
                    = new Attachment(fileName,
                    file.getContentType(),
                    file.getBytes());
            return attachmentRepository.save(image);
        } catch (Exception e){

            throw new Exception("Could not save file"+fileName);
        }
      /*  imageDataRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes())).build());

        return new ImageUploadResponse("Image uploaded successfully: " +
                file.getOriginalFilename());*/

       // return null;
    }

    public Attachment getFile(String fileId) throws Exception {
        return attachmentRepository.findById(fileId)
                .orElseThrow(() -> new Exception("File Not Found with Id : " + fileId));
    }
}
