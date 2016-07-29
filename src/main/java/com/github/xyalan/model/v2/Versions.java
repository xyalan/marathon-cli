package com.github.xyalan.model.v2;

import com.github.xyalan.utils.ModelUtils;

import java.io.Serializable;
import java.util.List;

/**
 * User: Alan
 * Email: houlong.yang@shuyun.com
 * Date: 01:59 7/30/16
 */
public class Versions implements Serializable {
	private List<String> versions;

	public List<String> getVersions() {
		return versions;
	}

	public void setVersions(List<String> versions) {
		this.versions = versions;
	}

	@Override
	public String toString() {
		return ModelUtils.toString(this);
	}
}
