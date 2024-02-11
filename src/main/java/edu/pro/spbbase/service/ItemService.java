package edu.pro.spbbase.service;
/*
  @author   george
  @project   spb-base
  @class  ItemService
  @version  1.0.0 
  @since 11.02.24 - 12.28
*/

import edu.pro.spbbase.model.Item;
import edu.pro.spbbase.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    private List<Item> items = List.of(
            new Item("1", "name1", "000001", "description1"),
            new Item("2", "name2", "000002", "description2"),
            new Item("3", "name3", "000003", "description3")

    );

  //  @PostConstruct
    void init() {
        repository.saveAll(items);
    }

    // CRUD

    public List<Item> getAll() {
        return repository.findAll();
    }

    public Item getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Item create(Item item) {
        return repository.save(item);
    }



}
