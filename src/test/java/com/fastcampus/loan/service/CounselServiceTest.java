package com.fastcampus.loan.service;

import com.fastcampus.loan.domain.Counsel;
import com.fastcampus.loan.dto.CounselDTO.*;
import com.fastcampus.loan.exception.BaseException;
import com.fastcampus.loan.exception.ResultType;
import com.fastcampus.loan.repository.CounselRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CounselServiceTest {

    @InjectMocks
    CounselServiceImpl counselService;

    @Mock
    private CounselRepository counselRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void Should_ReturnResponseOfNewCounselEntity_When_RequestCounsel() {

        Counsel entity = Counsel.builder()
                .name("Member")
                .cellPhone("010-1111-2222")
                .email("te@st.com")
                .memo("for test memo")
                .address("address")
                .addressDetail("detail address")
                .zipCode("1234")
                .build();

        Request request = Request.builder()
                .name("Member")
                .cellPhone("010-1111-2222")
                .email("te@st.com")
                .memo("for test memo")
                .address("address")
                .addressDetail("detail address")
                .zipCode("1234")
                .build();

        when(counselRepository.save(any(Counsel.class))).thenReturn(entity);

        Response actual = counselService.create(request);

        assertThat(actual.getName()).isEqualTo(entity.getName());

    }

    @Test
    void Should_ReturnResponseOfExistCounselEntity_When_RequestExistCounselId() {

        Long findId = 1L;

        Counsel entity = Counsel.builder()
                .counselId(1L)
                .build();

        when(counselRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = counselService.get(findId);

        assertThat(actual.getCounselId()).isEqualTo(findId);
    }

    @Test
    void Should_ThrowException_When_RequestNotExistCounselId() {

        Long findId = 4L;

        when(counselRepository.findById(findId)).thenThrow(new BaseException(ResultType.SYSTEM_ERROR));

        Assertions.assertThrows(BaseException.class, () -> counselService.get(findId));
    }

}