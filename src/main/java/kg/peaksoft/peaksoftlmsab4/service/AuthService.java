package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.controller.payload.AuthRequest;
import kg.peaksoft.peaksoftlmsab4.controller.payload.AuthResponse;

public interface AuthService {
    AuthResponse authenticate(AuthRequest authRequest);
}
