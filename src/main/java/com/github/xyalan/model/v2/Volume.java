package com.github.xyalan.model.v2;

import com.github.xyalan.utils.ModelUtils;

public class Volume {
	private String containerPath;
	private String hostPath;
	private String mode;
	private Persistent persistent;

	public String getContainerPath() {
		return containerPath;
	}

	public void setContainerPath(String containerPath) {
		this.containerPath = containerPath;
	}

	public String getHostPath() {
		return hostPath;
	}

	public void setHostPath(String hostPath) {
		this.hostPath = hostPath;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Persistent getPersistent() {
		return persistent;
	}

	public void setPersistent(Persistent persistent) {
		this.persistent = persistent;
	}

	public class Persistent {
		private Integer size;

		public Integer getSize() {
			return size;
		}

		public void setSize(Integer size) {
			this.size = size;
		}
	}

	@Override
	public String toString() {
		return ModelUtils.toString(this);
	}
}
