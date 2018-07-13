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
import net.reflxction.chatsaver.config.Settings;
import net.reflxction.chatsaver.proxy.IProxy;
import net.reflxction.chatsaver.saver.Saver;
import net.reflxction.chatsaver.saver.SaverImpl;
import net.reflxction.chatsaver.utils.MessageManager;
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

    // Mod settings
    private static Settings settings;

    // Mod's message manager
    private static MessageManager messageManager = new MessageManager();

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
        settings = new Settings();
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

    public static Configuration getConfig() {
        return config;
    }

    public static Saver getSaver() {
        return saver;
    }

    public static Settings getSettings() {
        return settings;
    }

    public static MessageManager getMessageManager() {
        return messageManager;
    }

}
