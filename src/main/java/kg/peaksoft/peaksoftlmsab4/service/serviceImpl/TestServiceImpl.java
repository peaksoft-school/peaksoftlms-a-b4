package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.TestRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestResponse;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.TestEditMapper;
import kg.peaksoft.peaksoftlmsab4.model.mapper.TestViewMapper;
import kg.peaksoft.peaksoftlmsab4.repository.TestRepository;
import kg.peaksoft.peaksoftlmsab4.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestViewMapper viewMapper;
    private final TestRepository repository;
    private final TestEditMapper editMapper;

    @Override
    public TestResponse create(TestRequest testRequest) {
        return viewMapper.viewTest(repository.save(editMapper.create(testRequest)));
    }

    @Override
    public TestResponse update(Long id, TestRequest testRequest) {
        TestEntity test = repository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("test not found this=%s", id)));
        editMapper.update(test,testRequest);

        return viewMapper.viewTest(repository.save(test));
    }

    @Override
    public TestResponse findById(Long id) {
        return viewMapper.viewTest(repository.findById(id).get());
    }

    @Override
    public TestResponse deleteById(Long id) {
        TestEntity test = repository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("test not found this=%s", id)));
        repository.deleteById(id);
        return viewMapper.viewTest(test);
    }

    @Override
    public List<TestResponse> findAll() {
        return viewMapper.viewTests(repository.findAll());
    }
}
