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
package net.reflxction.chatsaver.proxy;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.*;
import net.reflxction.chatsaver.commands.CSCommand;
import net.reflxction.chatsaver.events.PlayerSwitchWorldEvent;
import net.reflxction.chatsaver.listeners.KeybindListener;
import net.reflxction.chatsaver.listeners.PlayerSwitchWorldListener;
import net.reflxction.chatsaver.listeners.TickTracker;
import org.lwjgl.input.Keyboard;

public class ClientProxy implements IProxy {

    // Mod bindings
    private static KeyBinding[] bindings;

    /**
     * Called before the mod is fully initialized
     *
     * @param event Forge's pre-init event
     */
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new CSCommand());
    }

    /**
     * Called when the mod has been fully initialized
     *
     * @param event Forge's init event
     */
    @Override
    public void init(FMLInitializationEvent event) {
        // Register bindings
        bindings = new KeyBinding[2];
        bindings[0] = new KeyBinding("Open last text", Keyboard.KEY_U, "ChatSaver");
        bindings[1] = new KeyBinding("Clear text", Keyboard.KEY_Y, "ChatSaver");
        for (KeyBinding binding : bindings) {
            ClientRegistry.registerKeyBinding(binding);
        }

        // Registries for event posts
        MinecraftForge.EVENT_BUS.register(new PlayerSwitchWorldEvent());

        // Registries for event listeners
        MinecraftForge.EVENT_BUS.register(new TickTracker());
        MinecraftForge.EVENT_BUS.register(new PlayerSwitchWorldListener());
        MinecraftForge.EVENT_BUS.register(new KeybindListener());
    }


    /**
     * Called after the mod has been successfully initialized
     *
     * @param event Forge's post init event
     */
    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }


    /**
     * Called after {@link FMLServerAboutToStartEvent} and before {@link FMLServerStartedEvent}.
     *
     * @param event Forge's server starting event
     */
    @Override
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CSCommand());
    }

    /**
     * @return The key binding for opening text
     */
    public static KeyBinding getOpenBinding() {
        return bindings[0];
    }

    /**
     * @return The key binding for clearing text
     */
    public static KeyBinding getClearBinding() {
        return bindings[1];
    }
}
