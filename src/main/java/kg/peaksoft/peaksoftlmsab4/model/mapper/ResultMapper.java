//package kg.peaksoft.peaksoftlmsab4.model.mapper;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class ResultMapper {
////    private final QuestionRepository questionRepository;
////    private final TestStudentRepository testStudentRepository;
////
////    public ResultEntity createResult(ResultRequest resultRequest) {
////        int correct = 0;
////        int wrong = 0;
////        int point;
////
////        List<TestStudentEntity> testStudents = testStudentRepository.findAll();
////
////        ResultEntity resultEntity = new ResultEntity();
////        resultEntity.setAccepted(resultRequest.getAccepted());
////
////        resultEntity.setStudentName("Nurmuhammed");
////
////        if (resultEntity.getAccepted()) {
////            for (TestStudentEntity t : testStudents) {
////
////
////                if (t.getIsTrue()) {
////                    correct++;
////                    resultEntity.setCorrect(correct);
////                } else {
////                    wrong++;
////                    resultEntity.setError(wrong);
////                }
////            }
////
////            point = (correct * 100 / questionRepository.findAll().size());
////            resultEntity.setPoints(point);
////
////
////            resultEntity.setLocalDate(LocalDate.now());
////
////
////        }
////        return resultEntity;
////    }
////
////
////    public ResultResponse viewResult(ResultEntity resultEntity) {
////
////
////        ResultResponse resultResponse = new ResultResponse();
////
////        resultResponse.setStudentName(resultEntity.getStudentName());
////        resultResponse.setCorrect(resultEntity.getCorrect());
////        resultResponse.setError(resultEntity.getError());
////        resultResponse.setPoint(resultEntity.getPoints());
////        resultResponse.setLocalDate(resultEntity.getLocalDate());
////
////        return resultResponse;
////    }
////
////    public List<ResultResponse> viewResults(List<ResultEntity> results) {
////
////        List<ResultResponse> resultResponses = new ArrayList<>();
////        for (ResultEntity r : results) {
////            resultResponses.add(viewResult(r));
////        }
////        return resultResponses;
////    }
//
//}
