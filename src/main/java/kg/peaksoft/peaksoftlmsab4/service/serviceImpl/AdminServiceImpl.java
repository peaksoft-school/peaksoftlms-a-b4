package kg.peaksoft.peaksoftlmsab4.service.serviceImpl;

import kg.peaksoft.peaksoftlmsab4.dto.mapper.edit.AdminEditMapper;
import kg.peaksoft.peaksoftlmsab4.dto.mapper.view.AdminViewMapper;
import kg.peaksoft.peaksoftlmsab4.dto.request.AdminRequest;
import kg.peaksoft.peaksoftlmsab4.dto.response.AdminResponse;
import kg.peaksoft.peaksoftlmsab4.repository.AdminRepository;
import kg.peaksoft.peaksoftlmsab4.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminViewMapper adminViewMapper;
    private final AdminEditMapper adminEditMapper;
    private final AdminRepository adminRepository;

    @Override
    public AdminResponse saveAdmin(AdminRequest adminRequest) {
        return adminViewMapper.
                convertToAdminResponse(adminRepository.
                        save(adminEditMapper.convertToAdmin(adminRequest)));
    }
}
