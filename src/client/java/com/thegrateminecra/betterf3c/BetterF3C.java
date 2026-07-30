package com.thegrateminecra.betterf3c;

import com.thegrateminecra.betterf3c.config.BetterF3CConfig;
import com.mojang.logging.LogUtils;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;

public class BetterF3C implements ClientModInitializer {
    private static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitializeClient() {
        BetterF3CConfig.getInstance();
        LOGGER.info("Better F3 C initialized");
    }
}
