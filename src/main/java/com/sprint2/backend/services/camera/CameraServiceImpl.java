package com.sprint2.backend.services.camera;

import com.sprint2.backend.entity.*;
import com.sprint2.backend.model.MessageFromCamera;
import com.sprint2.backend.repository.CarRepository;
import com.sprint2.backend.repository.EntryLogRepository;
import com.sprint2.backend.repository.MemberCardRepository;
import com.sprint2.backend.repository.ParkingSlotRepository;
import net.sf.javaanpr.configurator.Configurator;
import net.sf.javaanpr.imageanalysis.CarSnapshot;
import net.sf.javaanpr.intelligence.Intelligence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CameraServiceImpl implements CameraService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private MemberCardRepository memberCardRepository;

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Autowired
    private EntryLogRepository entryLogRepository;

    private File fileImg = new File("E:\\Hướng dẫn\\Video xin viec\\Giới thiệu\\1.image-car");

    private MessageFromCamera messageFromCamera = new MessageFromCamera();

    @Override
    public String getNumberPlateFromImage(Image image) {
        String numberPlate;
        String srcImg = fileImg.getAbsolutePath() + "\\" + image.getSrc();
        this.messageFromCamera.setSrcImg(srcImg);
        try {
            Configurator.getConfigurator().loadConfiguration("src/main/resources/static/config.xml");
            Intelligence intel = new Intelligence();
            CarSnapshot carSnap = new CarSnapshot(srcImg);
            numberPlate = intel.recognize(carSnap);
        } catch (Exception e) {
            e.printStackTrace();
            numberPlate = "";
        }
        return numberPlate;
    }

    @Override
    public MessageFromCamera checkMemberOfCar(String numberPlate) {
        messageFromCamera.setPlateNumber(numberPlate);
        if (numberPlate == null || numberPlate.equals("") || !numberPlate.matches("^([A-Z]|\\d){6,10}$")) {
            this.messageFromCamera.setMessage("Can't read");
            return this.messageFromCamera;
        }
        Car carOnPlateNumber = this.carRepository.getCarByPlateNumber(numberPlate);
        if (carOnPlateNumber != null) {
            long idCar = carOnPlateNumber.getId();
            List<MemberCard> cardMemberOfcar = this.memberCardRepository.checkMemberCar(idCar);
            int memberCardTemp = cardMemberOfcar.size();
            if (memberCardTemp > 0) {
                try {
                    MemberCard memberCardInExpiryDate = this.memberCardRepository.getExpiryDateOfCar(idCar);
                    ParkingSlot parkingSlotOfCar = this.parkingSlotRepository.getParkingSlotByCarId(idCar);
                    if (memberCardInExpiryDate != null) {
                        parkingSlotOfCar.setStatus(!parkingSlotOfCar.getStatus());
                        this.parkingSlotRepository.save(parkingSlotOfCar);
                        this.messageFromCamera.setStatus(parkingSlotOfCar.getStatus());
                        this.messageFromCamera.setExpirationDate(memberCardInExpiryDate.getEndDate());
//                        Save entrylog
                        EntryLog entryLog = new EntryLog();
                        entryLog.setMemberCard(memberCardInExpiryDate);
                        List<EntryLog> entryLogList = this.entryLogRepository.getEntryLogByMemberCard(memberCardInExpiryDate.getId());
                        if (entryLogList == null  && entryLogList.size() == 0){
                            this.entryLogRepository.save(entryLog);
                        }
                        if ( this.messageFromCamera.getStatus()){
                            entryLog.setEnterDate(LocalDateTime.now());
                        } else {
                            entryLog = entryLogList.get(entryLogList.size()-1);
                            entryLog.setExitDate(LocalDateTime.now());
                        }
                        this.entryLogRepository.save(entryLog);
                        this.messageFromCamera.setEntryLog(LocalDateTime.now());
                        this.messageFromCamera.setMessage("Member ok");
                    } else {
                        this.messageFromCamera.setMessage("Member not in expiry");
                    }
                } catch (Exception e) {
                    this.messageFromCamera.setMessage("Database error");
                }
                return this.messageFromCamera;
            }
            this.messageFromCamera.setMessage("Not member");
            return this.messageFromCamera;
        }
        this.messageFromCamera.setMessage("Not in database");
        return this.messageFromCamera;
    }
}
