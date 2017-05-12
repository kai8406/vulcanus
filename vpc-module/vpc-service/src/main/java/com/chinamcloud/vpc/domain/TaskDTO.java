package com.chinamcloud.vpc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

	public String id;
	public String resourceCode;
	public String action;
	public String requestData;
	public String state;
	public String responseCode;
	public String responseData;

 
}
