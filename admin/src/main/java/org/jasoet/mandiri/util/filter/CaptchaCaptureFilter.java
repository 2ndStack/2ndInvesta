package org.jasoet.mandiri.util.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filter for capturing Captcha fields.
 * It's purpose is to store these values internally
 */
public class CaptchaCaptureFilter extends OncePerRequestFilter {
 
	protected Logger logger = Logger.getLogger("filter");
	private String reCaptchaResponse;
	private String reCaptchaChallenge;
	private String remoteAddr;

	@Override
	public void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
			FilterChain chain) throws IOException, ServletException {

//		logger.info("Captcha capture filter");
		
		// Assign values only when user has submitted a Captcha value.
		// Without this condition the values will be reset due to redirection
		// and CaptchaVerifierFilter will enter an infinite loop
		if (req.getParameter("recaptcha_challenge_field") != null) {
			reCaptchaResponse = req.getParameter("recaptcha_response_field");
			reCaptchaChallenge = req.getParameter("recaptcha_challenge_field");
			remoteAddr = req.getRemoteAddr();
		}
		
		/*logger.info("challenge: " + reCaptchaChallenge);
		logger.info("response: " + reCaptchaResponse);
		logger.info("remoteAddr: " + remoteAddr);*/
		
		// Proceed with the remaining filters
		chain.doFilter(req, res);
	}

	public String getReCaptchaResponse() {
		return reCaptchaResponse;
	}

	public void setReCaptchaResponse(String recaptchaResponse) {
		reCaptchaResponse = recaptchaResponse;
	}

	public String getReCaptchaChallenge() {
		return reCaptchaChallenge;
	}

	public void setReCaptchaChallenge(String recaptchaChallenge) {
		reCaptchaChallenge = recaptchaChallenge;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	
	
 
}
 
