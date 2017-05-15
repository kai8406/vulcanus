package com.chinamcloud.vpc.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinamcloud.vpc.entity.VpcDO;
import com.chinamcloud.vpc.repository.db.VpcRepository;
import com.chinamcloud.vpc.service.BaseEntityCrudServiceImpl;

@Service
@Transactional
public class VpcService extends BaseEntityCrudServiceImpl<VpcDO, VpcRepository> {

	@Autowired
	private VpcRepository repository;

	@Override
	protected VpcRepository getRepository() {
		return repository;
	}

}
