package com.thegrateminecra.betterf3c;

//? if forge {
/*import com.thegrateminecra.betterf3c.config.BetterF3CConfig;
import com.thegrateminecra.betterf3c.config.ConfigScreen;
import com.mojang.logging.LogUtils;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod("betterf3c")
public class BetterF3CForge {
    private static final Logger LOGGER = LogUtils.getLogger();

    public BetterF3CForge(FMLJavaModLoadingContext context) {
        BetterF3CConfig.getInstance();
        context.getContainer().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
            () -> new ConfigScreenHandler.ConfigScreenFactory(ConfigScreen::new));
        LOGGER.info("Better F3 C initialized");
    }
}
*///?}
