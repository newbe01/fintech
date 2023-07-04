package com.fastcampus.loan.service;

import com.fastcampus.loan.domain.Application;
import com.fastcampus.loan.domain.Judgment;
import com.fastcampus.loan.dto.JudgmentDTO.*;
import com.fastcampus.loan.repository.ApplicationRepository;
import com.fastcampus.loan.repository.JudgmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JudgmentServiceTest {

    @InjectMocks
    private JudgmentServiceImpl judgmentService;

    @Mock
    private JudgmentRepository judgmentRepository;

    @Mock
    private ApplicationRepository applicationRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void Should_ReturnResponseOfNewJudgmentEntity_When_RequestNewJudgment() {
        Judgment judgmentEntity = Judgment.builder()
                .name("Member Kim")
                .applicationId(1L)
                .approvalAmount(BigDecimal.valueOf(50000000))
                .build();

        Application applicationEntity = Application.builder()
                .applicationId(1L)
                .build();

        Request request = Request.builder()
                .name("Member Kim")
                .applicationId(1L)
                .approvalAmount(BigDecimal.valueOf(50000000))
                .build();

        when(applicationRepository.findById(1L)).thenReturn(Optional.ofNullable(applicationEntity));
        when(judgmentRepository.save(any(Judgment.class))).thenReturn(judgmentEntity);

        Response actual = judgmentService.create(request);

        assertThat(actual.getJudgmentId()).isEqualTo(judgmentEntity.getJudgmentId());
        assertThat(actual.getName()).isEqualTo(judgmentEntity.getName());
        assertThat(actual.getApplicationId()).isEqualTo(judgmentEntity.getApplicationId());
        assertThat(actual.getApprovalAmount()).isEqualTo(judgmentEntity.getApprovalAmount());

    }

}
