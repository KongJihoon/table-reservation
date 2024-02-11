package com.zerobase.tablereservation.auth.service;

import com.zerobase.tablereservation.auth.dto.LogIn;
import com.zerobase.tablereservation.auth.type.UserType;
import com.zerobase.tablereservation.customer.entity.Customer;
import com.zerobase.tablereservation.customer.entity.CustomerRepository;
import com.zerobase.tablereservation.global.exception.CustomException;
import com.zerobase.tablereservation.global.type.ErrorCode;
import com.zerobase.tablereservation.manager.entity.Manager;
import com.zerobase.tablereservation.manager.entity.ManagerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.zerobase.tablereservation.auth.type.UserType.CUSTOMER;
import static com.zerobase.tablereservation.auth.type.UserType.PARTNER;
import static com.zerobase.tablereservation.global.type.ErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final ManagerRepository managerRepository;
    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * 매니저 정보 확인, 패스워드 일치 확인
     */
    public Manager authenticatedManager(LogIn input){
        Manager manager = checkManagerEmail(input.getEmail());

        if(!this.passwordEncoder.matches(input.getPassword(), manager.getPassword())){
            throw new CustomException(PASSWORD_NOT_MATCH);
        }
        return manager;
    }

    /**
     * 고객 정보 확인, 패스워드 일치 확인
     */
    public Customer authenticationCustomer(LogIn input){
        Customer customer = checkCustomerEmail(input.getEmail());

        if(!this.passwordEncoder.matches(input.getPassword(), customer.getPassword())){
            throw new CustomException(PASSWORD_NOT_MATCH);
        }
        return customer;
    }


    /**
     * 회원 이메일을 이용하여 해당 Repository에 이메일과 일치하는 회원 찾기
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if(managerRepository.existsByEmail(email)) {
            Manager manager = checkManagerEmail(email);

            return createUserDetail(manager.getEmail(),manager.getPassword(), PARTNER);
        } else if (this.customerRepository.existsByEmail(email)) {
            Customer customer = checkCustomerEmail(email);


            return createUserDetail(customer.getEmail(), customer.getPassword(), CUSTOMER);
        }


        throw new UsernameNotFoundException("User not found with email: " + email);
    }

    private UserDetails createUserDetail(String name, String password, UserType userType){
        return User.withUsername(name)
                .password(this.passwordEncoder.encode(password))
                .roles(String.valueOf(userType))
                .build();
    }

    /**
     * 매니저 등록 이메일 확인
     */
    private Manager checkManagerEmail(String email){
        return this.managerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(MANAGER_NOT_FOUND));
    }
    /**
     * 고객 등록 이메일 확인
     */
    private Customer checkCustomerEmail(String email){
        return this.customerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(CUSTOMER_NOT_FOUND));
    }
}
