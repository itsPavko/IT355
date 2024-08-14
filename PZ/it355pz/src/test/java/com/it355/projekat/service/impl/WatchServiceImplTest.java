package com.it355.projekat.service.impl;

import com.it355.projekat.entity.Company;
import com.it355.projekat.entity.Watch;
import com.it355.projekat.entity.enums.WatchType;
import com.it355.projekat.repository.WatchRepository;
import com.it355.projekat.service.CompanyService;
import com.it355.projekat.service.WatchService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = {WatchServiceImpl.class})
public class WatchServiceImplTest {
    @MockBean
    private WatchRepository watchRepository;
    @MockBean
    private CompanyService companyService;
    @Autowired
    private WatchService watchService;

    private Company company;
    private Watch watch;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // Test data
        company = new Company();
        company.setId(1);
        company.setCompanyName("Company 1");
        company.setDirectorName("Doe");
        company.setEstablishedYear(1991);
        company.setCountry("USA");

        watch = new Watch();
        watch.setId(1);
        watch.setCompany(company);
        watch.setName("Test watch");
        watch.setPrice(10.99);
        watch.setImage("watch_image.jpg");
        watch.setWatchType(WatchType.FEMALE);
        watch.setAmount(10);
        watch.setDescription("This is a test watch.");
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testUpdateBook() {
        Integer watchId = 1;
        Integer companyId = 1;

        when(watchRepository.save(watch)).thenReturn(watch);
        when(companyService.findById(companyId)).thenReturn(company);

        watch.setName("Test");
        Watch updatedWatch = watchService.updateWatch(watch, watchId, companyId);

        assertEquals(companyId, updatedWatch.getCompany().getId());
        assertEquals(watchId, updatedWatch.getId());
        assertEquals("Test", updatedWatch.getName());
    }
}