package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.KeyboardRest;

public interface KeyboardService {
    void save(Long id);

    KeyboardRest findByRefId(Long refId);

    void deleteByRefId(Long refId);

    void update(KeyboardRest keyboardRest);
}
