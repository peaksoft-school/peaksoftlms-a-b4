package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.controller.payload.request.AuthRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse authenticate(AuthRequest request);

}
