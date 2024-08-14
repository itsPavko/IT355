package com.it355.projekat.service;


import com.it355.projekat.entity.Shopping;
import com.it355.projekat.service.generic.GenericService;

import java.util.List;

public interface ShoppingService extends GenericService<Shopping> {

    Shopping saveShopping(Integer userId, Integer watchId);

    List<Shopping> findAllByLoggedInMember();
}
