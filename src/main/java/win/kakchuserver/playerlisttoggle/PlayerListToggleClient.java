package win.kakchuserver.playerlisttoggle;

import net.fabricmc.api.ClientModInitializer;

public final class PlayerListToggleClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PlayerListToggleConfig.load();
    }
}
