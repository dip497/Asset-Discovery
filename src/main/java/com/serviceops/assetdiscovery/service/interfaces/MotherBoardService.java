package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.MotherBoardRest;

import java.util.List;

public interface MotherBoardService {
    void save(long refId);

    List<MotherBoardRest> findByRefId(long refId);

    MotherBoardRest updateByRefId(long refId, MotherBoardRest motherBoardRest);

    boolean deleteByRefId(long refId);

}
