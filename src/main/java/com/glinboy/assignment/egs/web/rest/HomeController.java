package com.glinboy.assignment.egs.web.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping(value = "/")
	public void redirect(HttpServletResponse response) throws IOException {
		response.sendRedirect("/swagger-ui.html");
	}

}
