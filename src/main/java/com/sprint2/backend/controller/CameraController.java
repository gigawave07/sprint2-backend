package com.sprint2.backend.controller;


import com.sprint2.backend.entity.Car;
import com.sprint2.backend.entity.Image;
import com.sprint2.backend.services.camera.CameraService;
import net.sf.javaanpr.configurator.Configurator;
import net.sf.javaanpr.imageanalysis.CarSnapshot;
import net.sf.javaanpr.intelligence.Intelligence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;


@RestController
@CrossOrigin
public class CameraController {
    @Autowired
    private CameraService cameraService;

    @PostMapping("/api/7/post-src-image")
    public ResponseEntity<String> mainTest(@RequestBody Image newImage) throws Exception {
        String plateNumber = this.cameraService.getNumberPlateFromImage(newImage);
        String check = this.cameraService.checkMemberOfCar(plateNumber);
        System.out.println(plateNumber);
        System.out.println(check);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
