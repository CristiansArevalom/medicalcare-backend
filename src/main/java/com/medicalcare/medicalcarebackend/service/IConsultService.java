package com.medicalcare.medicalcarebackend.service;

import com.medicalcare.medicalcarebackend.model.Consult;
import com.medicalcare.medicalcarebackend.model.Exam;

import java.util.List;

public interface IConsultService extends ICRUD <Consult, Integer>{

    Consult saveTransactional (Consult consult , List<Exam> exams);
}
