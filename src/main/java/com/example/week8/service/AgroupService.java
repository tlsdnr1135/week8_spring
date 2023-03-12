package com.example.week8.service;

import com.example.week8.dto.ItemObject;
import com.example.week8.entity.Agroup;
import com.example.week8.repository.AgroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgroupService {


    private final AgroupRepository agroupRepository;
    public Agroup saveAgroup(Agroup agroup) {
        agroup.setAgroupActYn(1); //기본 값.
        agroup.setAgroupUseActYn(1); //기본 값.
        agroupRepository.save(agroup);
        return agroup;
    }

    public ItemObject findAgroup() {
        ItemObject itemObject = new ItemObject();
        itemObject.setAgroups(agroupRepository.findAll());
        return itemObject;
    }
}
