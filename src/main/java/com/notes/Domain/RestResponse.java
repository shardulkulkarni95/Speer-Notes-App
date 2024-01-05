package com.notes.Domain;

public class RestResponse {
	
	public Boolean status;
	public String massage;
	public Object data;
	public RestResponse(Boolean status, Object data, String massage) {
		super();
		this.status = status;
		this.massage = massage;
		this.data = data;
	}

}
