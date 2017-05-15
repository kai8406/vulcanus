package com.chinamcloud.task.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task_service")
public class TaskDTO {

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	public String id;

	@Column(name = "resource_id")
	public String resourceId;

	@Column(name = "action")
	public String action;

	@Column(name = "request_data")
	public String requestData;

	@Column(name = "status")
	public String status;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "response_code")
	public String responseCode;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "response_data")
	public String responseData;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "create_time")
	private Date createTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "modify_time")
	private Date modifyTime;

}
