package WeatherPick.weatherpick.domain.review.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import WeatherPick.weatherpick.common.ResponseDto;
import WeatherPick.weatherpick.domain.review.dto.ImageUrlListResponseDto;
import WeatherPick.weatherpick.domain.review.entity.ImageEntity;
import WeatherPick.weatherpick.domain.review.repository.ImageRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class S3Service {

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;
    
    private final AmazonS3Client s3Client;
    private final ImageRepository imageRepository;

    public S3Service(AmazonS3Client s3Client, ImageRepository imageRepository) {
        this.s3Client = s3Client;
        this.imageRepository = imageRepository;
    }

    public String upload(MultipartFile file) {
        if(file.isEmpty()) return null;

        try {
            /* 업로드할 파일의 이름을 변경 */
            String originalFileName = file.getOriginalFilename();
            String fileName = changeFileName(originalFileName);

            /* S3에 업로드할 파일의 메타데이터 생성 */
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());

            /* S3에 파일 업로드 */
            s3Client.putObject(bucket, fileName, file.getInputStream(), metadata);

            /* 업로드한 파일의 S3 URL 주소 반환 */
            return s3Client.getUrl(bucket, fileName).toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private String changeFileName(String originalFileName) {
        /* 업로드할 파일의 이름을 변경하는 로직 */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return originalFileName + "_" + LocalDateTime.now().format(formatter);
    }

    public Resource getImage(String fileName) {
        try {
            return new UrlResource(s3Client.getUrl(bucket, fileName));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResponseEntity<? super ImageUrlListResponseDto> getImageUrlsByPostId(Long postId) {
        try {
            List<ImageEntity> images = imageRepository.findByReviewPostEntity_ReviewPostId(postId);
            if (images.isEmpty()) return ImageUrlListResponseDto.success(null);

            List<String> imageUrls = images.stream()
                    .map(ImageEntity::getImage)
                    .collect(Collectors.toList());

            return ImageUrlListResponseDto.success(imageUrls);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    public boolean deleteFile(String fileUrl) {
        try {
            // URL에서 파일 이름 추출
            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            // S3에서 파일 삭제
            s3Client.deleteObject(bucket, fileName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
} 