package com.example.edux_backend.service;

import com.example.edux_backend.dto.request.AuthenticationRequest;
import com.example.edux_backend.dto.request.IntrospectRequest;
import com.example.edux_backend.dto.response.AuthenticationResponse;
import com.example.edux_backend.dto.response.IntrospectResponse;
import com.example.edux_backend.exception.AppException;
import com.example.edux_backend.exception.ErrorCode;
import com.example.edux_backend.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGN_KEY;

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        JWSVerifier verifier = new MACVerifier(SIGN_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);
        return IntrospectResponse.builder()
                .valid(verified && expiryTime.after(new Date()))
                .build();

    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) throws JOSEException {
        var user = userRepository.findByUserName(request.getUserName())
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXITED));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated =  passwordEncoder.matches(
                request.getUserPassword(), user.getUserPassword()
        );
        if(!authenticated) throw new AppException(ErrorCode.USER_INVALID);
        var token = generateToken(request.getUserName());
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    private String generateToken(String userName) throws JOSEException {
        JWSHeader header = new JWSHeader(JWSAlgorithm.ES512);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(userName)
                .issuer("eduX.com")
                .issueTime(new Date())
                .expirationTime(Date.from(
                        Instant.now().plus(Duration.ofHours(1))
                ))
                .claim("customClaim","Custom")
                .build();
        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
                jwsObject.sign(new MACSigner(SIGN_KEY.getBytes()));
                return jwsObject.serialize();
            } catch (JOSEException e){
                throw new RuntimeException(e);
            }
        }
}

