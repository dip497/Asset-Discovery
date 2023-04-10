package com.serviceops.assetdiscovery.service.interfaces;
import com.serviceops.assetdiscovery.rest.MotherBoardRest;

public interface MotherBoardService {
    void save(Long id);

    MotherBoardRest getMotherBoard(Long id);
}
