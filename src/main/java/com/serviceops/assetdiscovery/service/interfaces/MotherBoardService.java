package com.serviceops.assetdiscovery.service.interfaces;
import com.serviceops.assetdiscovery.rest.MotherBoardRest;

import java.util.List;

public interface MotherBoardService {
    void save(Long refId);

    List<MotherBoardRest> findByRefId(Long refId);

    void deleteByRefId(Long refId);

    void update(Long refId, MotherBoardRest motherBoardRest);

}
