package com.chinamcloud.vpc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.chinamcloud.vpc.business.VPCBusiness;
import com.chinamcloud.vpc.domain.VPCVO;

@RestController
@RequestMapping("/vpc")
public class VPCController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private VPCBusiness business;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public VPCVO getVPC(@RequestParam(value = "access_token") String access_token,
			@RequestParam(value = "callType", defaultValue = "api") String callType,
			@RequestParam(value = "platformId") String platformId, @RequestParam(value = "regionId") String regionId,
			@RequestParam(value = "vpcId") String cidrBlock) {

		// String userId = restTemplate
		// .getForEntity("http://AUTH-SERVER/users/id?access_token=" + access_token, String.class).getBody();
		// System.err.println(userId);

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
	public VPCVO saveVPC(@Valid @RequestBody VPC vpc) {

		System.out.println(vpc);

		return new VPCVO();
		// return business.saveVPC();
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
