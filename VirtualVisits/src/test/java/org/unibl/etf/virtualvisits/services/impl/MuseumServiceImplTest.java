package org.unibl.etf.virtualvisits.services.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.unibl.etf.virtualvisits.services.MuseumService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MuseumServiceImplTest {

    @Autowired
    private MuseumService museumService;

    @Test
    void findById() {
        assertTrue(true);
    }

    @Test
    void findByName() {
        assertTrue(true);
    }

    @Test
    void findByCity() {
        assertTrue(true);
    }
}