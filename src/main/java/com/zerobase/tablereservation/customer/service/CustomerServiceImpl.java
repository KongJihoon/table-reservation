package com.zerobase.tablereservation.customer.service;

import com.zerobase.tablereservation.auth.type.UserType;
import com.zerobase.tablereservation.customer.dto.CustomerDto;
import com.zerobase.tablereservation.customer.dto.SignUpCustomer;
import com.zerobase.tablereservation.customer.entity.Customer;
import com.zerobase.tablereservation.customer.entity.CustomerRepository;
import com.zerobase.tablereservation.global.exception.CustomException;
import com.zerobase.tablereservation.global.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.zerobase.tablereservation.global.type.ErrorCode.ALREADY_EXIST_USER;
import static com.zerobase.tablereservation.global.type.ErrorCode.CUSTOMER_NOT_FOUND;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public CustomerDto register(SignUpCustomer customer) {

        boolean exists = this.customerRepository.existsByEmail(customer.getEmail());

        if(exists){
            throw new CustomException(ALREADY_EXIST_USER);
        }

        customer.setPassword(this.encoder.encode(customer.getPassword()));

        Customer setCustomer = this.customerRepository.save(Customer.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .phone(customer.getPhone())
                .userType(UserType.CUSTOMER)
                .build());

        return CustomerDto.fromEntity(setCustomer);
    }

    @Override
    public CustomerDto customerDetail(Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomException(CUSTOMER_NOT_FOUND));

        return CustomerDto.fromEntity(customer);
    }
}
