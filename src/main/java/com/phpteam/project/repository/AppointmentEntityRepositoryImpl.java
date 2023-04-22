//package com.phpteam.project.repository;
//
//import com.phpteam.project.entity.AppointmentEntity;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@Transactional
//public class AppointmentEntityRepositoryImpl implements CustomAppointmentEntityRepository {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public <S extends AppointmentEntity> S save(S entity) {
//        entityManager.persist(entity);
//        return entity;
//    }
//    }
//
