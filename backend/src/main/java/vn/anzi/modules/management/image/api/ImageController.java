package vn.anzi.modules.management.image.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import vn.anzi.modules.management.image.services.FileManageService;

@RestController
@RequestMapping(path = "/management/image")
public class ImageController implements WebMvcConfigurer {

    @Autowired
    FileManageService fileManageService;

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configure) {
        configure.setDefaultTimeout(86_400_000L);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<Object> getPdf(@PathVariable String fileName) {
        byte[] content = null;
        try {
            content = fileManageService.getFileObject(fileName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(content);
    }
}
