package com.fastcampus.loan.service;

import com.fastcampus.loan.domain.Terms;
import com.fastcampus.loan.dto.TermsDTO.*;
import com.fastcampus.loan.repository.TermsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class TermsServiceImpl implements TermsService{

    private final TermsRepository termsRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response create(Request request) {

        Terms terms = modelMapper.map(request, Terms.class);

        Terms created = termsRepository.save(terms);

        return modelMapper.map(created, Response.class);
    }

}
