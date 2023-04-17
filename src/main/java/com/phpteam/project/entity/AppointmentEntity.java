package com.phpteam.project.entity;

import com.phpteam.project.model.Doctor;
import com.phpteam.project.model.Patient;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "appointment_datetime")
    private LocalDateTime datetime;

    @Column(name = "appointment_status")
    private Enum status;

}
