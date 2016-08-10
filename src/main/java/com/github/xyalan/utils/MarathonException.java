package com.github.xyalan.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MarathonException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private int status;
	private String reason;
	private String message;
	
	public MarathonException(int status, String reason, String message) {
		this.status = status;
		this.reason = reason;
		this.message = message;
	}

    /**
     * Gets the HTTP status code of the failure, such as 404.
	 * @return http status code
     */
    public int getStatus() {
        return status;
    }

	public String getReason() {
		return reason;
	}

	private String formatError() {
		JsonElement element = new JsonParser().parse(message);
		JsonObject json = element.getAsJsonObject();
		json.addProperty("reason", reason);
		json.addProperty("status", status);
		return json.toString();
	}

	@Override
	public String getMessage() {
		return this.formatError();
	}

	@Override
	public String toString() {
		return this.formatError();
	}
}
