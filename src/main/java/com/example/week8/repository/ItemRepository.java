package com.example.week8.repository;

import com.example.week8.dto.item.find.ItemListFindJoinAdWhereItemNoAndItemNameResDto;
import com.example.week8.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    List<Item> findByItemNoContainingAndItemNameContaining(String itemNo,String itemName);

    @Query(value = "select i.item_id as 'key', i.adult_yn as adultYn, i.item_act_yn as itemActYn, i.item_no as itemNo,i.item_name as itemName, i.item_org_cost as itemOrgCost, a.ad_use_config_yn as adUseConfigYn, a.ad_act_yn as adActYn, a.ad_id as adId \n" +
            "    from item i \n" +
            "    inner join ad a on a.item_id=i.item_id and a.adv_id = :adv and a.agroup_id = :agroupId \n and a.ad_act_yn = 1" +
            "    where i.item_no like %:itemNo% and i.item_name like %:itemName%", nativeQuery = true)
    List<ItemListFindJoinAdWhereItemNoAndItemNameResDto> findItemListJoinAdWhereItemNameAndItemNo(@Param(value = "adv") String advId, @Param(value = "agroupId") Long agroupId, @Param(value = "itemNo") String itemNo,@Param(value = "itemName") String itemName);

}
