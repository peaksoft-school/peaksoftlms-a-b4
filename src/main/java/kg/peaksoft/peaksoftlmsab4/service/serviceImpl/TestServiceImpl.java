package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.api.payload.TestRequest;
import kg.peaksoft.peaksoftlmsab4.api.payload.TestResponse;
import kg.peaksoft.peaksoftlmsab4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsab4.model.entity.TestEntity;
import kg.peaksoft.peaksoftlmsab4.model.mapper.TestMapper;
import kg.peaksoft.peaksoftlmsab4.repository.TestRepository;
import kg.peaksoft.peaksoftlmsab4.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {


    private final TestRepository repository;
    private final TestMapper testMapper;

    @Override
    public TestResponse create(TestRequest testRequest) {
        return testMapper.viewTest(repository.save(testMapper.create(testRequest)));
    }

    @Override
    public TestResponse update(Long id, TestRequest testRequest) {
        TestEntity test = repository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("test not found this=%s", id)));
        testMapper.update(test,testRequest);

        return testMapper.viewTest(repository.save(test));
    }

    @Override
    public TestResponse findById(Long id) {
        return testMapper.viewTest(repository.findById(id).orElseThrow(()->new NotFoundException(
                String.format("test not found this=%s",id)
        )));
    }

    @Override
    public TestResponse deleteById(Long id) {
        TestEntity test = repository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("test not found this=%s", id)));
        repository.deleteById(id);
        return testMapper.viewTest(test);
    }

    @Override
    public List<TestResponse> findAll() {
        return testMapper.viewTests(repository.findAll());
    }
}
