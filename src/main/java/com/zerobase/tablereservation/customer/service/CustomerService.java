package com.zerobase.tablereservation.customer.service;

import com.zerobase.tablereservation.customer.dto.CustomerDto;
import com.zerobase.tablereservation.customer.dto.SignUpCustomer;

public interface CustomerService {

   /**
    * 회원 가입
    */
   CustomerDto register(SignUpCustomer customer);


   /**
    * 고객 정보 확인
    */
   CustomerDto customerDetail(Long customerId);

}
