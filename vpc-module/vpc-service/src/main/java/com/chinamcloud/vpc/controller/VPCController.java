package com.chinamcloud.vpc.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	public VPCVO getVPC(@RequestParam(value = "access_token", required = false) String access_token) {

		String userId = restTemplate
				.getForEntity("http://AUTH-SERVER/users/id?access_token=" + access_token, String.class).getBody();

		System.err.println(userId);

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
