package com.example.week8.dto.daddet.find;

public interface ResponseDadDetListJoinAdKwdItem {

    Long getKey(); //dad_det_id
    String getItemName(); //아이템 네임
    String getKwdName(); //키워드 네임
    Long getCnrReqId(); //검수 요청청

}