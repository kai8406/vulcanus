package com.chinamcloud.vpc.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chinamcloud.vpc.business.VPCBusiness;
import com.chinamcloud.vpc.entity.CreateVpcRequest;
import com.chinamcloud.vpc.entity.DeleteVpcRequest;
import com.chinamcloud.vpc.entity.UpdateVpcRequest;
import com.chinamcloud.vpc.entity.VpcDO;
import com.chinamcloud.vpc.util.exception.RestResult;

@RestController
@RequestMapping("/vpc")
public class VpcController {

	@Autowired
	private VPCBusiness business;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public RestResult<VpcDO> getVpc(@PathVariable("id") String id) {
		return business.getVpc(id);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public VpcDO listVpc(@RequestParam(value = "access_token") String access_token,
			@RequestParam(value = "callType", defaultValue = "api") String callType,
			@RequestParam(value = "platformId") String platformId) {
		return null;
	}

	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public RestResult<?> removeVpc(@Valid @RequestBody DeleteVpcRequest vpc) {
		return business.removeVpc(vpc);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public RestResult<VpcDO> saveVpc(@Valid @RequestBody CreateVpcRequest vpc) {
		return business.saveVpc(vpc);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public RestResult<VpcDO> updateVpc(@Valid @RequestBody UpdateVpcRequest vpc) {
		return business.updateVpc(vpc);
	}
}
