package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.MotherBoardRest;

import java.util.List;

public interface MotherBoardService {
    void save(long refId);

    List<MotherBoardRest> findByRefId(long refId);

    boolean deleteByRefId(long refId);

    MotherBoardRest update(long refId, MotherBoardRest motherBoardRest);

}
