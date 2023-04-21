package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.KeyboardRest;

import java.util.List;

public interface KeyboardService {
    void save(long id);

    List<KeyboardRest> findAllByRefId(long refId);

    KeyboardRest updateById(long refId, long id, KeyboardRest keyboardRest);

    boolean deleteById(long refId, long id);
}
