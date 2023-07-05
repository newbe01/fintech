package com.fastcampus.loan.controller;

import com.fastcampus.loan.dto.JudgmentDTO.*;
import com.fastcampus.loan.dto.ResponseDTO;
import com.fastcampus.loan.service.JudgmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/judgments")
@RestController
public class JudgmentController extends AbstractController {

    private final JudgmentService judgmentService;

    @PostMapping
    public ResponseDTO<Response> create(@RequestBody Request request) {
        return ok(judgmentService.create(request));
    }

    @GetMapping("/{judgmentId}")
    public ResponseDTO<Response> get(@PathVariable Long judgmentId) {
        return ok(judgmentService.get(judgmentId));
    }

    @GetMapping("/applications/{applicationId}")
    public ResponseDTO<Response> getJudgmentOfApplication(@PathVariable Long applicationId) {
        return ok(judgmentService.getJudgmentOfApplication(applicationId));
    }

    @PutMapping("/{judgmentId}")
    public ResponseDTO<Response> update(@PathVariable Long judgmentId, @RequestBody Request request) {
        return ok(judgmentService.update(judgmentId, request));
    }

}
