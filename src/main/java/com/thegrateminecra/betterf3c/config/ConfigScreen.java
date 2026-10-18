package com.thegrateminecra.betterf3c.config;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public class ConfigScreen extends Screen {
    private static final Component TITLE = Component.literal("Better F3 C Config");

    private final Screen parent;
    private BetterF3CConfig config;

    public ConfigScreen(Screen parent) {
        super(TITLE);
        this.parent = parent;
    }

    @Override
    protected void init() {
        config = BetterF3CConfig.getInstance();

        Button modeButton = Button.builder(
                Component.literal(getModeLabel(config.getCopyMode())),
                btn -> {
                    CopyMode[] modes = CopyMode.values();
                    int next = (config.getCopyMode().ordinal() + 1) % modes.length;
                    config.setCopyMode(modes[next]);
                    btn.setMessage(Component.literal(getModeLabel(config.getCopyMode())));
                }
        ).bounds(width / 2 - 100, height / 2 - 30, 200, 20).build();

        Button doneButton = Button.builder(
                CommonComponents.GUI_DONE,
                btn -> {
                    //? if >=1.21.11 {
                    minecraft.setScreenAndShow(parent);
                    //?} else {
                    /*minecraft.setScreen(parent);
                    *///?}
                }
        ).bounds(width / 2 - 100, height / 2 + 20, 200, 20).build();

        addRenderableWidget(modeButton);
        addRenderableWidget(doneButton);
    }

    @Override
    public void onClose() {
        //? if >=1.21.11 {
        minecraft.setScreenAndShow(parent);
        //?} else {
        /*minecraft.setScreen(parent);
        *///?}
    }

    private static String getModeLabel(CopyMode mode) {
        return switch (mode) {
            case BASIC -> "Mode: Basic";
            case ADVANCED -> "Mode: Advanced";
            case TP_COMMAND -> "Mode: TP Command";
            case ULTIMATE -> "Mode: Ultimate";
        };
    }
}
