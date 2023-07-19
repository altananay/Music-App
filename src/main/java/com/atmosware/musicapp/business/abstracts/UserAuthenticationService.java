package com.atmosware.musicapp.business.abstracts;

import com.atmosware.musicapp.core.utils.dto.AuthenticationRequest;
import com.atmosware.musicapp.core.utils.dto.AuthenticationResponse;
import com.atmosware.musicapp.core.utils.dto.RegisterRequest;
import com.atmosware.musicapp.core.utils.dto.RegisterResponse;

public interface UserAuthenticationService {
    RegisterResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}