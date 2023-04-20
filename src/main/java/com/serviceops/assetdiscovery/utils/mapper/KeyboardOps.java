package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Keyboard;
import com.serviceops.assetdiscovery.rest.KeyboardRest;
import com.serviceops.assetdiscovery.utils.mapper.base.AssetBaseOps;
import com.serviceops.assetdiscovery.utils.mapper.base.SingleBaseOps;

public class KeyboardOps extends SingleBaseOps<Keyboard, KeyboardRest> {
    private final Keyboard keyboard;
    private final KeyboardRest keyboardRest;

    public KeyboardOps(Keyboard keyboard, KeyboardRest keyboardRest) {
        super(keyboard, keyboardRest);
        this.keyboard = keyboard;
        this.keyboardRest = keyboardRest;
    }

    public Keyboard restToEntity() {
        super.restToEntity(keyboardRest);
        keyboard.setRefId(keyboardRest.getRefId());
        keyboard.setName(keyboardRest.getName());
        return keyboard;
    }

    public KeyboardRest entityToRest() {
        super.entityToRest(keyboard);
        keyboardRest.setRefId(keyboard.getRefId());
        keyboardRest.setName(keyboard.getName());
        return keyboardRest;
    }
}
