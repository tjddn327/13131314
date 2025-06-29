package WeatherPick.weatherpick.domain.review.dto;

import WeatherPick.weatherpick.common.ResponseCode;
import WeatherPick.weatherpick.common.ResponseDto;
import WeatherPick.weatherpick.common.ResponseMassage;
import WeatherPick.weatherpick.domain.review.repository.GetReviewCommentResultSet;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetCommentListResponseDto extends ResponseDto {
    private List<ReviewCommentListItem> commentList;
    private GetCommentListResponseDto(List<GetReviewCommentResultSet> resultSets){
        super(ResponseCode.SUCCESS, ResponseMassage.SUCCESS);
        this.commentList = ReviewCommentListItem.copyList(resultSets);
    }

    public static ResponseEntity<GetCommentListResponseDto> success(List<GetReviewCommentResultSet> resultSets){
        GetCommentListResponseDto result = new GetCommentListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    public static ResponseEntity<ResponseDto> noExistReview(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXIDSTED_REVIEW,ResponseMassage.NOT_EXIDSTED_REVIEW);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }


}
