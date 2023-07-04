package com.fastcampus.loan.service;

import com.fastcampus.loan.domain.Judgment;
import com.fastcampus.loan.dto.JudgmentDTO.*;
import com.fastcampus.loan.exception.BaseException;
import com.fastcampus.loan.exception.ResultType;
import com.fastcampus.loan.repository.ApplicationRepository;
import com.fastcampus.loan.repository.JudgmentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class JudgmentServiceImpl implements JudgmentService {

    private final JudgmentRepository judgmentRepository;
    private final ApplicationRepository applicationRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response create(Request request) {

        Long applicationId = request.getApplicationId();
        if (!isPresentApplication(applicationId)) {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }

        Judgment judgment = modelMapper.map(request, Judgment.class);

        Judgment saved = judgmentRepository.save(judgment);

        return modelMapper.map(saved, Response.class);
    }

    private boolean isPresentApplication(Long applicationId) {
        return applicationRepository.findById(applicationId).isPresent();
    }

}
