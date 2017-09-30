package me.cikai.demo.controller;

import me.cikai.demo.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Configuration
@EnableWebSecurity
@RequestMapping(path = "/admin")
public class AdminController extends WebSecurityConfigurerAdapter {

  @Autowired
  private AppService service;

  @GetMapping(path = "")
  public String admin() {
    return "admin";
  }

  @GetMapping(path = "/app")
  public String version(Model model) {
    model.addAttribute("apps", service.findAll());
    return "app";
  }

  @GetMapping(path = "/login")
  public String advice() {
    return "login";
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeRequests()
      .antMatchers("/admin", "/admin/*", "/app/add", "/app/delete").authenticated()
      .and().formLogin().loginPage("/admin/login").permitAll()
      .and().logout().permitAll().logoutSuccessUrl("/admin/login?logout");
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .inMemoryAuthentication()
      .withUser("app").password("123456").roles("ADMIN");
  }
}
