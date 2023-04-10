package com.serviceops.assetdiscovery.utils.mapper;

import com.serviceops.assetdiscovery.entity.Keyboard;
import com.serviceops.assetdiscovery.rest.KeyboardRest;
import com.serviceops.assetdiscovery.utils.mapper.base.AssetBaseOps;

public class KeyboardOps extends AssetBaseOps<Keyboard, KeyboardRest> {
    private static  Keyboard keyboard = new Keyboard();
    private static  KeyboardRest keyboardRest = new KeyboardRest();


    public KeyboardOps() {
        super(keyboard, keyboardRest);
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
