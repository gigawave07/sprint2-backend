package com.sprint2.backend.controller;


import com.sprint2.backend.entity.Image;
import net.sf.javaanpr.configurator.Configurator;
import net.sf.javaanpr.imageanalysis.CarSnapshot;
import net.sf.javaanpr.intelligence.Intelligence;
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
    File fileImg = new File("E:\\Hướng dẫn\\Video xin viec\\Giới thiệu\\1.image-car");
    @PostMapping("/api/7/post-src-image")
    public ResponseEntity<String> mainTest(@RequestBody Image newImage) throws Exception {
//        Main.systemLogic.recognize(new CarSnapshot("C:\\Users\\PC\\Desktop\\git_clone_img\\javaanpr_2\\javaanpr\\lib\\test_001.jpg"));
//        Main.systemLogic = new Intelligence(false);
        String numberPlate = null;
        try {
            Configurator.getConfigurator().loadConfiguration("src/main/resources/static/config.xml");
            Intelligence intel = new Intelligence();
            CarSnapshot carSnap = new CarSnapshot(fileImg.getAbsolutePath() + "\\" + newImage.getSrc());
            numberPlate = intel.recognize(carSnap);
            System.out.println(numberPlate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
