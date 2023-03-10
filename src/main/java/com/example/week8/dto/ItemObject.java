package com.example.week8.dto;

import com.example.week8.entity.Agroup;
import com.example.week8.entity.Item;
import com.example.week8.entity.Kwd;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemObject {

    private List<Item> items;

    private List<Agroup> agroups;

    private List<Kwd> kwds;

}
