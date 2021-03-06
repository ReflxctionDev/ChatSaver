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
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.reflxction.chatsaver.ChatSaver;
import net.reflxction.chatsaver.proxy.ClientProxy;
import net.reflxction.chatsaver.utils.MessageManager.Notification;

/**
 * Class which listens to the mod's keybinds
 */
public class KeybindListener {

    @SubscribeEvent
    public void onInputKeyInput(KeyInputEvent event) {
        if (ChatSaver.getSettings().isEnabled()) {
            if (ChatSaver.getSaver().hasContent()) {
                if (ClientProxy.getOpenBinding().isPressed()) {
                    Minecraft.getMinecraft().displayGuiScreen(new GuiChat(ChatSaver.getSaver().getText()));
                    ChatSaver.getMessageManager().sendNotification(Notification.CLEAR_CHAT);
                } else if (ClientProxy.getClearBinding().isPressed()) {
                    ChatSaver.getSaver().clear();
                    ChatSaver.getMessageManager().sendNotification(Notification.CHAT_CLEARED);
                }
            }
        }
    }
}
