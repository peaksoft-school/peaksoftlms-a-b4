package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.dto.auth.AuthRequest;
import kg.peaksoft.peaksoftlmsab4.dto.auth.AuthResponse;

public interface AuthService {
    AuthResponse authenticate(AuthRequest authRequest);
}
