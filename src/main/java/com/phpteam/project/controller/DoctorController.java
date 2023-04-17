package com.phpteam.project.controller;

import com.phpteam.project.model.Doctor;
import com.phpteam.project.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @GetMapping("/list")
    public String listDoctors(Model theModel){
        List<Doctor> theDoctors = doctorService.getAllDoctors();

        theModel.addAttribute("doctors", theDoctors);

        return "doctors/list-doctors";
    }

}
