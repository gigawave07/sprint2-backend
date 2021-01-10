package com.sprint2.backend.controller;

import com.sprint2.backend.entity.AppAdmin;
import com.sprint2.backend.model.MessageDTO;
import com.sprint2.backend.services.app_admin.AppAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AppAdminController {
    @Autowired
    private AppAdminService appAdminService;

    // --------------------------- Vinh Begin -----------------------------------
    @GetMapping("/getAdminInformation/{adminId}")
    private ResponseEntity<?> getAdminInformation(@PathVariable Long adminId){
        AppAdmin appAdmin = null;
        if (adminId != null){
            appAdmin = this.appAdminService.findByID(adminId);
        }
        return (appAdmin != null) ? ResponseEntity.ok(appAdmin): ResponseEntity.ok(new MessageDTO("not found"));
    }
    // --------------------------- Vinh Begin -----------------------------------
}
