package com.senla.training.kononovich.server.service;

import java.text.ParseException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.senla.training.kononovich.server.api.service.ITokenUtility;

@Component
public class TokenUtility implements ITokenUtility {
	private static Logger log = Logger.getLogger(TokenUtility.class);

	public String createToken(Long id) {
		byte[] sharedSecret = new byte[32];
		try {
			JWSSigner signer = new MACSigner(sharedSecret);
			JWTClaimsSet claimsSet = new JWTClaimsSet.Builder().issuer("SuperWebApp").claim("id", id).build();
			SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
			signedJWT.sign(signer);
			String token = signedJWT.serialize();
			return token;
		} catch (Exception e) {
			log.error(e.toString());
			return null;
		}
	}

	public Long getUserIdByToken(String token) {
		try {
			SignedJWT signedJWT = SignedJWT.parse(token);
			Long id = (Long) signedJWT.getJWTClaimsSet().getClaim("id");
			return id;
		} catch (ParseException e) {
			log.error(e.toString());
			return null;
		} catch (Exception e) {
			log.error(e.toString());
			return null;
		}
	}
}
