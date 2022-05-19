package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.repository.TaskTypeRepository;
import kg.peaksoft.peaksoftlmsab4.service.TaskTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskTypeServiceImpl implements TaskTypeService {
    private final TaskTypeRepository taskTypeRepository;
    @Override
    public String delete(Long id) {
        taskTypeRepository.deleteById(id);
        return "Task deleted";
    }
}
