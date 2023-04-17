package com.serviceops.assetdiscovery.service.interfaces;
import com.serviceops.assetdiscovery.rest.MotherBoardRest;

public interface MotherBoardService {
    void save(Long id);

    MotherBoardRest findByRefId(Long refId);

    void deleteByRefId(Long refId);

    void update(Long refId, MotherBoardRest motherBoardRest);

}
