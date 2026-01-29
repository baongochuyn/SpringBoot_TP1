package com.springboot.tp.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Map;

@RestController
public class JwkController {

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    @PostConstruct
    public void init() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // clé 2048 bits
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        publicKey = (RSAPublicKey) keyPair.getPublic();
        privateKey = (RSAPrivateKey) keyPair.getPrivate();
    }

    /** Endpoint JWKS pour les autres microservices */
    @GetMapping("/.well-known/jwks.json")
    public Map<String, Object> getJwks() {
        return Map.of(
                "keys", new Object[] {
                        Map.of(
                                "kty", "RSA",
                                "kid", "auth-service-key-2026",
                                "use", "sig",
                                "alg", "RS256",
                                "n", Base64.getUrlEncoder().withoutPadding()
                                        .encodeToString(publicKey.getModulus().toByteArray()),
                                "e", Base64.getUrlEncoder().withoutPadding()
                                        .encodeToString(publicKey.getPublicExponent().toByteArray()))
                });
    }

    /** Méthode interne pour signer les JWT */
    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    public RSAPublicKey getPublicKey() {
        return publicKey;
    }
}
