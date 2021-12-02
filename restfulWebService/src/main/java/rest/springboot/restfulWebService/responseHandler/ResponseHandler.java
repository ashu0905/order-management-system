package rest.springboot.restfulWebService.responseHandler;

import org.springframework.http.HttpStatus;

public class ResponseHandler {
	
	private HttpStatus responseCode;
	private String responseStatus;
	
	public HttpStatus getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(HttpStatus responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	public ResponseHandler(HttpStatus badRequest, String responseStatus) {
		super();
		this.responseCode = badRequest;
		this.responseStatus = responseStatus;
	}
}