package com.chinamcloud.vpc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chinamcloud.vpc.business.VPCBusiness;
import com.chinamcloud.vpc.domain.VPCVO;

@RestController
@RequestMapping("/vpc")
public class VPCController {

	@Autowired
	private VPCBusiness business;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public VPCVO getVPC() {
		return business.getVPC();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public VPCVO listVPC() {
		return null;
	}

	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public VPCVO removeVPC() {
		return null;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public VPCVO saveVPC() {
		return business.saveVPC();
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public VPCVO updateVPC() {
		return null;
	}
}
