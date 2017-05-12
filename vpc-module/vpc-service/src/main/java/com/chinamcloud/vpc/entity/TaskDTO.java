package com.chinamcloud.vpc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO {

	public String id;
	public String resourceId;
	public String action;
	public String requestData;
	public String status;
	public String responseCode;
	public String responseData;

 
}
