/*
 * * Copyright 2018 github.com/ReflxctionDev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.reflxction.chatsaver;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.reflxction.chatsaver.proxy.IProxy;
import net.reflxction.chatsaver.saver.Saver;
import net.reflxction.chatsaver.saver.SaverImpl;
import net.reflxction.chatsaver.utils.Reference;

import java.io.File;

/**
 * ChatSaver: A mod which saves chat when switching words
 */
@Mod(
        modid = Reference.MOD_ID,
        name = Reference.NAME,
        version = Reference.VERSION,
        acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS
)
public class ChatSaver {

    // Config for saving data
    private static Configuration config;

    // If the mod is enabled
    private static boolean enabled;

    @SidedProxy(
            clientSide = Reference.CLIENT_PROXY,
            serverSide = Reference.SERVER_PROXY
    )
    private static IProxy proxy;

    private static Saver saver = new SaverImpl();

    /*
     * Initialize variables here
     */
    static {
        config = new Configuration(new File("config/chat-saver.cfg"));
        enabled = config.get("Enabled", "Enabled", true).getBoolean();
    }

    /**
     * Fired before the mod is initialized
     */
    @EventHandler
    public void onFMLPreInitialization(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    /**
     * Register events and client commands here
     */
    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    /**
     * Called after the mod is initialized
     */
    @EventHandler
    public void onFMLPostInitialization(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    /**
     * Register server commands here
     */
    @EventHandler
    public void serverStart(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }

    /**
     * @return If the mod is enabled
     */
    public static boolean isEnabled() {
        return enabled;
    }

    public static void setEnabled(boolean enabled) {
        ChatSaver.enabled = enabled;
    }

    public static Configuration getConfig() {
        return config;
    }

    public static Saver getSaver() {
        return saver;
    }

}
