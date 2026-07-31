package com.thegrateminecra.betterf3c.config;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public class ConfigScreen extends Screen {
    private static final Component TITLE = Component.literal("Better F3 C Config");
    private static final Component DESC_BASIC = Component.literal("123 64 456");
    private static final Component DESC_ADVANCED = Component.literal("123 64 456 -90.00 0.00");
    private static final Component DESC_TP = Component.literal("/tp @s 123 64 456");
    private static final Component DESC_ULTIMATE = Component.literal("/execute in minecraft:overworld run tp @s 123.00 64.00 456.00 -90.00 0.00");

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
                btn -> minecraft.setScreenAndShow(parent)
        ).bounds(width / 2 - 100, height / 2 + 20, 200, 20).build();

        addRenderableWidget(modeButton);
        addRenderableWidget(doneButton);
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);

        graphics.centeredText(getFont(), TITLE.getString(), width / 2, height / 2 - 60, 0xFFFFFF);

        Component desc = switch (config.getCopyMode()) {
            case BASIC -> DESC_BASIC;
            case ADVANCED -> DESC_ADVANCED;
            case TP_COMMAND -> DESC_TP;
            case ULTIMATE -> DESC_ULTIMATE;
        };
        graphics.centeredText(getFont(), desc.getString(), width / 2, height / 2 + 5, 0x808080);
    }

    @Override
    public void onClose() {
        minecraft.setScreenAndShow(parent);
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
