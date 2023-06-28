package com.fastcampus.loan.service;

import java.math.BigDecimal;

import com.fastcampus.loan.domain.Application;
import com.fastcampus.loan.dto.ApplicationDTO.*;
import com.fastcampus.loan.repository.ApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTest {

    @InjectMocks
    ApplicationServiceImpl applicationService;

    @Mock
    private ApplicationRepository applicationRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void Should_ReturnResponseOfNewApplyEntity_When_RequestApply() {
        Application entity = Application.builder()
                .name("Member Kim")
                .cellPhone("010-1111-2222")
                .email("mail@abc.de")
                .hopeAmount(BigDecimal.valueOf(50000000))
                .build();

        Request request = Request.builder()
                .name("Member Kim")
                .cellPhone("010-1111-2222")
                .email("mail@abc.de")
                .hopeAmount(BigDecimal.valueOf(50000000))
                .build();

        when(applicationRepository.save(any(Application.class))).thenReturn(entity);

        Response actual = applicationService.create(request);

        assertThat(actual.getName()).isEqualTo(entity.getName());
    }

}
