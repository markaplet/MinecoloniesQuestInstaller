package com.hogbits.questinstaller;

import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Mod("questinstaller")
public class QuestInstaller {

    public QuestInstaller() {
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // Copy assets before kubejs scans directories.
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onConstruct);
    }

    private void onConstruct(final FMLConstructModEvent event) {
        // FTB QUESTS FILES
        copyIfMissing("ftbquests/quests/chapters/minecolonies.snbt",
                FMLPaths.CONFIGDIR.get().resolve("ftbquests/quests/chapters/minecolonies.snbt"));

        copyIfMissing("ftbquests/quests/reward_tables/choice_flowers.snbt",
                FMLPaths.CONFIGDIR.get().resolve("ftbquests/quests/reward_tables/choice_flowers.snbt"));

        copyIfMissing("ftbquests/quests/reward_tables/choice_hospital.snbt",
                FMLPaths.CONFIGDIR.get().resolve("ftbquests/quests/reward_tables/choice_hospital.snbt"));

        copyIfMissing("ftbquests/quests/reward_tables/minecolonies_common.snbt",
                FMLPaths.CONFIGDIR.get().resolve("ftbquests/quests/reward_tables/minecolonies_common.snbt"));

        // KUBE JS FILES
        copyIfMissing("kubejs/assets/minecolonies/textures/quests/hc_build_tool.png",
                FMLPaths.GAMEDIR.get().resolve("kubejs/assets/minecolonies/textures/quests/hc_build_tool.png"));

        copyIfMissing("kubejs/assets/minecolonies/textures/quests/hc_defense.png",
                FMLPaths.GAMEDIR.get().resolve("kubejs/assets/minecolonies/textures/quests/hc_defense.png"));

        copyIfMissing("kubejs/assets/minecolonies/textures/quests/hc_feed_the_machine.png",
                FMLPaths.GAMEDIR.get().resolve("kubejs/assets/minecolonies/textures/quests/hc_feed_the_machine.png"));

        copyIfMissing("kubejs/assets/minecolonies/textures/quests/hc_getting_started.png",
                FMLPaths.GAMEDIR.get().resolve("kubejs/assets/minecolonies/textures/quests/hc_getting_started.png"));

        copyIfMissing("kubejs/assets/minecolonies/textures/quests/hc_logistics.png",
                FMLPaths.GAMEDIR.get().resolve("kubejs/assets/minecolonies/textures/quests/hc_logistics.png"));

        copyIfMissing("kubejs/assets/minecolonies/textures/quests/hc_production.png",
                FMLPaths.GAMEDIR.get().resolve("kubejs/assets/minecolonies/textures/quests/hc_production.png"));

        copyIfMissing("kubejs/assets/minecolonies/textures/quests/hc_the_builder.png",
                FMLPaths.GAMEDIR.get().resolve("kubejs/assets/minecolonies/textures/quests/hc_the_builder.png"));

        copyIfMissing("kubejs/assets/minecolonies/textures/quests/hc_university_research.png",
                FMLPaths.GAMEDIR.get().resolve("kubejs/assets/minecolonies/textures/quests/hc_university_research.png"));

        copyIfMissing("kubejs/assets/minecolonies/textures/quests/hc_utility.png",
                FMLPaths.GAMEDIR.get().resolve("kubejs/assets/minecolonies/textures/quests/hc_utility.png"));

        copyIfMissing("kubejs/assets/minecolonies/textures/quests/minecolonies_logo_medium.png",
                FMLPaths.GAMEDIR.get().resolve("kubejs/assets/minecolonies/textures/quests/minecolonies_logo_medium.png"));
    }

    private void copyIfMissing(String resourcePath, Path targetPath) {
        try {
            if (!Files.exists(targetPath)) {
                Files.createDirectories(targetPath.getParent());
                try (InputStream in = getClass().getClassLoader()
                        .getResourceAsStream("data/questinstaller/defaults/" + resourcePath)) {
                    if (in != null) {
                        Files.copy(in, targetPath);
                        System.out.println("[QuestInstaller] Copied: " + targetPath);
                    } else {
                        System.err.println("[QuestInstaller] Missing resource in JAR: " + resourcePath);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
