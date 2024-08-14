package com.it355.projekat.service.impl;

import com.it355.projekat.entity.Company;
import com.it355.projekat.repository.CompanyRepository;
import com.it355.projekat.service.CompanyService;
import com.it355.projekat.service.generic.impl.GenericServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends GenericServiceImpl<Company> implements CompanyService {

    protected CompanyServiceImpl(CompanyRepository companyRepository) {
        super(companyRepository);
    }
}
