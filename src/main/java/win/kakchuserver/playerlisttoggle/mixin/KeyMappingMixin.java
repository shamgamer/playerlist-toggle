package win.kakchuserver.playerlisttoggle.mixin;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import win.kakchuserver.playerlisttoggle.PlayerListToggleConfig;
import win.kakchuserver.playerlisttoggle.PlayerListToggleState;

@Mixin(KeyMapping.class)
public abstract class KeyMappingMixin {
    @Inject(method = "isDown", at = @At("HEAD"), cancellable = true)
    private void playerlisttoggle$isDown(CallbackInfoReturnable<Boolean> cir) {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null || client.level == null || client.screen != null) {
            return;
        }

        if (!PlayerListToggleConfig.enabled()) {
            return;
        }

        if ((Object) this == client.options.keyPlayerList) {
            cir.setReturnValue(PlayerListToggleState.toggled());
        }
    }
}
