package com.chinamcloud.vpc.service;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.chinamcloud.vpc.repository.JpaSpecificationRepository;

public abstract class BaseEntityCrudServiceImpl<T, R extends JpaSpecificationRepository<T>>
		implements BaseEntityCrudService<T> {

	private static <T> Specification<T> bySearchFilter(final Collection<SearchFilter> filters,
			final Class<T> entityClazz) {
		return new Specification<T>() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				if ((filters != null) && !(filters.isEmpty())) {

					List<Predicate> predicates = new ArrayList<>();
					for (SearchFilter filter : filters) {
						// nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
						String[] names = StringUtils.split(filter.fieldName, ".");
						Path expression = root.get(names[0]);
						for (int i = 1; i < names.length; i++) {
							expression = expression.get(names[i]);
						}

						// logic operator
						switch (filter.operator) {
						case EQ:
							predicates.add(builder.equal(expression, filter.value));
							break;
						case LIKE:
							predicates.add(builder.like(expression, "%" + filter.value + "%"));
							break;
						case GT:
							predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
							break;
						case LT:
							predicates.add(builder.lessThan(expression, (Comparable) filter.value));
							break;
						case GTE:
							predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
							break;
						case LTE:
							predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
							break;
						case NOT:
							predicates.add(builder.notEqual(expression, (Comparable) filter.value));
							break;
						case IsNull:
							predicates.add(builder.isNull(expression));
							break;
						case NotNull:
							predicates.add(builder.isNotNull(expression));
							break;
						}
					}

					// 将所有条件用 and 联合起来
					if (predicates.size() > 0) {
						return builder.and(predicates.toArray(new Predicate[predicates.size()]));
					}
				}

				return builder.conjunction();
			}
		};
	}

	@SuppressWarnings("unchecked")
	private Specification<T> buildSpecification(Map<String, Object> searchParams) {

		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);

		/**
		 * 通过反射的API来获取T的Class
		 * 
		 * http://www.blogjava.net/calvin/archive/2009/12/10/43830.html
		 */
		return bySearchFilter(filters.values(),
				(Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	@Override
	public long count() {
		return getRepository().count();
	}

	@Override
	public long count(Map<String, Object> searchParams) {
		return getRepository().count(buildSpecification(searchParams));
	}

	@Override
	public void delete(String id) {
		getRepository().delete(id);
	}

	@Override
	public void delete(T entity) {
		getRepository().delete(entity);
	}

	@Override
	public void deleteInBatch(Iterable<T> entities) {
		getRepository().deleteInBatch(entities);
	}

	@Override
	public boolean exists(String id) {
		return getRepository().exists(id);
	}

	@Override
	public T find(String id) {
		return getRepository().findOne(id);
	}

	@Override
	public T find(Map<String, Object> searchParams) {
		return getRepository().findOne(buildSpecification(searchParams));
	}

	@Override
	public List<T> findAll() {
		return getRepository().findAll();
	}

	@Override
	public List<T> findAll(Map<String, Object> searchParams) {
		return getRepository().findAll(buildSpecification(searchParams));
	}

	protected abstract R getRepository();

	@Override
	public List<T> save(Iterable<T> entities) {
		return getRepository().save(entities);
	}

	@Override
	public T saveAndFlush(T entity) {
		return getRepository().saveAndFlush(entity);
	}

}
