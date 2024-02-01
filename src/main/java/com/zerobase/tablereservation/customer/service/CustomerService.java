package com.zerobase.tablereservation.customer.service;

import com.zerobase.tablereservation.customer.dto.CustomerDto;
import com.zerobase.tablereservation.customer.dto.SignUpCustomer;

public interface CustomerService {

   CustomerDto register(SignUpCustomer customer);

}
