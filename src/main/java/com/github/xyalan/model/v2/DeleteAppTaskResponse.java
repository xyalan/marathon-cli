package com.github.xyalan.model.v2;

import com.github.xyalan.utils.ModelUtils;

public class DeleteAppTaskResponse {
	private Task task;

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	@Override
	public String toString() {
		return ModelUtils.toString(this);
	}

}
