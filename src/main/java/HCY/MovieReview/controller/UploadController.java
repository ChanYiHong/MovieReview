package HCY.MovieReview.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Log4j2
public class UploadController {

    @PostMapping("/uploadAjax")
    public void uploadFile(MultipartFile[] uploadFiles){
        for (MultipartFile uploadFile : uploadFiles) {

            String originalFilename = uploadFile.getOriginalFilename();
            String fileName = originalFilename.substring(originalFilename.lastIndexOf("\\") + 1);

            log.info(fileName);
        }
    }

}
