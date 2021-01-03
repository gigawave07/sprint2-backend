package com.sprint2.backend.services.camera;

import com.sprint2.backend.entity.Car;
import com.sprint2.backend.entity.Image;
import com.sprint2.backend.model.MessageFromCamera;

public interface CameraService {
    String getNumberPlateFromImage(Image image);
    String checkMemberOfCar(String numberPlate);
//    MessageFromCamera getMessageCamera(Car car);
}
