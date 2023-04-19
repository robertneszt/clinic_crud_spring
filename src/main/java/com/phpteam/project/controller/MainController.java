package com.phpteam.project.controller;

import com.phpteam.project.exception.EntityNotFoundException;
import com.phpteam.project.model.Appointment;
import com.phpteam.project.model.Doctor;
import com.phpteam.project.model.Patient;
import com.phpteam.project.service.AppointmentService;
import com.phpteam.project.service.DoctorService;
import com.phpteam.project.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    @GetMapping("/")
    public String index() {
        return "index";
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

    //trying 3 different login methods, id, email and name just as test TODO
    // made email as unique and will use email to login
    // doctor can view his appointments, all appointments, book an appointment and modify an appointment.
    // based upon this we can crate login of patient and give some options.
    // patients can login an can see just there appointments or can modify there appointments
    // TODO: 2023-04-18
    @RequestMapping("/doctorLogin")
    public String getDoctorByEmail(@RequestParam(value = "docEmail") String docEmail, Model theModel){

        try{
            Doctor existingDoc = doctorService.getDoctorByEmail(docEmail);
            theModel.addAttribute("doctor", existingDoc);
            return "doctor/doctor-detail";

        }catch (EntityNotFoundException exception){
            theModel.addAttribute("doctor", null);
            theModel.addAttribute("exceptionMessage", exception.getMessage());
            return "error";
        }

    }
//    @RequestMapping("/doctorLogin2")
//    public String getDoctorByName(@RequestParam(value = "docName") String name, Model theModel){
//
//        try{
//            Doctor existingDoc = doctorService.getDoctorByName(name);
//            theModel.addAttribute("doctor", existingDoc);
//            return "doctor/doctor-detail";
//
//        }catch (EntityNotFoundException exception){
//            theModel.addAttribute("doctor", null);
//            theModel.addAttribute("exceptionMessage", exception.getMessage());
//            return "error";
//        }
//
//    }
//    @RequestMapping("/doctorLogin3")
//    public String getDoctorByID(@RequestParam(value = "docId") Long docId, Model theModel){
//
//            Doctor existingDoc = doctorService.getDoctorById(docId);
//            theModel.addAttribute("doctor", existingDoc);
//            return "doctor/doctor-detail";
//    }

    @RequestMapping("/patientLogin")
    public String getPatientByEmail(@RequestParam(value = "patEmail") String patEmail, Model theModel) {

        try {
            Patient existingPat = patientService.getPatientByEmail(patEmail);
            theModel.addAttribute("patient", existingPat);
            return "patient/patient-detail";

        } catch (EntityNotFoundException exception) {
            theModel.addAttribute("patient", null);
            theModel.addAttribute("exceptionMessage", exception.getMessage());
            return "error";
        }
    }

    @GetMapping("/list-patients")
    public String listPatients(Model theModel){
        List<Patient> thePatients = patientService.getAllPatients();
        theModel.addAttribute("patients", thePatients);
        return "patient/patient-list";
    }

    @GetMapping("/patient")
    public String getPatientById(@RequestParam("patId") Long theId, Model theModel){
        Patient existingPat = patientService.getPatientById(theId);
        theModel.addAttribute("patient", existingPat);
        return "patient/patient-detail";
    }

    // Show create patient form
    @GetMapping("patient/create-patient")
    public String showCreatePatientForm(Model model) {
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        return "patient/create-patient";
    }

    // Handle create patient form submission
    @PostMapping("patient/create-patient")
    public String createPatient(Patient patient) {
        patientService.savePatient(patient);
        return "redirect:/clinic/list-patients";
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

//    @GetMapping("/appointment/create")
//    public String showCreateAppointmentForm(Model theModel) {
//        List<Patient> patients = patientService.getAllPatients();
//        List<Doctor> doctors = doctorService.getAllDoctors();
//        Appointment appointment= new Appointment();
//        theModel.addAttribute("appointment", appointment);
//        theModel.addAttribute("patients", patients);
//        theModel.addAttribute("doctors", doctors);
//        return "appointments/create-appointment";
//    }
    @GetMapping("/appointment/create")
    public String showCreateAppointmentForm(@RequestParam("patId") Long theId,Model theModel) {
        List<Patient> patients = patientService.getAllPatients();
        List<Doctor> doctors = doctorService.getAllDoctors();
        Doctor doctorFound= doctorService.getDoctorByName("sam");
        Patient foundPatient = patientService.getPatientById(theId);
        Appointment appointment= new Appointment();
        theModel.addAttribute("appointment", appointment);
        //theModel.addAttribute("patients", patients);
        theModel.addAttribute("patient", theId);
        theModel.addAttribute("doctor", doctorFound.getId());
        return "appointments/create-appointment";
    }

    @PostMapping("/appointment/save")
    public String saveAppointment(@Valid @ModelAttribute("appointment") Appointment theAppointment, BindingResult result) {
        if(result.hasErrors()){
            return "redirect:/clinic/list-patients";
        }
        appointmentService.saveAppointment(theAppointment);

        return "redirect:/clinic/";
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
        appointmentService.saveAppointment(theAppointment);
        return "redirect:/clinic/list-appointments";
    }

    @GetMapping("/appointment/delete/{id}")
    public String deleteAppointment(@PathVariable("id") long theId) {
        appointmentService.deleteAppointment(theId);
        return "redirect:/clinic/list-appointments";
    }
}
