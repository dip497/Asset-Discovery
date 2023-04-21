package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.MotherBoardRest;

import java.util.List;

public interface MotherBoardService {
    void save(Long refId);

    List<MotherBoardRest> findByRefId(Long refId);

    boolean deleteByRefId(Long refId);

    MotherBoardRest update(Long refId, MotherBoardRest motherBoardRest);

}
