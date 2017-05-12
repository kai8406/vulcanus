package com.chinamcloud.vpc.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinamcloud.vpc.POJO.VpcDO;
import com.chinamcloud.vpc.repository.db.VPCRepository;
import com.chinamcloud.vpc.service.BaseEntityCrudServiceImpl;

@Service
@Transactional
public class VPCService extends BaseEntityCrudServiceImpl<VpcDO, VPCRepository> {

	@Autowired
	private VPCRepository repository;

	@Override
	protected VPCRepository getRepository() {
		return repository;
	}

}
