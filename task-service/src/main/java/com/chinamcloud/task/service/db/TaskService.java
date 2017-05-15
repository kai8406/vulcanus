package com.chinamcloud.task.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinamcloud.task.entity.TaskDTO;
import com.chinamcloud.task.repository.db.TaskRepository;
import com.chinamcloud.task.service.BaseEntityCrudServiceImpl;

@Service
@Transactional
public class TaskService extends BaseEntityCrudServiceImpl<TaskDTO, TaskRepository> {

	@Autowired
	private TaskRepository repository;

	@Override
	protected TaskRepository getRepository() {
		return repository;
	}

}
