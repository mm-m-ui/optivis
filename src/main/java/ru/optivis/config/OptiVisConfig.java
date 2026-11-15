package ru.optivis.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class OptiVisConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("optivis.json");

    public boolean disableAllParticles = false;
    public boolean disableExplosionParticles = true;
    public boolean disableSmokeParticles = true;
    public boolean disablePortalParticles = true;
    public boolean disablePotionParticles = true;
    public boolean disableCriticalParticles = true;
    public boolean disableWaterParticles = false;
    public boolean disableFireParticles = false;

    public boolean disableGrass = false;
    public boolean disableFlowers = false;
    public boolean disableVines = false;
    public boolean disableFerns = false;
    public boolean disableDeadBush = false;

    public boolean freezeTextureAnimations = false;

    public double entityRenderDistance = 64.0;

    public boolean disableSky = false;
    public boolean disableClouds = false;
    public boolean disableSun = false;
    public boolean disableMoon = false;
    public boolean disableStars = false;

    public boolean disableFirstPersonHand = false;
    public boolean disableFirstPersonModel = true;

    public static OptiVisConfig INSTANCE = new OptiVisConfig();

    public static void load() {
        if (Files.exists(CONFIG_PATH)) {
            try {
                String json = Files.readString(CONFIG_PATH);
                OptiVisConfig loaded = GSON.fromJson(json, OptiVisConfig.class);
                if (loaded != null) {
                    INSTANCE = loaded;
                }
            } catch (Exception e) {
                INSTANCE = new OptiVisConfig();
            }
        } else {
            save();
        }
    }

    public static void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            Files.writeString(CONFIG_PATH, GSON.toJson(INSTANCE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
