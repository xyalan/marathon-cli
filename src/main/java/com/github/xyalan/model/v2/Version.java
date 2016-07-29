package com.github.xyalan.model.v2;

import com.github.xyalan.utils.ModelUtils;

import java.io.Serializable;

/**
 * User: Alan
 * Email: houlong.yang@shuyun.com
 * Date: 02:01 7/30/16
 */
public class Version implements Serializable {
	private String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return ModelUtils.toString(this);
	}
}
