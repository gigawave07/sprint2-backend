package com.sprint2.backend.services.customer_ngan;

import com.sprint2.backend.entity.*;
import com.sprint2.backend.model.NganCustomerDTO;
import com.sprint2.backend.repository.*;
import com.sprint2.backend.services.CarTypeNgan.CarTypeService;
import com.sprint2.backend.services.carNgan.CarService;
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
    public void saveCustomer(NganCustomerDTO nganCustomerDTO) {
        Customer customer = new Customer();
        customer.setCustomerCode(nganCustomerDTO.getCustomerCode());
        customer.setIdentityNumber(nganCustomerDTO.getIdentityNumber());
        customer.setFullName(nganCustomerDTO.getFullName());
        if (nganCustomerDTO.getGender().equals("Nam")) {
            customer.setGender(true);
        } else {
            customer.setGender(false);
        }
        customer.setEmail(nganCustomerDTO.getEmail());
        customer.setBirthday(nganCustomerDTO.getBirthday());
        customer.setPhone(nganCustomerDTO.getPhone());
        customer.setAddress(nganCustomerDTO.getAddress());
        this.customerRepository.save(customer);
    }
    @Override
    public void update(NganCustomerDTO nganCustomerDTO) {
        Customer customer = new Customer();
        customer.setCustomerCode(nganCustomerDTO.getCustomerCode());
        customer.setIdentityNumber(nganCustomerDTO.getIdentityNumber());
        customer.setFullName(nganCustomerDTO.getFullName());
        if (nganCustomerDTO.getGender().equals("Nam")) {
            customer.setGender(true);
        } else {
            customer.setGender(false);
        }
        customer.setEmail(nganCustomerDTO.getEmail());
        customer.setBirthday(nganCustomerDTO.getBirthday());
        customer.setPhone(nganCustomerDTO.getPhone());
        customer.setAddress(nganCustomerDTO.getAddress());
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
