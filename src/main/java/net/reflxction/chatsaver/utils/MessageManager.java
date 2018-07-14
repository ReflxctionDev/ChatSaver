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
package net.reflxction.chatsaver.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.reflxction.chatsaver.ChatSaver;

/**
 * Utility which organizes message sending and fix some issues in Minecraft's message sending
 */
public class MessageManager {

    private boolean sendSwitchNotification = true;

    /**
     * Sends a normal text message to the client
     *
     * @param text Text to send, chat formatted
     */
    private void sendMessage(String text) {
        if (Minecraft.getMinecraft().thePlayer != null) { // For safety :>
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Reference.PREFIX + ChatColor.format(text)));
        }
    }

    /**
     * Sends a notification to the client
     *
     * @param notification Notification to send
     */
    public void sendNotification(Notification notification) {
        if (ChatSaver.getSettings().sendNotifications()) {
            sendMessage(ChatColor.format(notification.getContent()));
        }
    }

    /**
     * For timing management
     *
     * @return {@link MessageManager#sendSwitchNotification}
     */
    public boolean allowSendNotification() {
        return sendSwitchNotification;
    }

    /**
     * Sets if the notification sending is done or not
     *
     * @param sendSwitchNotification Boolean to set
     */
    public void setAllowSendNotification(boolean sendSwitchNotification) {
        this.sendSwitchNotification = sendSwitchNotification;
    }

    public enum Notification {

        CHAT_CLEARED("Chat has been successfully cleared!"),
        WORLD_SWITCHED("Your chat has been saved since the last time it was closed when switching worlds. To retrieve it, press the key bind you've set &b(Default: &cU&b))"),
        CLEAR_CHAT("You have opened the last chat you had before switching. To clear it, press the clear key you specified in keybindings &b(Default: &cY&b)");

        private String content;

        Notification(String content) {
            this.content = content;
        }

        /**
         * Fixes the notification content's color format
         *
         * @return Fixed content format
         */
        public String getContent() {
            String[] words = content.split(" ");
            StringBuilder builder = new StringBuilder();
            for (String word : words) {
                if(!word.startsWith("&")) {
                    builder.append("&a").append(word).append(" ");
                }
            }
            return ChatColor.format(builder.toString());
        }
    }

}
