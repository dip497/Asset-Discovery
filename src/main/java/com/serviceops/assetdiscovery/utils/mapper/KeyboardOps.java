package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Keyboard;
import com.serviceops.assetdiscovery.rest.KeyboardRest;
import com.serviceops.assetdiscovery.utils.mapper.base.AssetBaseOps;

public class KeyboardOps extends AssetBaseOps<Keyboard, KeyboardRest> {
    private final Keyboard keyboard;
    private final KeyboardRest keyboardRest;
    public KeyboardOps(Keyboard keyboard, KeyboardRest keyboardRest) {
        super(keyboard, keyboardRest);
        this.keyboard = keyboard;
        this.keyboardRest = keyboardRest;
    }

    public Keyboard restToEntity() {
        super.restToEntity(keyboardRest);
        keyboard.setName(keyboardRest.getName());
        return keyboard;
    }

    public KeyboardRest entityToRest() {
        super.entityToRest(keyboard);
        keyboardRest.setName(keyboard.getName());
        return keyboardRest;
    }
}
