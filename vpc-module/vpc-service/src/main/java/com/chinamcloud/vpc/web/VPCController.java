package com.chinamcloud.vpc.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chinamcloud.vpc.business.VPCBusiness;
import com.chinamcloud.vpc.entity.CreateVpcRequest;
import com.chinamcloud.vpc.entity.VpcDO;

@RestController
@RequestMapping("/vpc")
public class VPCController {

	@Autowired
	private VPCBusiness business;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public VpcDO getVPC(@RequestParam(value = "access_token") String access_token,
			@RequestParam(value = "callType", defaultValue = "api") String callType,
			@RequestParam(value = "platformId") String platformId, @RequestParam(value = "regionId") String regionId,
			@RequestParam(value = "vpcId") String cidrBlock) {

		return business.getVPC();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public VpcDO listVPC(@RequestParam(value = "access_token") String access_token,
			@RequestParam(value = "callType", defaultValue = "api") String callType,
			@RequestParam(value = "platformId") String platformId) {
		return null;
	}

	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public VpcDO removeVPC(@RequestParam(value = "access_token") String access_token,
			@RequestParam(value = "callType", defaultValue = "api") String callType,
			@RequestParam(value = "platformId") String platformId, @RequestParam(value = "vpcId") String vpcId) {
		return null;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public VpcDO saveVPC(@Valid @RequestBody CreateVpcRequest vpc) {
		return business.saveVPC(vpc);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public VpcDO updateVPC(@RequestParam(value = "access_token") String access_token,
			@RequestParam(value = "callType", defaultValue = "api") String callType,
			@RequestParam(value = "platformId") String platformId, @RequestParam(value = "vpcId") String vpcId,
			@RequestParam(value = "vpcName", required = false) String vpcName,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "userCidr", required = false) String userCidr) {
		return null;
	}
}
