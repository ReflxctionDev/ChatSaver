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

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.reflxction.chatsaver.ChatSaver;
import net.reflxction.chatsaver.events.PlayerSwitchWorldEvent;
import net.reflxction.chatsaver.utils.MessageManager.Notification;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Listener for {@link net.reflxction.chatsaver.events.PlayerSwitchWorldEvent}
 */
public class PlayerSwitchWorldListener {

    @SubscribeEvent
    public void onPlayerSwitchWorld(PlayerSwitchWorldEvent event) {
        if (ChatSaver.getSettings().isEnabled()) {
            if (ChatSaver.getSaver().hasContent()) {
                if (ChatSaver.getMessageManager().allowSendNotification()) {
                    ChatSaver.getMessageManager().sendNotification(Notification.WORLD_SWITCHED);
                    ChatSaver.getMessageManager().setAllowSendNotification(false);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            ChatSaver.getMessageManager().setAllowSendNotification(true);
                        }
                    }, 6000);
                }
            }
        }
    }
}
