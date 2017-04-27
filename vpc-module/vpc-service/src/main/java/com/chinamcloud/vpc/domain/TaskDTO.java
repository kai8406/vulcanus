package com.chinamcloud.vpc.domain;

public class TaskDTO {

	public String code;
	public String resourceCode;
	public Long id;

	
	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public String getCode() {
		return code;
	}

	public Long getId() {
		return id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
