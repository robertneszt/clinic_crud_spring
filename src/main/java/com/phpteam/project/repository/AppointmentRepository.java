package com.phpteam.project.repository;

import com.phpteam.project.entity.AppointmentEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity,Long> {

}
