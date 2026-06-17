package com.library.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.library.Service.DashboardService;
import com.library.dto.DashboardResponse;

@Controller
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/")
    public String dashboard(Model model){

        DashboardResponse stats =
                dashboardService.getDashboardStats();

        model.addAttribute("stats", stats);

        return "index";
    }
}