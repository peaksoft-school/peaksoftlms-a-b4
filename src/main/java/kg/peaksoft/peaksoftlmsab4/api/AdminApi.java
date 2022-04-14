package kg.peaksoft.peaksoftlmsab4.api;

import kg.peaksoft.peaksoftlmsab4.dto.request.AdminRequest;
import kg.peaksoft.peaksoftlmsab4.dto.response.AdminResponse;
import kg.peaksoft.peaksoftlmsab4.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin")
@AllArgsConstructor
public class AdminApi {

    private final AdminService adminService;

    @PostMapping("/save")
    public AdminResponse saveAdmin(@RequestBody AdminRequest adminRequest) {
        return adminService.saveAdmin(adminRequest);
    }


}
