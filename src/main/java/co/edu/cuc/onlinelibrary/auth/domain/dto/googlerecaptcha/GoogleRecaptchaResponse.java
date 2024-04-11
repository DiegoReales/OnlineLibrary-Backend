package co.edu.cuc.onlinelibrary.auth.domain.dto.googlerecaptcha;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GoogleRecaptchaResponse {
	
	@JsonProperty("success")
    private Boolean success;
    
    @JsonProperty("challenge_ts")
    private String challengeTs;
    
    @JsonProperty("hostname")
    private String hostname;
    
    @JsonProperty("error-codes")
    private List<String> errorCodes;
    
	public Boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String getChallengeTs() {
		return challengeTs;
	}
	
	public void setChallengeTs(String challengeTs) {
		this.challengeTs = challengeTs;
	}
	public String getHostname() {
		return hostname;
	}
	
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
	public List<String> getErrorCodes() {
		return errorCodes;
	}
	
	public void setErrorCodes(List<String> errorCodes) {
		this.errorCodes = errorCodes;
	}
}
