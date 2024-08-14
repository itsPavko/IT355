package com.it355.projekat.controller;

import com.it355.projekat.entity.Company;
import com.it355.projekat.entity.Watch;
import com.it355.projekat.service.CompanyService;
import com.it355.projekat.service.WatchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = {WatchController.class})
public class WatchControllerTest {
    @MockBean
    private WatchService watchService;
    @MockBean
    private CompanyService companyService;
    @MockBean
    private Model model;
    @MockBean
    private BindingResult bindingResult;

    @Autowired
    private WatchController watchController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAddWatchPage() {
        Company company1 = new Company(1, "John", "Doe", 30, "Canada");
        Company company2 = new Company(2, "Mike", "Smith", 40, "USA");
        List<Company> companies = Arrays.asList(company1, company2);

        when(companyService.findAll()).thenReturn(companies);

        String result = watchController.getAddWatchPage(model);

        assertEquals("watch/save-watch", result);
    }

    @Test
    public void testGetUpdateWatchPage_WithExistingWatch() {
        int watchId = 1;
        Watch watch = new Watch();
        watch.setId(watchId);

        Company company = new Company();
        List<Company> companies = List.of(company);

        when(watchService.findById(watchId)).thenReturn(watch);
        when(companyService.findAll()).thenReturn(companies);

        String result = watchController.getUpdateWatchPage(watchId, model);

        assertEquals("watch/update-watch", result);
    }

    @Test
    public void testGetUpdateWatchPage_WithoutExistingWatch() {
        Company company = new Company();
        List<Company> companies = List.of(company);

        when(companyService.findAll()).thenReturn(companies);

        String result = watchController.getUpdateWatchPage(null, model);

        assertEquals("watch/update-watch", result);
    }

    @Test
    public void testGetAvailableWatches() {
        List<Watch> watches = Arrays.asList(new Watch(), new Watch());
        when(watchService.findAll()).thenReturn(watches);

        String result = watchController.getAvailableWatches(model);

        assertEquals("watch/watches", result);
        verify(model, times(1)).addAttribute("watches", watches);
    }

    @Test
    public void testSaveWatch_WithValidData() {
        int companyId = 1;
        Watch watch = new Watch();
        Company company = new Company();
        watch.setCompany(company);

        String result = watchController.saveWatch(watch, companyId, bindingResult);

        assertEquals("redirect:/watches", result);
        verify(watchService, times(1)).save(watch);
        verify(companyService, times(1)).findById(companyId);
    }

    @Test
    public void testSaveWatch_WithInvalidData() {
        int directorId = 1;
        Watch watch = new Watch();
        when(bindingResult.hasErrors()).thenReturn(true);

        assertThrows(RuntimeException.class, () -> watchController.saveWatch(watch, directorId, bindingResult));
        verify(watchService, never()).save(watch);
        verify(companyService, never()).findById(directorId);
    }

    @Test
    public void testDeleteWatch() {
        int watchId = 1;

        String result = watchController.deleteWatch(watchId);

        assertEquals("redirect:/watches", result);
        verify(watchService, times(1)).delete(watchId);
    }

    @Test
    public void testUpdateWatch_WithValidData() {
        int watchId = 1;
        int companyId = 2;
        Watch watch = new Watch();
        Company company = new Company();
        watch.setCompany(company);

        String result = watchController.updateWatch(watch, watchId, companyId, bindingResult);

        assertEquals("redirect:/watches", result);
        verify(watchService, times(1)).updateWatch(watch, watchId, companyId);
    }

    @Test
    public void testUpdateWatch_WithInvalidData() {
        int watchId = 1;
        int companyId = 2;
        Watch watch = new Watch();
        when(bindingResult.hasErrors()).thenReturn(true);

        assertThrows(RuntimeException.class, () -> watchController.updateWatch(watch, watchId, companyId, bindingResult));
        verify(watchService, never()).updateWatch(watch, watchId, companyId);
    }
}
