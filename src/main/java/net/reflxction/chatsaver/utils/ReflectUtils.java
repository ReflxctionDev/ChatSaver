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
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * Class which simplifies access to private/protected fields in Minecraft's classes
 * <p>
 * JavaReflectionMemberAccess - Since MCP obfuscates field names after compiling, name may change
 */
@SuppressWarnings("JavaReflectionMemberAccess")
public class ReflectUtils {

    /**
     * Returns an instance of Minecraft's text chat box which has protected access in {@link GuiChat}
     *
     * @return The protected field {@link GuiChat#inputField}
     */
    public static Optional<GuiTextField> getChatTextField() {
        try {
            // Declaration so we can use it in multiple try/catch blocks
            Field fieldText;
            try {
                // Try to use the obfuscated name for the input field
                fieldText = GuiChat.class.getDeclaredField("field_146415_a");
            } catch (NoSuchFieldException e) {
                // Obfuscated name wasn't found, so try the de-obfuscated name
                fieldText = GuiChat.class.getDeclaredField("inputField");
            }
            fieldText.setAccessible(true);
            return Optional.of(((GuiTextField) fieldText.get(Minecraft.getMinecraft().currentScreen)));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException ignored) {
            return Optional.empty();
        }
        return Optional.empty();
    }

}
