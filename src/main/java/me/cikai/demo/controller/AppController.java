package me.cikai.demo.controller;

import me.cikai.demo.model.App;
import me.cikai.demo.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequestMapping(path = "/app")
public class AppController {

  @Autowired
  private AppService service;

  @GetMapping(path = "/add")
  public void add(@RequestParam String name,
                  @RequestParam String version) {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    App app = new App();
    app.setName(name);
    app.setVersion(version);
    app.setCreatedAt(timestamp);

    service.save(app);
  }

  @GetMapping(path = "/delete")
  public void delete(String id) {
    App app = new App();
    app.setId(Integer.parseInt(id));

    service.delete(app);
  }

  @GetMapping(path = "/get")
  public Iterable<App> getAppAll() {
    return service.findAll();
  }

  @GetMapping(path = "/getById")
  public Iterable<App> getAppById(@RequestParam String id) {
    return service.findAppById(Integer.parseInt(id));
  }
}
