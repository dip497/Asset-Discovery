package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Keyboard;
import com.serviceops.assetdiscovery.rest.KeyboardRest;

public class KeyboardOps extends AssetBaseOps<Keyboard, KeyboardRest> {
    private Keyboard keyboard;
    private KeyboardRest keyboardRest;


    public KeyboardOps(Keyboard keyboard, KeyboardRest keyboardRest) {
        super(keyboard, keyboardRest);
        this.keyboard = keyboard;
        this.keyboardRest = keyboardRest;
    }

    @Override
    public Keyboard restToEntity(KeyboardRest keyboardRest) {
        super.restToEntity(keyboardRest);
        keyboard.setName(keyboardRest.getName());
        keyboard.setDescription(keyboardRest.getDescription());
        keyboard.setInstalledDate(keyboardRest.getInstalledDate());
        keyboard.setPnpDeviceId(keyboardRest.getPnpDeviceId());
        return keyboard;
    }

    @Override
    public KeyboardRest entityToRest(Keyboard keyboard) {
        super.entityToRest(keyboard);
        keyboardRest.setName(keyboard.getName());
        keyboardRest.setDescription(keyboard.getDescription());
        keyboardRest.setInstalledDate(keyboard.getInstalledDate());
        keyboardRest.setPnpDeviceId(keyboard.getPnpDeviceId());
        return keyboardRest;
    }
}
