package com.serviceops.assetdiscovery.service.interfaces;

import com.serviceops.assetdiscovery.rest.KeyboardRest;

import java.util.List;

public interface KeyboardService {
    void save(Long id);

    KeyboardRest findByRefId(Long refId);

    List<KeyboardRest> findAllByRefId(Long refId);

    boolean deleteByRefId(Long refId, Long id);

    KeyboardRest update(Long refId, Long id, KeyboardRest keyboardRest);
}
