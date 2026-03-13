package win.kakchuserver.playerlisttoggle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class PlayerListToggleConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("playerlist-toggle.json");

    private static Config data = new Config();

    private PlayerListToggleConfig() {
    }

    public static void load() {
        if (!Files.exists(FILE)) {
            save();
            return;
        }

        try {
            String json = Files.readString(FILE, StandardCharsets.UTF_8);
            Config loaded = GSON.fromJson(json, Config.class);
            data = loaded == null ? new Config() : loaded;
        } catch (IOException | JsonParseException ignored) {
            data = new Config();
            save();
        }
    }

    public static boolean enabled() {
        return data.enabled;
    }

    public static void save() {
        try {
            Files.createDirectories(FILE.getParent());
            Files.writeString(FILE, GSON.toJson(data), StandardCharsets.UTF_8);
        } catch (IOException ignored) {
        }
    }

    private static final class Config {
        boolean enabled = true;
    }
}
