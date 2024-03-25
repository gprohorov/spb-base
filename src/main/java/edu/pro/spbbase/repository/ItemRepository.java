package edu.pro.spbbase.repository;
/*
  @author   george
  @project   spb-base
  @class  ItemRepository
  @version  1.0.0 
  @since 11.02.24 - 12.22
*/

import edu.pro.spbbase.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
    boolean existsItemByCode(String code);
}
