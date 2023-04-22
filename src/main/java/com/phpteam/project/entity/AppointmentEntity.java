package com.phpteam.project.entity;

import com.phpteam.project.model.Doctor;
import com.phpteam.project.model.Patient;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "medical_record")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "doc_id")
////    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "doc_id")
    @Column(name = "doc_id")
//    private DoctorEntity doctorEntity;
     private Long doctorId;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "patient_id")
////    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "patient_id")
    @Column (name = "patient_id")
//    private PatientEntity patientEntity;
    private Long patientId;
    @Column(name = "appointment_datetime")
    private LocalDateTime datetime;

    @Column(name = "appointment_status")
    private String status;

}
