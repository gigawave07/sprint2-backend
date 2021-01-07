package com.sprint2.backend.services.customer;

import com.sprint2.backend.entity.*;
import com.sprint2.backend.model.CustomerDTO;
import com.sprint2.backend.repository.*;
import com.sprint2.backend.services.CarType.CarTypeService;
import com.sprint2.backend.services.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    //Ngan's tasks
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CarService carService;
    @Autowired
    private CarTypeService carTypeService;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private MemberCardRepository memberCardRepository;
    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer findByID(Long id) {
        return this.customerRepository.findById(id).orElse(null);
    }

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomerCode(customerDTO.getCustomerCode());
        customer.setIdentityNumber(customerDTO.getIdentityNumber());
        customer.setFullName(customerDTO.getFullName());
        if (customerDTO.getGender().equals("Nam")) {
            customer.setGender(true);
        } else {
            customer.setGender(false);
        }
        customer.setEmail(customerDTO.getEmail());
        customer.setBirthday(customerDTO.getBirthday());
        customer.setPhone(customerDTO.getPhone());
        customer.setAddress(customerDTO.getAddress());
        this.customerRepository.save(customer);
    }
    @Override
    public void update(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setCustomerCode(customerDTO.getCustomerCode());
        customer.setIdentityNumber(customerDTO.getIdentityNumber());
        customer.setFullName(customerDTO.getFullName());
        if (customerDTO.getGender().equals("Nam")) {
            customer.setGender(true);
        } else {
            customer.setGender(false);
        }
        customer.setEmail(customerDTO.getEmail());
        customer.setBirthday(customerDTO.getBirthday());
        customer.setPhone(customerDTO.getPhone());
        customer.setAddress(customerDTO.getAddress());
//        this.customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
        this.customerRepository.deleteById(id);
    }

    @Override
    public Customer findCustomerByCustomerCode(String customerCode) {
        return customerRepository.findCustomerByCustomerCode(customerCode);
    }

    @Override
    public List<Customer> search(String input, String key, Pageable pageable) {
        if (input.equals("fullName")) {
            return customerRepository.getCustomersByName(key);
        } else if (input.equals("customerCode")) {
            return customerRepository.getCustomersByCustomerCode(key);
        } else if (input.equals("email")) {
            return customerRepository.getCustomersByEmail(key);
        } else if (input.equals("identityNumber")) {
            return customerRepository.getCustomersByIdNumber(key);
        }
        return null;
    }
    //End Ngan's tasks
}
