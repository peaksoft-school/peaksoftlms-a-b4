package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.OptionResponse;
import kg.peaksoft.peaksoftlmsab4.api.payload.ResultResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.OptionEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ResultViewMapper {

    public ResultResponse viewResult(ResultEntity resultEntity) {


        ResultResponse resultResponse = new ResultResponse();

        resultResponse.setStudentName(resultEntity.getStudentName());
        resultResponse.setCorrect(resultEntity.getCorrect());
        resultResponse.setError(resultEntity.getError());
        resultResponse.setPoint(resultEntity.getPoints());
        resultResponse.setLocalDate(resultEntity.getLocalDate());

        return resultResponse;
    }
    public List<ResultResponse> viewResults(List<ResultEntity> results) {

        List<ResultResponse> resultResponses = new ArrayList<>();
        for (ResultEntity r : results) {
            resultResponses.add(viewResult(r));
        }
        return resultResponses;
    }
}
