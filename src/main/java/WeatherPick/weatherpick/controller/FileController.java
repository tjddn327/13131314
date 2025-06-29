package WeatherPick.weatherpick.controller;

import WeatherPick.weatherpick.domain.review.dto.ImageUrlListResponseDto;
import WeatherPick.weatherpick.domain.review.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final S3Service service;

    @PostMapping("/upload")
    public String upload(
            @RequestParam(value = "file", required = true) MultipartFile file
    ){
        return service.upload(file);
    }

    @GetMapping(value = "{fileName}",produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE})
    public Resource getImage(
        @PathVariable("fileName") String fileName
    ){
        return service.getImage(fileName);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<? super ImageUrlListResponseDto> getPostImages(@PathVariable("postId") Long postId) {
        return service.getImageUrlsByPostId(postId);
    }

    @DeleteMapping("/{fileUrl}")
    public boolean deleteFile(@PathVariable("fileUrl") String fileUrl) {
        return service.deleteFile(fileUrl);
    }
}
