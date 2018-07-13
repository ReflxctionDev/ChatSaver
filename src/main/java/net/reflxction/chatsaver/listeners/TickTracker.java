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

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.reflxction.chatsaver.ChatSaver;
import net.reflxction.chatsaver.utils.ReflectUtils;

/**
 * Listener which tracks ticks and saves the text to the mod saver
 */
@SuppressWarnings("ConstantConditions")
public class TickTracker {

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {

        // Check if the chat GUI is opened
        if (FMLClientHandler.instance().isGUIOpen(GuiChat.class)) {

            // Perform safety checks
            if (check(ReflectUtils.getChatTextField().get())) {

                // Update the text in the saver
                ReflectUtils.getChatTextField().ifPresent(textField -> ChatSaver.getSaver().setText(textField.getText()));
            }
        }
    }

    /**
     * Ensures safety methods when using {@link ReflectUtils#getChatTextField()}
     *
     * @param field Field to check for
     * @return True if the field is ready to be used
     */
    private boolean check(GuiTextField field) {
        return
                field != null && // Ensure safety
                        !field.getText().isEmpty(); // Ensure that it has text

    }

}
