package com.chinamcloud.vpc.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.chinamcloud.vpc.entity.VpcDO;
import com.chinamcloud.vpc.service.db.VpcService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VpcTest {

	@Autowired
	public VpcService service;

	@Test
	public void find() {

		Map<String, Object> map = new HashMap<>();
		map.put("EQ_id", "8a80cb815c10c05e015c10c0b7390000");
		VpcDO entity = service.find(map);

		System.err.println(entity);
	}

	@Test
	public void list() {

		Map<String, Object> map = new HashMap<>();
		List<VpcDO> list = service.findAll(map);

		list.stream().forEach(s -> System.out.println(s));
	}

	@Test
	public void pageable() {

		Map<String, Object> map = new HashMap<>();
		map.put("EQ_id", "8a80cb815c10c05e015c10c0b7390000");

		Pageable pageable = new PageRequest(0, 6, new Sort(Direction.DESC, "id"));

		Page<VpcDO> page = service.findAll(map, pageable);

		page.getContent().stream().forEach(s -> System.err.println("Content:" + s));

		System.err.println("hasContent:" + page.hasContent()); // 是否包含内容
		System.err.println("Number:" + page.getNumber()); // 当前页为第x页
		System.err.println("NumberOfElements:" + page.getNumberOfElements()); // 当前页显示的数据数量
		System.err.println("Size:" + page.getSize());// 每页大小为15
		System.err.println("TotalElements:" + page.getTotalElements()); // 总数据量
		System.err.println("TotalPages:" + page.getTotalPages()); // 总页数
		System.err.println("hasPrevious:" + page.hasPrevious());// 是否有上页
		System.err.println("hasNext:" + page.hasNext());// 是否有下页
		System.err.println("isFirst:" + page.isFirst());// 是否第一页
		System.err.println("isLast:" + page.isLast());// 是否最后一页

	}

}
