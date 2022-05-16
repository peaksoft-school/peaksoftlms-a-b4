package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.ResultRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.ResultResponse;
import kg.peaksoft.peaksoftlmsab4.model.mapper.ResultMapper;
import kg.peaksoft.peaksoftlmsab4.repository.QuestionRepository;
import kg.peaksoft.peaksoftlmsab4.repository.ResultRepository;
import kg.peaksoft.peaksoftlmsab4.repository.TestStudentRepository;
import kg.peaksoft.peaksoftlmsab4.service.ResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;
    private final ResultMapper editMapper;

    @Override
    public ResultResponse createResult(ResultRequest resultRequest) {

        return editMapper.viewResult(resultRepository.save(editMapper.createResult(resultRequest)));

    }

    @Override
    public List<ResultResponse> getAll() {
        return editMapper.viewResults(resultRepository.findAll());
    }
}
