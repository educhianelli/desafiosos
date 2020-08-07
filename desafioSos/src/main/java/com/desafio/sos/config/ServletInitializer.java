package com.desafio.sos.config;

import org.directwebremoting.spring.DwrSpringServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;

@Controller
@ImportResource(locations = "classpath:spring.xml")
public class ServletInitializer  {

	
	
	@Bean
    public ServletRegistrationBean servletRegistrationBean() {
        DwrSpringServlet servlet = new DwrSpringServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(servlet, "/dwr/*");
        registrationBean.addInitParameter("debug", "true");
        registrationBean.addInitParameter("activeReverseAjaxEnabled", "true");
        registrationBean.addInitParameter("pollAndCometEnabled", "true");
        registrationBean.addInitParameter("allowScriptTagRemoting", "true");
        return registrationBean;
    }

}
