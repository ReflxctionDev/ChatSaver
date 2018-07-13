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
import net.minecraft.client.gui.GuiChat;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.reflxction.chatsaver.ChatSaver;
import net.reflxction.chatsaver.proxy.ClientProxy;
import net.reflxction.chatsaver.utils.ChatColor;
import net.reflxction.chatsaver.utils.Reference;

/**
 * Class which listens to the mod's keybinds
 */
public class KeybindListener {

    @SubscribeEvent
    public void onInputKeyInput(KeyInputEvent event) {
        if (!ChatSaver.getSaver().isCleared()) {
            if (ClientProxy.getOpenBinding().isPressed()) {
                Minecraft.getMinecraft().displayGuiScreen(new GuiChat(ChatSaver.getSaver().getText()));
                sendMessage("&aYou have opened the last chat you had before switching. To clear it, press the clear key you specified in keybindings &b(Default: &cY&b)");
            } else if (ClientProxy.getClearBinding().isPressed()) {
                ChatSaver.getSaver().clear();
                sendMessage("&aChat has been cleared!");
            }
        }
    }

    /**
     * Sends a text message to the client
     *
     * @param text Text to send, chat formatted
     */
    private void sendMessage(String text) {
        if (Minecraft.getMinecraft().thePlayer != null) { // For safety :>
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Reference.PREFIX + ChatColor.format(text)));
        }
    }

}
