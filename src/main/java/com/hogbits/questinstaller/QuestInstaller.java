package com.hogbits.questinstaller;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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

        copyIfMissing("ftbquests/quests/reward_tables/choice_compost.snbt",
                FMLPaths.CONFIGDIR.get().resolve("ftbquests/quests/reward_tables/choice_compost.snbt"));

        copyIfMissing("ftbquests/quests/reward_tables/choice_crush.snbt",
                FMLPaths.CONFIGDIR.get().resolve("ftbquests/quests/reward_tables/choice_crush.snbt"));

        copyIfMissing("ftbquests/quests/reward_tables/choice_flowers.snbt",
                FMLPaths.CONFIGDIR.get().resolve("ftbquests/quests/reward_tables/choice_flowers.snbt"));

        copyIfMissing("ftbquests/quests/reward_tables/choice_food.snbt",
                FMLPaths.CONFIGDIR.get().resolve("ftbquests/quests/reward_tables/choice_food.snbt"));

        copyIfMissing("ftbquests/quests/reward_tables/choice_hospital.snbt",
                FMLPaths.CONFIGDIR.get().resolve("ftbquests/quests/reward_tables/choice_hospital.snbt"));

        copyIfMissing("ftbquests/quests/reward_tables/choice_logs.snbt",
                FMLPaths.CONFIGDIR.get().resolve("ftbquests/quests/reward_tables/choice_logs.snbt"));

        copyIfMissing("ftbquests/quests/reward_tables/choice_sapling.snbt",
                FMLPaths.CONFIGDIR.get().resolve("ftbquests/quests/reward_tables/choice_sapling.snbt"));

        copyIfMissing("ftbquests/quests/reward_tables/choice_stone.snbt",
                FMLPaths.CONFIGDIR.get().resolve("ftbquests/quests/reward_tables/choice_stone.snbt"));

        copyIfMissing("ftbquests/quests/reward_tables/minecolonies_common.snbt",
                FMLPaths.CONFIGDIR.get().resolve("ftbquests/quests/reward_tables/minecolonies_common.snbt"));
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
