package com.chinamcloud.vpc.repository;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.chinamcloud.vpc.VpcServiceApplication;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VpcServiceApplication.class)
@Slf4j
public class JpaMappingTest {

	@PersistenceContext
	private EntityManager em;

	@Test
	public void allClassMapping() throws Exception {

		Metamodel model = em.getEntityManagerFactory().getMetamodel();

		assertTrue("No entity mapping found", model.getEntities().size() > 0);

		int i = 0;

		for (EntityType<?> entityType : model.getEntities()) {
			String entityName = entityType.getName();
			em.createQuery("select o from " + entityName + " o").getResultList();
			log.info("ok: " + entityName);
			i++;
		}

		log.info("total: " + i);

	}

}
