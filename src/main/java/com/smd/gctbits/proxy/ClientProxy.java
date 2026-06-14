package com.smd.gctbits.proxy;

import com.smd.gctbits.module.ModuleSide;

public class ClientProxy extends CommonProxy {

    public ClientProxy() {
        this.moduleManager.setCurrentSide(ModuleSide.CLIENT);
    }
}
