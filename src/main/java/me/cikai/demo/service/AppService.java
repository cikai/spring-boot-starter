package me.cikai.demo.service;

import me.cikai.demo.model.App;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppService extends CrudRepository<App, Long> {
  List<App> findAppById(int id);
}
