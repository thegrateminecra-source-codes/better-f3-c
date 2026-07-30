package com.thegrateminecra.betterf3c.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.logging.LogUtils;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;

public class BetterF3CConfig {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("betterf3c.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static BetterF3CConfig instance;

    private CopyMode copyMode = CopyMode.BASIC;

    public static BetterF3CConfig getInstance() {
        if (instance == null) {
            instance = load();
        }
        return instance;
    }

    public CopyMode getCopyMode() {
        return copyMode;
    }

    public void setCopyMode(CopyMode mode) {
        this.copyMode = mode;
        save();
    }

    private static BetterF3CConfig load() {
        if (Files.exists(CONFIG_PATH)) {
            try {
                JsonObject obj = JsonParser.parseString(Files.readString(CONFIG_PATH)).getAsJsonObject();
                BetterF3CConfig config = new BetterF3CConfig();
                if (obj.has("copyMode")) {
                    config.copyMode = CopyMode.valueOf(obj.get("copyMode").getAsString());
                }
                return config;
            } catch (Exception e) {
                LOGGER.warn("Failed to load config, using defaults", e);
            }
        }
        return new BetterF3CConfig();
    }

    public void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            JsonObject obj = new JsonObject();
            obj.addProperty("copyMode", copyMode.name());
            Files.writeString(CONFIG_PATH, GSON.toJson(obj));
        } catch (Exception e) {
            LOGGER.warn("Failed to save config", e);
        }
    }
}
