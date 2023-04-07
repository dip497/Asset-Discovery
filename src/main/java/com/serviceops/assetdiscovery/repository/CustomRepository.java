package com.serviceops.assetdiscovery.repository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomRepository {

    private final EntityManager em;

    CriteriaBuilder criteriaBuilder;

    @PostConstruct
    void init(){
        this.criteriaBuilder = em.getCriteriaBuilder();
    }


    public CustomRepository(EntityManager em) {
        this.em = em;
    }

    public <T> T findByColumn(final String column, final String email, final Class<T> clazz){
        CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
        Root<T> from = query.from(clazz);
        query.select(from).where(criteriaBuilder.equal(from.get(column), criteriaBuilder.parameter(String.class,column)));
             return em.createQuery(query).setParameter(column, email).getSingleResult();

    }
    public <F,T> List<T> findByColumns(List<String> column, List<F> value,Class<T> clazz) {
        CriteriaQuery<T> cq = criteriaBuilder.createQuery(clazz);
        Root<T> root = cq.from(clazz);
        Predicate[] predicates = new Predicate[column.size()];
        for(int i=0;i<column.size();++i) {
            predicates[i] = criteriaBuilder.equal(root.get(column.get(i)),value.get(i));
        }
        cq.select(root).where(predicates);
        return em.createQuery(cq).getResultList();
    }

    public <T> List<T> findAll(final Class<T> clazz){
        CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
        Root<T> from = query.from(clazz);
        query.select(from);
        return em.createQuery(query).getResultList();
    }
    public <T> List<T> findAllByColumnName(final Class<T> clazz,String foreignColumn,String foreignKey , String columnValue){
        CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
        Root<T> from = query.from(clazz);
        query.select(from).where(criteriaBuilder.equal(from.get(foreignColumn).get(foreignKey),criteriaBuilder.parameter(String.class,foreignKey)));
        return em.createQuery(query).setParameter(foreignKey,columnValue).getResultList();
    }
    @Transactional
    public <T> void  save(T t ){
        if (!em.contains(t)) {
            em.persist(t);
            em.flush();
        }
    }
    @Transactional
    public <T>  void updateColumn(final Class<T> clazz,String columnName,String columnValue){
        CriteriaUpdate<T> query = criteriaBuilder.createCriteriaUpdate(clazz);
        Root<T> from = query.from(clazz);
        query.set(from.get(columnName),columnValue);
        em.createQuery(query).executeUpdate();
    }

    @Transactional
    public <T> void deleteById(final Class<T> clazz,Long id,String columnName){
        CriteriaDelete<T> query = criteriaBuilder.createCriteriaDelete(clazz);
        Root<T> from = query.from(clazz);
        query.where(criteriaBuilder.equal(from.get(columnName),criteriaBuilder.parameter(Long.class,columnName)));
        em.createQuery(query).setParameter(columnName,id).executeUpdate();
    }




}
