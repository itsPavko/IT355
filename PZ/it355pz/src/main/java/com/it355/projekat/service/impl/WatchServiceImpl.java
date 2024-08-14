package com.it355.projekat.service.impl;

import com.it355.projekat.entity.Company;
import com.it355.projekat.entity.Watch;
import com.it355.projekat.repository.WatchRepository;
import com.it355.projekat.service.CompanyService;
import com.it355.projekat.service.WatchService;
import com.it355.projekat.service.generic.impl.GenericServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class WatchServiceImpl extends GenericServiceImpl<Watch> implements WatchService {

    private final CompanyService companyService;

    protected WatchServiceImpl(WatchRepository watchRepository, CompanyService companyService) {
        super(watchRepository);
        this.companyService = companyService;
    }

    @Override
    public Watch updateWatch(Watch watch, Integer watchId, Integer companyId) {
        Company company = companyService.findById(companyId);
        watch.setCompany(company);
        watch.setId(watchId);
        watch.setImage(watch.getImage());
        return abstractRepository.save(watch);
    }
}
