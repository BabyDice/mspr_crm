// com.msprcrm.msprcrm.controller.StatistiquesController

package com.msprcrm.msprcrm.controller;

import com.msprcrm.msprcrm.service.StatistiquesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class StatistiquesController {

    @Autowired
    private StatistiquesService statistiquesService;

    @GetMapping("/stats")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getStats() {
        try {
            Map<String, Object> stats = statistiquesService.getStatistiques();
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Exception during getStats: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
