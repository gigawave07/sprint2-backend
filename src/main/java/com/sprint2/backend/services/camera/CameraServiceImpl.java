package com.sprint2.backend.services.camera;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.entity.Image;
import com.sprint2.backend.entity.MemberCard;
import com.sprint2.backend.entity.ParkingSlot;
import com.sprint2.backend.model.MessageFromCamera;
import com.sprint2.backend.repository.CarRepository;
import com.sprint2.backend.repository.MemberCardRepository;
import com.sprint2.backend.repository.ParkingSlotRepository;
import net.sf.javaanpr.configurator.Configurator;
import net.sf.javaanpr.imageanalysis.CarSnapshot;
import net.sf.javaanpr.intelligence.Intelligence;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class CameraServiceImpl implements CameraService{
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private MemberCardRepository memberCardRepository;

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    private File fileImg = new File("E:\\Hướng dẫn\\Video xin viec\\Giới thiệu\\1.image-car");


    @Override
    public String getNumberPlateFromImage(Image image) {
        String numberPlate= "";
        String srcImg = fileImg.getAbsolutePath() + "\\" + image.getSrc();
        try {
            Configurator.getConfigurator().loadConfiguration("src/main/resources/static/config.xml");
            Intelligence intel = new Intelligence();
            CarSnapshot carSnap = new CarSnapshot(srcImg);
            numberPlate = intel.recognize(carSnap);
        } catch (Exception e) {
            e.printStackTrace();
            numberPlate = "Can't read";
        }
        return numberPlate;
    }

    @Override
    public String checkMemberOfCar(String numberPlate) {
        Car carOnPlateNumber = this.carRepository.getCarByPlateNumber(numberPlate);
        if (carOnPlateNumber !=null){
            long idCar = carOnPlateNumber.getId();
            List<MemberCard> cardMemberOfcar = this.memberCardRepository.checkMemberCar(idCar);
            int memberCardTemp = cardMemberOfcar.size();
            if (memberCardTemp > 0){
                MemberCard memberCardInExpiryDate = this.memberCardRepository.getExpiryDateOfCar(idCar);
                ParkingSlot parkingSlotOfCar = this.parkingSlotRepository.getParkingSlotByCarId(idCar);
                if (memberCardInExpiryDate!=null){
                    parkingSlotOfCar.setStatus(!parkingSlotOfCar.getStatus());
                    this.parkingSlotRepository.save(parkingSlotOfCar);
                    return "Member in expiry day to : " + memberCardInExpiryDate.getEndDate();
                }
                return "Member not in expiry";
            }
            return "Not member";
        }
        return "Not in database";
    }

//    @Override
//    public MessageFromCamera getMessageCamera(Car car) {
//        MessageFromCamera newMessage = new MessageFromCamera();
//        List<MemberCard> carMemberOfcar = this.memberCardRepository.getExpiryDateByCarId(car.getId());
//        if (car == null){
//            System.out.println("Không có member");
//            return newMessage;
//        }
//        System.out.println("có member");
//        if (carMemberOfcar == null){
//            System.out.println("Xe "+ car.getPlateNumber()+ " đã quá hạn thẻ member !");
//            return newMessage;
//        }
//        System.out.println("Xe ok");
//        return newMessage;
//    }
}
