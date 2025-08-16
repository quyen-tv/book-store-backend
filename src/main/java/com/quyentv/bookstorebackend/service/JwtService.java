package com.quyentv.bookstorebackend.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import com.quyentv.bookstorebackend.entity.User;

import java.text.ParseException;

public interface JwtService {

    String generateAccessToken(User user);
    String generateRefreshToken(User user);
    SignedJWT verifyAccessToken(String token) throws JOSEException, ParseException;
}
