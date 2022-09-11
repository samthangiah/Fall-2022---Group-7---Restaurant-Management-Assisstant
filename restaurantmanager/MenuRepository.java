package edu.sru.group7.restaurantmanager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import edu.sru.group7.restaurantmanager.domain.Menu;

@Service
public interface MenuRepository extends CrudRepository<Menu, Long> {}