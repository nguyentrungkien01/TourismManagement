package com.ou.common.repositories.impl;


import com.ou.common.repositories.CMScheduleRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.ScheduleEntity;
import com.ou.utils.PageUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class CMScheduleRepositoryImpl implements CMScheduleRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public List<Object[]> getSchedules(Integer pageIndex) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<ScheduleEntity> scheduleEntityRoot = criteriaQuery.from(ScheduleEntity.class);
        criteriaQuery.multiselect(scheduleEntityRoot.get("scheId"), scheduleEntityRoot.get("scheTitle"),
                scheduleEntityRoot.get("scheSlug"), scheduleEntityRoot.get("scheContent"));
        if (pageIndex != null) {
            PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
            pageUtil.setSearchIndex(pageIndex);
            return session.createQuery(criteriaQuery).setFirstResult(pageUtil.getSearchIndex())
                    .setMaxResults(PageUtil.getPageSize()).getResultList();
        }
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public ScheduleEntity getSchedule(String scheSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<ScheduleEntity> criteriaQuery = criteriaBuilder.createQuery(ScheduleEntity.class);
        Root<ScheduleEntity> scheduleEntityRoot = criteriaQuery.from(ScheduleEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(scheduleEntityRoot.get("scheSlug").as(String.class), scheSlug));
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public ScheduleEntity getSchedule(Integer scheId) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<ScheduleEntity> criteriaQuery = criteriaBuilder.createQuery(ScheduleEntity.class);
        Root<ScheduleEntity> scheduleEntityRoot = criteriaQuery.from(ScheduleEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(scheduleEntityRoot.get("scheId").as(Integer.class), scheId));
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public boolean createSchedule(ScheduleEntity scheduleEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(scheduleEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updateSchedule(ScheduleEntity scheduleEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(scheduleEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteSchedule(ScheduleEntity scheduleEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(scheduleEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}