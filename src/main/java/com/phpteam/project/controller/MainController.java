package com.phpteam.project.controller;

import com.phpteam.project.model.Appointment;
import com.phpteam.project.model.Doctor;
import com.phpteam.project.model.Patient;
import com.phpteam.project.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clinic")
public class MainController {

    private final DoctorService doctorService;
    private final PatientService patientService;
    private final AppointmentService appointmentService;

    @Autowired
    public MainController(DoctorService doctorService, PatientService patientService, AppointmentService appointmentService){
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("/list-doctors")
    public String listDoctors(Model theModel){
        List<Doctor> theDoctors = doctorService.getAllDoctors();
        theModel.addAttribute("doctors", theDoctors);
        return "doctor/list-doctors";
    }

    @GetMapping("/doctor")
    public String getDoctorById(@RequestParam("docId") Long theId, Model theModel){
        Doctor existingDoc = doctorService.getDoctorById(theId);
        theModel.addAttribute("doctor", existingDoc);
        return "doctor/doctor-detail";
    }

    @GetMapping("/list-patients")
    public String listPatients(Model theModel){
        List<Patient> thePatients = patientService.getAllPatients();
        theModel.addAttribute("patients", thePatients);
        return "patient/list-patients";
    }

    @GetMapping("/patient")
    public String getPatientById(@RequestParam("patId") Long theId, Model theModel){
        Patient existingPat = patientService.getPatientById(theId);
        theModel.addAttribute("patient", existingPat);
        return "patient/patient-detail";
    }

    @GetMapping("/doctor-appointments")
    public String getDoctorAppointments(@RequestParam("docId") Long theId, Model theModel){
        List<Appointment> theAppointments = appointmentService.getAppointmentsByDocId(theId);
        theModel.addAttribute("appointments", theAppointments);
        return "appointment/list-appointments";
    }

    @GetMapping("/patient-appointments")
    public String getPatientAppointments(@RequestParam("patId") Long theId, Model theModel){
        List<Appointment> theAppointments = appointmentService.getAppointmentsByPatId(theId);
        theModel.addAttribute("appointments", theAppointments);
        return "appointment/list-appointments";
    }

    @GetMapping("/appointment-by-doctor-phone")
    public String getAppointmentByDoctorPhone(@RequestParam("docPhone") String thePhone, Model theModel){
        List<Appointment> theAppointments = appointmentService.getAppointmentsByDocPhone(thePhone);
        theModel.addAttribute("appointments", theAppointments);
        return "appointment/list-appointments";
    }

    @GetMapping("/appointment-by-patient-phone")
    public String getAppointmentByPatientPhone(@RequestParam("patPhone") String thePhone, Model theModel){
        List<Appointment> theAppointments = appointmentService.getAppointmentsByPatPhone(thePhone);
        theModel.addAttribute("appointments", theAppointments);
        return "appointment/list-appointments";
    }

    @GetMapping("/appointment-by-doctor-email")
    public String getAppointmentByDoctorEmail(@RequestParam("docEmail") String theEmail, Model theModel){
        List<Appointment> theAppointments = appointmentService.getAppointmentsByDocEmail(theEmail);
        theModel.addAttribute("appointments", theAppointments);
        return "appointment/list-appointments";
    }

    @GetMapping("/appointment-by-patient-email")
    public String getAppointmentByPatientEmail(@RequestParam("patEmail") String theEmail, Model theModel){
        List<Appointment> theAppointments = appointmentService.getAppointmentsByPatEmail(theEmail);
        theModel.addAttribute("appointments", theAppointments);
        return "appointment/list-appointments";
    }

    @GetMapping("/appointment/create")
    public String showCreateAppointmentForm(Model theModel) {
        List<Patient> patients = patientService.getAllPatients();
        List<Doctor> doctors = doctorService.getAllDoctors();
        theModel.addAttribute("appointment", new Appointment());
        theModel.addAttribute("patients", patients);
        theModel.addAttribute("doctors", doctors);
        return "appointment/create-appointment";
    }

    @PostMapping("/appointment/save")
    public String saveAppointment(@ModelAttribute("appointment") Appointment theAppointment) {
        appointmentService.saveAppointment(theAppointment);
        return "redirect:/clinic/list-appointments";
    }

    @GetMapping("/appointment/edit/{id}")
    public String showEditAppointmentForm(Model theModel, @PathVariable("id") long theId) {
        Appointment appointment = appointmentService.getAppointmentById(theId);
        List<Patient> patients = patientService.getAllPatients();
        List<Doctor> doctors = doctorService.getAllDoctors();
        theModel.addAttribute("editAppointment", appointment);
        theModel.addAttribute("patients", patients);
        theModel.addAttribute("doctors", doctors);
        return "appointments/edit-appointment";
    }

    @PostMapping("/appointment/update/{id}")
    public String updateAppointment(@PathVariable("id") long theId, @ModelAttribute("editAppointment") Appointment theAppointment) {
        theAppointment.setId(theId);
        appointmentService.updateAppointment(theAppointment);
        return "redirect:/clinic/list-appointments";
    }

    @GetMapping("/appointment/delete/{id}")
    public String deleteAppointment(@PathVariable("id") long theId) {
        appointmentService.deleteAppointment(theId);
        return "redirect:/clinic/list-appointments";
    }
}
