package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Keyboard;
import com.serviceops.assetdiscovery.rest.KeyboardRest;
import com.serviceops.assetdiscovery.utils.mapper.base.AuditBaseOps;

public class KeyboardOps extends AuditBaseOps<Keyboard, KeyboardRest> {

    @Override
    public Keyboard restToEntity(Keyboard keyboard, KeyboardRest keyboardRest) {
        super.restToEntity(keyboard, keyboardRest);
        keyboard.setRefId(keyboardRest.getRefId());
        keyboard.setName(keyboardRest.getName());
        return keyboard;
    }

    @Override
    public KeyboardRest entityToRest(Keyboard keyboard, KeyboardRest keyboardRest) {
        super.entityToRest(keyboard, keyboardRest);
        keyboardRest.setRefId(keyboard.getRefId());
        keyboardRest.setName(keyboard.getName());
        return keyboardRest;
    }
}
