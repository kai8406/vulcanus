package com.chinamcloud.vpc.web;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chinamcloud.vpc.business.VPCBusiness;
import com.chinamcloud.vpc.entity.CreateVpcRequest;
import com.chinamcloud.vpc.entity.UpdateVpcRequest;
import com.chinamcloud.vpc.entity.VpcDO;
import com.chinamcloud.vpc.util.Servlets;
import com.chinamcloud.vpc.util.exception.RestResult;

@RestController
@RequestMapping("/vpc")
public class VpcController {

	public static final String Request_Prefix = "search_";

	@Autowired
	private VPCBusiness business;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public RestResult<VpcDO> getVpc(@PathVariable("id") String id,
			@RequestParam(value = "access_token") String accessToken) {
		return business.getVpc(accessToken,id);
	}

	/**
	 * 分页查询
	 * 
	 * size:每页大小,默认为10,URL后面跟上size=20可设定 page:当前页数,从0开始,URL后面跟上page=0可设定页数
	 * 通过在URL后面跟上sort=id,asc可设定某个字段的排序,可同时设置多个排序条件,默认以创建时间倒序显示.
	 * 
	 * 
	 * @param pageable
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public RestResult<Page<VpcDO>> listVpc(@RequestParam(value = "access_token") String accessToken,
			@PageableDefault(value = 10, direction = Direction.DESC, sort = { "createTime" }) Pageable pageable,
			ServletRequest request) {

		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, Request_Prefix);

		return business.findAll(accessToken, searchParams, pageable);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public RestResult<?> removeVpc(@PathVariable("id") String id,
			@RequestParam(value = "access_token") String accessToken) {
		return business.removeVpc(accessToken, id);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public RestResult<VpcDO> saveVpc(@RequestParam(value = "access_token") String accessToken,
			@Valid @RequestBody CreateVpcRequest vpc) {
		return business.saveVpc(accessToken, vpc);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public RestResult<VpcDO> updateVpc(@PathVariable("id") String id,
			@RequestParam(value = "access_token") String accessToken, @Valid @RequestBody UpdateVpcRequest vpc) {
		return business.updateVpc(accessToken, id, vpc);
	}
}
