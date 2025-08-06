package com.janzdev.restfulapi.service;

import com.janzdev.restfulapi.entity.Local;
import com.janzdev.restfulapi.repository.LocalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocalServiceTest {

    @Autowired
    private LocalService localService;
    @MockBean
    private LocalRepository localRepository;

    @BeforeEach
    void setUp() {
        Local local = Local.builder()
                .id(1L)
                .name("CoffeeShop")
                .floor("First floor")
                .code("cof-005-01")
                .build();

        Mockito.when(localRepository.findByNameIgnoreCase("CoffeeShop")).thenReturn(Optional.of(local));
    }

    @Test
    @DisplayName("Prueba de obtención de información de un local enviando un nombre válido")
    public void findByNameIgnoreCaseShouldFound(){
        String localName = "CoffeeShop";
        Local local = localService.findByNameIgnoreCase(localName).get();
        assertEquals(localName,local.getName());
        System.out.println("local = " + local);
    }
}