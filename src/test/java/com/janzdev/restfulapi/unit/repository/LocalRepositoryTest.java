package com.janzdev.restfulapi.unit.repository;

import com.janzdev.restfulapi.entity.Local;
import com.janzdev.restfulapi.repository.LocalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LocalRepositoryTest {

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    TestEntityManager testEntityManager;


    @BeforeEach
    void setUp() {
        Local local =
                Local.builder()
                        .name("SuperMarket")
                        .floor("Third floor")
                        .code("sup-010-3")
                        .build();
        testEntityManager.persist(local);
    }


    @Test
    public void findLocalByNameIgnoreCaseFound(){
        Optional<Local> local = localRepository.findByNameIgnoreCase("SuperMarket");
        assertEquals(local.get().getName(), "SuperMarket");
        System.out.println("local = " + local.get());
    }

    @Test
    public void findLocalByNameIgnoreCaseNotFound(){
        Optional<Local> local = localRepository.findByNameIgnoreCase("Cinema");
        assertEquals(local, Optional.empty());
    }


}