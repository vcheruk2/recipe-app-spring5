package com.ravi.recipe.service;

import com.ravi.recipe.commands.UnitOfMeasureCommand;

import java.util.Set;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 4/12/2020 */
public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
