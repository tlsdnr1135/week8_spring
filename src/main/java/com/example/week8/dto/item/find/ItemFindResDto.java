package com.example.week8.dto.item.find;

import com.example.week8.entity.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ItemFindResDto {

    private List<Item> items;

    @Builder
    public ItemFindResDto(List<Item> items) {
        this.items = items;
    }
}
