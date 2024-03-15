package edu.pro.spbbase.repository;

import edu.pro.spbbase.model.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
  @author   george
  @project   spb-base
  @class  ItemRepositoryTest
  @version  1.0.0 
  @since 08.03.24 - 11.44
*/

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataMongoTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository underTest;

    @BeforeEach
    void setUp() {
         List<Item> items = List.of(
                new Item("t1", "John", "000001", " ###test"),
                new Item("t2", "Paul", "000002", "tgcfhcvhc ###test"),
                new Item("t3", "Freddie", "000003", "###test vhgvhgv")
        );
         underTest.saveAll(items);

    }

    @AfterEach
    void tearDown() {
        List<Item> items = underTest.findAll()
                .stream().filter(item -> item.getDescription().contains("test"))
                .toList();
           underTest.deleteAll(items);
    }

    @Test
    void itShouldCheckThrCollectionIsNotEmpty() {
        assertFalse(underTest.findAll().isEmpty());
        List<Item> items = underTest.findAll()
                .stream().filter(item -> item.getDescription().contains("test"))
                .toList();
        assertEquals(items.size(), 3);
    }

    @Test
    void itShouldSaveItem() {
        //given
        Item testItem = new Item("Test", "0004", " jhvb ###test");
        // when
        underTest.save(testItem);
        // then
        Item forTest = underTest.findAll().stream()
                .filter(item -> item.getName().equals("Test"))
                .filter(item -> item.getDescription().contains("test"))
                .findAny()
                .orElse(null);
        assertNotNull(forTest);
        assertNotNull(forTest.getId());
        assertFalse(forTest.getId().isEmpty());
        assertEquals(forTest.getCode(), "0004");
    }

    // TODO:  check up update, delete

}
