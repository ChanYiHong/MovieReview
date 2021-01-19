package HCY.MovieReview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Data
@AllArgsConstructor
public class UploadResponseDTO {

    private String fileName;
    private String uuid;
    private String folderPath;

    // 전체 경로가 필요한 경우를 대비해서 만든 메서드.
    public String getImageURL() {
        try {
            return URLEncoder
                    .encode(folderPath + "/" + uuid + "_" + fileName, StandardCharsets.UTF_8);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public String getThumbnailURL() {
        try {
            return URLEncoder.encode(folderPath+"/s_"+uuid+"_"+fileName, "UTF-8");
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return "";
    }
}
