package com.sprint2.backend.services.customer;

import com.sprint2.backend.entity.*;
import com.sprint2.backend.model.InformationCustomerDTO;
import com.sprint2.backend.model.ListEntryLogDTO;
import com.sprint2.backend.repository.AppAccountRepository;
import com.sprint2.backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    // Đin
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AppAccountRepository appAccountRepository;

    // Update Customer
    @Override
    public void updateCustomer(InformationCustomerDTO customerDTO) {
        // Tìm kiếm customer tương ứng với giá trị thuộc tính id của customerDTO
        Customer customer = this.customerRepository.findById(customerDTO.getId()).orElse(null);
        // Set các giá trị tương ứng của customerDTO cho customer nếu customer không null với giá trị thuộc tính 'id'
        // của customerDTO
        if (customer != null) {
            customer.setAddress(customerDTO.getAddress());
            customer.setFullName(customerDTO.getFullName());
            customer.setGender(customerDTO.getGender());
            customer.setEmail(customerDTO.getEmail());
            customer.setPhone(customerDTO.getPhone());
            customer.setIdentityNumber(customerDTO.getIdentityNumber());
            customer.setImageAvatar(customerDTO.getImageAvatar());
            customer.setBirthday(customerDTO.getBirthday());
            this.customerRepository.save(customer);
        }
    }

    @Override
    public Customer findByID(Long id) {
        return this.customerRepository.findById(id).orElse(null);
    }

    // Tìm kiếm customer theo appAccount
    @Override
    public Customer findByAppAccount(Long accountId) {
        return (this.appAccountRepository.findById(accountId).orElse(null)).getCustomer();
    }

    /**
     * nguyen quoc khanh
     * @param id
     * @return
     */
    @Override
    public Customer findCustomerByAppAccountId(Long id) {
        return this.customerRepository.findCustomerByAppAccountId(id);
    }


    /**
     * Nguyen Quang Danh
     *
     * @return Thống kê số lượng khách hàng
     */
    @Override
    public Long getTotalCustomer() {
        return this.customerRepository.getTotalCustomer();
    }


    /**
     * @param fromDay
     * @param toDay
     * @return Thống kê số lượng khách hàng đăng ký trong khoảng thời gian
     * @throws ParseException
     */
    @Override
    public Object getToTalCustomerRegisterPeriod(String fromDay, String toDay) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate localFromDay = LocalDate.parse(fromDay, formatter);
        LocalDate localToDay = LocalDate.parse(toDay, formatter);
        return this.customerRepository.getToTalCustomerRegisterPeriod(localFromDay, localToDay);
    }



    // Lấy page phân trang của lịch sử xe ra vào
    @Override
    public Page<ListEntryLogDTO> findListEntryLog(Long accountId, int pageable) {
        List<ListEntryLogDTO> listEntryLogDTOS = new ArrayList<>();
        AppAccount appAccount = this.appAccountRepository.findById(accountId).orElse(null);
        // Sử dụng if appAccount không null thì lấy thông tin của customer theo tài appAccount
        if (appAccount != null) {
            Customer customer = appAccount.getCustomer();
            //   Lấy danh sách xe của customer
            List<Car> listCar = customer.getCarList();
            List<MemberCard> listMemberCar;
            List<EntryLog> listEntryLog;
            // Duyệt qua danh sách xe để lấy thẻ thành viên tương ứng với từng xe
            for (Car car : listCar) {
                listMemberCar = car.getMemberCardList();
                // Duyệt qua danh sách thẻ thành viên của từng xe tính cả thẻ thành viên đã hết hạn
                for (MemberCard memberCard : listMemberCar) {
                    listEntryLog = memberCard.getEntryLogList();
                    if (listEntryLog != null) {
                        // Duyệt qua lịch sử ra vào của từng thẻ tương ứng với từng xe và lưu vào danh sách
                        // listEntryLogDTOS
                        for (EntryLog entryLog : listEntryLog) {
                            ListEntryLogDTO listEntryLogDTO = new ListEntryLogDTO();
                            listEntryLogDTO.setBrandName(car.getBrandName());
                            listEntryLogDTO.setPlateNumber(car.getPlateNumber());
                            listEntryLogDTO.setEnterDate(entryLog.getEnterDate());
                            listEntryLogDTO.setExitDate(entryLog.getExitDate());
                            listEntryLogDTOS.add(listEntryLogDTO);
                        }
                    }
                }
            }
        }
        // Tạo 1 pageable để xác định số lượng entry log cho 1 trang và vị trí trang xuất hiện
        Pageable pageable1 = PageRequest.of(pageable, 5);
        int start = (int) pageable1.getOffset();
        int end = (int) (Math.min((start + pageable1.getPageSize()), listEntryLogDTOS.size()));
        return new PageImpl<ListEntryLogDTO>(listEntryLogDTOS.subList(start, end), pageable1, listEntryLogDTOS.size());
    }
    // End
}
