package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.dto.request.AdminRequest;
import kg.peaksoft.peaksoftlmsab4.dto.response.AdminResponse;

public interface AdminService {

    AdminResponse saveAdmin(AdminRequest adminRequest);
}
