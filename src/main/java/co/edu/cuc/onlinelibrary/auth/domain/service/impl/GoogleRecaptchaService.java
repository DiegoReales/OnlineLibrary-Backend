package co.edu.cuc.onlinelibrary.auth.domain.service.impl;

import co.edu.cuc.onlinelibrary.auth.domain.dto.googlerecaptcha.GoogleRecaptchaResponse;
import co.edu.cuc.onlinelibrary.auth.domain.service.IGoogleRecaptchaService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GoogleRecaptchaService implements IGoogleRecaptchaService {
	
	protected final Log logger = LogFactory.getLog(getClass());

	@Value("${auth.google.recaptcha.site}")
	private String urlSite;

	@Value("${auth.google.recaptcha.secret}")
	private String secret;

	@Override
	public boolean validateToken(String token, String ipRemote) {
		if (Objects.isNull(token) || token.isBlank()) return false;

		try {
			RestTemplate rest = new RestTemplate();
			URI verifyUri = URI.create(String.format(urlSite, secret, token, ipRemote));
			GoogleRecaptchaResponse response = rest.getForObject(verifyUri, GoogleRecaptchaResponse.class);
			return response != null && (response.isSuccess() != null ? response.isSuccess() : Boolean.FALSE);
		} catch (Exception e) {
			logger.error(String.format("Failed validation google recaptcha => %s", e.getMessage()));
			return false;
		}
	}
	
}
