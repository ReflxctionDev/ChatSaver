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
package net.reflxction.chatsaver.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.reflxction.chatsaver.ChatSaver;
import net.reflxction.chatsaver.config.ConfigManager;
import net.reflxction.chatsaver.utils.ChatColor;
import net.reflxction.chatsaver.utils.Reference;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which handles command input for "/cs" and "/chatsaver"
 */
public class CSCommand implements ICommand {

    private ConfigManager config = new ConfigManager();

    @Override
    public String getCommandName() {
        return "chatsaver";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/chatsaver <toggle / notifications>";
    }

    @Override
    public List<String> getCommandAliases() {
        List<String> aliases = new ArrayList<>();
        aliases.add("cs");
        return aliases;
    }

    @Override
    public void processCommand(ICommandSender executor, String[] args) {
        switch (args.length) {
            case 0:
                sendMessage("&cIncorrect command usage. Try " + getCommandUsage(executor));
                break;
            case 1:
                switch (args[0]) {
                    case "toggle":
                        config.setEnabled(!ChatSaver.getSettings().isEnabled());
                        sendMessage(ChatSaver.getSettings().isEnabled() ? "&aChatSaver has been enabled" : "&cChatSaver has been disabled");
                        break;
                    case "notifications":
                        config.setNotifications(!ChatSaver.getSettings().sendNotifications());
                        sendMessage(ChatSaver.getSettings().sendNotifications() ? "&aNotifications have been enabled" : "&cNotifications have been disabled");
                        break;
                }
                break;
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        List<String> tabs = new ArrayList<>();
        tabs.add("chatsaver");
        tabs.add("cs");
        return tabs;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return true;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }

    /**
     * Sends a message to the client, chat-formatted
     *
     * @param message Message to send
     */
    private void sendMessage(String message) {
        if (Minecraft.getMinecraft().thePlayer != null) { // For safety :>
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Reference.PREFIX + ChatColor.format(message)));
        }
    }

}
