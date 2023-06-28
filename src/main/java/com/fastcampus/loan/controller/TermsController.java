package com.fastcampus.loan.controller;

import com.fastcampus.loan.dto.ResponseDTO;
import com.fastcampus.loan.dto.TermsDTO.*;
import com.fastcampus.loan.service.TermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/terms")
@RestController
public class TermsController extends AbstractController{

    private final TermsService termsService;

    @PostMapping
    public ResponseDTO<Response> create(@RequestBody Request request) {

        return ok(termsService.create(request));
    }

}
