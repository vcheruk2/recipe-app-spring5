package com.ravi.recipe.service;

import com.ravi.recipe.commands.UnitOfMeasureCommand;
import com.ravi.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.ravi.recipe.domain.UnitOfMeasure;
import com.ravi.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/* Created by: Venkata Ravichandra Cherukuri
   Created on: 4/12/2020 */
@Service
@Slf4j
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {
        Set<UnitOfMeasureCommand> unitOfMeasureCommand = new HashSet<>();
        Set<UnitOfMeasure> unitOfMeasure = new HashSet<>();
        unitOfMeasureRepository.findAll().iterator().forEachRemaining(unitOfMeasure::add);

        for(UnitOfMeasure i : unitOfMeasure)
            unitOfMeasureCommand.add(unitOfMeasureToUnitOfMeasureCommand.convert(i));

        log.debug("Unit of measure command size = " + unitOfMeasureCommand.size());

        return unitOfMeasureCommand;
    }
}
