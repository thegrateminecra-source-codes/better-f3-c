package com.thegrateminecra.betterf3c.mixin;

import com.thegrateminecra.betterf3c.config.BetterF3CConfig;
import com.thegrateminecra.betterf3c.config.CopyMode;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(KeyboardHandler.class)
public class KeyboardHandlerMixin {
    @Shadow @Final private Minecraft minecraft;
    @Shadow private long debugCrashKeyTime;
    @Shadow private long debugCrashKeyReportedTime;
    @Shadow private long debugCrashKeyReportedCount;

    @Inject(method = "handleDebugKeys", at = @At("HEAD"), cancellable = true)
    private void onDebugKey(KeyEvent keyEvent, CallbackInfoReturnable<Boolean> cir) {
        if (minecraft.player == null) return;

        // 1.21.10 has no debug copy keybindings, the keys are hardcoded
        int key = keyEvent.key();
        boolean matches = key == 67 || key == 73;

        if (!matches) return;
        if (minecraft.player.isReducedDebugInfo()) return;

        copyCoords();

        debugCrashKeyTime = -1L;
        debugCrashKeyReportedTime = -1L;
        debugCrashKeyReportedCount = 0;

        cir.setReturnValue(true);
    }

    private void copyCoords() {
        LocalPlayer player = minecraft.player;
        CopyMode mode = BetterF3CConfig.getInstance().getCopyMode();
        String coords = switch (mode) {
            case BASIC -> String.format("%.0f %.0f %.0f", player.getX(), player.getY(), player.getZ());
            case ADVANCED -> String.format("%.0f %.0f %.0f %.2f %.2f", player.getX(), player.getY(), player.getZ(), player.getYRot(), player.getXRot());
            case TP_COMMAND -> String.format("/tp @s %.0f %.0f %.0f", player.getX(), player.getY(), player.getZ());
            case ULTIMATE -> {
                String dim = player.level().dimension().location().toString();
                yield String.format("/execute in %s run tp @s %.2f %.2f %.2f %.2f %.2f", dim, player.getX(), player.getY(), player.getZ(), player.getYRot(), player.getXRot());
            }
        };
        minecraft.keyboardHandler.setClipboard(coords);
        player.displayClientMessage(Component.literal("Copied: " + coords), true);
    }
}
