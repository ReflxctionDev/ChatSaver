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
package net.reflxction.chatsaver.config;

import net.reflxction.chatsaver.ChatSaver;

/**
 * Class which contains all the mod settings
 */
public class Settings {

    // Whether the mod is enabled or not
    private boolean enabled;

    // Whether the mod should send notifications to the client
    private boolean sendNotifications;

    public Settings() {
        enabled = ChatSaver.getConfig().get("Enabled", "Enabled", true).getBoolean();
        sendNotifications = ChatSaver.getConfig().get("Enabled", "Notifications", true).getBoolean();
    }

    /**
     * @return Whether the mod is enabled or not
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the cached boolean
     *
     * @param enabled Flag to set
     */
    void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Whether the mod should send notifications
     *
     * @return ^
     */
    public boolean sendNotifications() {
        return sendNotifications;
    }

    /**
     * Sets if the mod should send notifications to the client or not
     *
     * @param sendNotifications Boolean to set
     */
    void setNotifications(boolean sendNotifications) {
        this.sendNotifications = sendNotifications;
    }
}
