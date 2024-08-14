package com.it355.projekat.service;

import com.it355.projekat.entity.Watch;
import com.it355.projekat.service.generic.GenericService;


public interface WatchService extends GenericService<Watch> {

    Watch updateWatch(Watch watch, Integer watchId, Integer companyId);
}
