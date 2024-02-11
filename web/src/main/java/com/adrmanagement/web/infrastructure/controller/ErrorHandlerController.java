package com.adrmanagement.web.infrastructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ErrorHandlerController {
	
	@GetMapping("/403Error")
    public String error403(Model model) {
        return "403page";
    }
	
	@GetMapping("/404Error")
    public String error404(Model model) {
        return "404page";
    }
	
	@GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    }

}
