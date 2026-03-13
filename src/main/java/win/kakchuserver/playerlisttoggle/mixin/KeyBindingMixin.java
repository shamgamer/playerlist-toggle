package win.kakchuserver.playerlisttoggle.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import win.kakchuserver.playerlisttoggle.PlayerListToggleConfig;
import win.kakchuserver.playerlisttoggle.PlayerListToggleState;

@Mixin(KeyBinding.class)
public abstract class KeyBindingMixin {
    @Inject(method = "setKeyPressed", at = @At("HEAD"))
    private static void playerlisttoggle$setKeyPressed(InputUtil.Key key, boolean pressed, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null || client.currentScreen != null) {
            return;
        }

        KeyBinding playerListKey = client.options.playerListKey;
        InputUtil.Key boundKey = ((KeyBindingAccessor) (Object) playerListKey).playerlisttoggle$getBoundKey();

        if (boundKey.equals(key)) {
            PlayerListToggleState.playerListDown(pressed);

            if (PlayerListToggleConfig.enabled() && pressed) {
                PlayerListToggleState.toggle();
            }
        }
    }

    @Inject(method = "isPressed", at = @At("HEAD"), cancellable = true)
    private void playerlisttoggle$isPressed(CallbackInfoReturnable<Boolean> cir) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null || client.currentScreen != null) {
            return;
        }

        if (!PlayerListToggleConfig.enabled()) {
            return;
        }

        KeyBinding self = (KeyBinding) (Object) this;
        if (self == client.options.playerListKey) {
            cir.setReturnValue(PlayerListToggleState.toggled());
        }
    }
}
