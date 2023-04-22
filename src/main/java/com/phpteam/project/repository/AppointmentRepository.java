package com.phpteam.project.repository;

import com.phpteam.project.entity.AppointmentEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity,Long> {

//    @Query("SELECT e FROM AppointmentEntity e WHERE e.email = ?1")
//    Optional<AppointmentEntity> findByEmail(String EmailId);
//TODO
//    @Query("SELECT e FROM AppointmentEntity e WHERE e.doctorId=?1")
//    Optional<AppointmentEntity> findAllByDo(Integer docId);
    @Query("SELECT e FROM AppointmentEntity e WHERE e.doctorId=?1")
    List<AppointmentEntity> findAllDoctor(Long doctorId);

//    @Query("SELECT e FROM AppointmentEntity e WHERE e.patient_id = ?1")
    Optional<AppointmentEntity> findByPatientId(Integer id);
}
