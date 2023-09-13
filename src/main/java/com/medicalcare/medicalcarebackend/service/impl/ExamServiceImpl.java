package com.medicalcare.medicalcarebackend.service.impl;

import com.medicalcare.medicalcarebackend.model.Exam;
import com.medicalcare.medicalcarebackend.repository.IExamRepo;
import com.medicalcare.medicalcarebackend.repository.IGenericRepo;
import com.medicalcare.medicalcarebackend.service.IExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ExamServiceImpl extends CRUDImpl<Exam, Integer> implements IExamService {

    //@Autowired
    private final IExamRepo repo; // = new ExamRepo();
    @Override
    protected IGenericRepo<Exam, Integer> getRepo() {
        return repo;
    }
}
