package com.fastcampus.loan.controller;

import com.fastcampus.loan.dto.ApplicationDTO.*;
import com.fastcampus.loan.dto.ResponseDTO;
import com.fastcampus.loan.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/applications")
@RestController
public class ApplicationController extends AbstractController{

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseDTO<Response> create(@RequestBody Request request) {
        return ok(applicationService.create(request));
    }

    @GetMapping("/{applicationId}")
    public ResponseDTO<Response> get(@PathVariable Long applicationId) {
        return ok(applicationService.get(applicationId));
    }

    @PutMapping("/{applicationId}")
    public ResponseDTO<Response> put(@PathVariable Long applicationId, @RequestBody Request request) {
        return ok(applicationService.update(applicationId, request));
    }

    @DeleteMapping("/{applicationId}")
    public ResponseDTO<Void> put(@PathVariable Long applicationId) {

        applicationService.delete(applicationId);

        return ok();
    }

}