package com.fastcampus.loan.service;

import com.fastcampus.loan.domain.Balance;
import com.fastcampus.loan.dto.BalanceDTO.*;
import com.fastcampus.loan.exception.BaseException;
import com.fastcampus.loan.exception.ResultType;
import com.fastcampus.loan.repository.BalanceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response create(Long applicationId, Request request) {

        if (balanceRepository.findByApplicationId(applicationId).isPresent()) {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }

        Balance balance = modelMapper.map(request, Balance.class);

        balance.setApplicationId(applicationId);
        balance.setBalance(request.getEntryAmount());

        Balance saved = balanceRepository.save(balance);

        return modelMapper.map(saved, Response.class);
    }

}
