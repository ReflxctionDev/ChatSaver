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
package net.reflxction.chatsaver.listeners;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.reflxction.chatsaver.ChatSaver;
import net.reflxction.chatsaver.events.PlayerSwitchWorldEvent;
import net.reflxction.chatsaver.utils.ChatColor;
import net.reflxction.chatsaver.utils.Reference;

/**
 * Listener for {@link net.reflxction.chatsaver.events.PlayerSwitchWorldEvent}
 */
@SuppressWarnings("SameParameterValue")
public class PlayerSwitchWorldListener {

    @SubscribeEvent
    public void onPlayerSwitchWorld(PlayerSwitchWorldEvent event) {
        if(!ChatSaver.getSaver().isCleared()) {
            sendMessage("&aYour chat has been saved since the last time it was closed when switching. To get it back, click on the key-bind you've set &b(Default: &cU&b).");
        }
    }

    /**
     * Sends a text message to the client
     *
     * @param text Text to send, chat formatted
     */
    private void sendMessage(String text) {
        if(Minecraft.getMinecraft().thePlayer != null) { // For safety :>
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Reference.PREFIX + ChatColor.format(text)));
        }
    }

}
