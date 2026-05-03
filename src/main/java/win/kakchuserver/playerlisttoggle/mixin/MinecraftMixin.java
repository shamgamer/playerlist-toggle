package win.kakchuserver.playerlisttoggle.mixin;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import win.kakchuserver.playerlisttoggle.PlayerListToggleConfig;
import win.kakchuserver.playerlisttoggle.PlayerListToggleState;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void playerlisttoggle$tick(CallbackInfo ci) {
        Minecraft client = (Minecraft) (Object) this;
        if (client.player == null || client.level == null || client.screen != null) {
            PlayerListToggleState.playerListDown(false);
            return;
        }

        KeyMapping playerListKey = client.options.keyPlayerList;
        InputConstants.Key boundKey = ((KeyMappingAccessor) (Object) playerListKey).playerlisttoggle$getKey();
        boolean down = InputConstants.isKeyDown(client.getWindow(), boundKey.getValue());
        boolean wasDown = PlayerListToggleState.playerListDown();

        if (PlayerListToggleConfig.enabled() && down && !wasDown) {
            PlayerListToggleState.toggle();
        }

        PlayerListToggleState.playerListDown(down);
    }
}
