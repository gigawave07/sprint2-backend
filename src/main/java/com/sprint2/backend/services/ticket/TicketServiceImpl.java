package com.sprint2.backend.services.ticket;

import com.sprint2.backend.entity.*;
import com.sprint2.backend.model.TicketDTO;
import com.sprint2.backend.repository.CarTypeRepository;
import com.sprint2.backend.repository.TicketRepository;
import com.sprint2.backend.services.car.CarService;
import com.sprint2.backend.services.parking_slot.ParkingSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    CarService carService;

    @Autowired
    ParkingSlotService parkingSlotService;

    @Autowired
    CarTypeRepository carTypeRepository;

    @Override
    public List<Ticket> findAll() {
        return this.ticketRepository.findAll();
    }

    @Override
    public Ticket findByID(Long id) {
        return this.ticketRepository.findById(id).orElse(null);
    }

    // Quan start
    @Override
    public Ticket convert(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        Car car = carService.findByPlateNumber(ticketDTO.getCar().getPlateNumber());

        if (car == null) {
            car = carService.convert(ticketDTO.getCar());
        }

        CarType carType = carTypeRepository.findByCarTypeNameContains(
                ticketDTO.getCar().getCarType().getCarTypeName());
        car.setCarType(carType);

        ticket.setCar(car);
        ticket.setEnterDate(ticketDTO.getEnterDate());
        ticket.setExitDate(ticketDTO.getExitDate());
        ticket.setPrice(ticketDTO.getPrice());

        return ticket;
    }

    @Override
    public String save(TicketDTO ticketDTO) {
        Ticket ticket = convert(ticketDTO);
        Car car = carService.findByPlateNumber(ticket.getCar().getPlateNumber());
        // if car not existed in database, create and find again
        if (car == null) {
            carService.save(ticket.getCar());
        }
        car = carService.findByPlateNumber(ticket.getCar().getPlateNumber());

        ParkingSlot parkingSlot = car.getParkingSlot();

        // check if car is already parked
        if (parkingSlot == null) {
            parkingSlot = parkingSlotService.arrangeSlot(car);
            // check if there is available slot if car is not registered
            if (parkingSlot == null) return "no available slot";
        } else {
            // check if there is available slot if car is registered
            if (parkingSlot.getStatus()) return "car is already parked";
        }

        // get current member card
        List<MemberCard> memberCardList = car.getMemberCardList();
        MemberCard memberCard = null;
        if (memberCardList != null && memberCardList.size() != 0) {
            memberCard = memberCardList.get(memberCardList.size() - 1);
        }

        // check if car has available member card
        if (memberCard != null && memberCard.getEndDate().isAfter(LocalDateTime.now())) {
            parkingSlot.setStatus(true);
        } else {
            carService.save(ticket.getCar());
            parkingSlot.setCar(car);
            parkingSlot.setStatus(true);
            ticketRepository.save(ticket);
        }

        return "succeed";
    }

    @Override
    public Boolean close(TicketDTO ticketDTO) {
        Ticket ticket = convert(ticketDTO);
        Car car = carService.findByPlateNumber(ticket.getCar().getPlateNumber());
        ParkingSlot parkingSlot = car.getParkingSlot();
        if (parkingSlot == null) return false;

        List<Ticket> ticketList = car.getTicketList();
        Ticket currentTicket = ticketList.get(ticketList.size() - 1);
        currentTicket.setPrice(ticket.getPrice());
        currentTicket.setExitDate(ticket.getExitDate());
        ticketRepository.save(currentTicket);

        parkingSlot.setStatus(false);
        parkingSlot.setCar(null);
        parkingSlotService.save(parkingSlot);

        return true;
    }
    // Quan end



    /**
     * Nguyen Quang Danh
     *
     * @param fromEnterDay
     * @param toExitDay
     * @return Thống kê doanh thu trong khoảng thời gian (ticket)
     */
    @Override
    public Object getTotalRevenueTicketPeriod(String fromEnterDay, String toExitDay) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate localFromEnterDay = LocalDate.parse(fromEnterDay, formatter);
        LocalDate localToExitDay = LocalDate.parse(toExitDay, formatter);
        return this.ticketRepository.getTotalRevenueTicketPeriod(localFromEnterDay, localToExitDay);
    }


}
