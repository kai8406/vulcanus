package com.chinamcloud.vpc.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

	public String id;
	public String resourceId;
	public String action;
	public String requestData;
	public String status;
	public String responseCode;
	public String responseData;

}
