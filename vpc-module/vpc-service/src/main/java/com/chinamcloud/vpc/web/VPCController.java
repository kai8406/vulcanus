package com.chinamcloud.vpc.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chinamcloud.vpc.business.VPCBusiness;
import com.chinamcloud.vpc.domain.CreateVpcRequest;
import com.chinamcloud.vpc.domain.VPCVO;

@RestController
@RequestMapping("/vpc")
public class VPCController {

	@Autowired
	private VPCBusiness business;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public VPCVO getVPC(@RequestParam(value = "access_token") String access_token,
			@RequestParam(value = "callType", defaultValue = "api") String callType,
			@RequestParam(value = "platformId") String platformId, @RequestParam(value = "regionId") String regionId,
			@RequestParam(value = "vpcId") String cidrBlock) {

		return business.getVPC();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public VPCVO listVPC(@RequestParam(value = "access_token") String access_token,
			@RequestParam(value = "callType", defaultValue = "api") String callType,
			@RequestParam(value = "platformId") String platformId) {
		return null;
	}

	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public VPCVO removeVPC(@RequestParam(value = "access_token") String access_token,
			@RequestParam(value = "callType", defaultValue = "api") String callType,
			@RequestParam(value = "platformId") String platformId, @RequestParam(value = "vpcId") String vpcId) {
		return null;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public VPCVO saveVPC(@Valid @RequestBody CreateVpcRequest vpc) {
		return new VPCVO();
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public VPCVO updateVPC(@RequestParam(value = "access_token") String access_token,
			@RequestParam(value = "callType", defaultValue = "api") String callType,
			@RequestParam(value = "platformId") String platformId, @RequestParam(value = "vpcId") String vpcId,
			@RequestParam(value = "vpcName", required = false) String vpcName,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "userCidr", required = false) String userCidr) {
		return null;
	}
}
