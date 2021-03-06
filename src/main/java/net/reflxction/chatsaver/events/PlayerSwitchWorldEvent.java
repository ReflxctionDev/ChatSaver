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
package net.reflxction.chatsaver.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Fired when the player switches worlds
 */
public class PlayerSwitchWorldEvent extends Event {

    // Player involved in the event
    private EntityPlayer player;

    // World involved in the event
    private World world;

    /**
     * Event constructor for <strong>posting</strong>
     *
     * @param player Player involved in the event
     * @param world  World involved in the event
     */
    private PlayerSwitchWorldEvent(EntityPlayer player, World world) {
        this.player = player;
        this.world = world;
    }

    /**
     * Event constructor for <strong>registering</strong>
     */
    public PlayerSwitchWorldEvent() {
    }

    public EntityPlayer getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }

    // Trigger for the event
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent e) {
        try {
            if (e.entity instanceof EntityPlayerSP) {
                EntityPlayerSP player = ((EntityPlayerSP) e.entity);
                if (player.getName().equals(Minecraft.getMinecraft().thePlayer.getName())) {
                    PlayerSwitchWorldEvent event = new PlayerSwitchWorldEvent(player, e.world);
                    MinecraftForge.EVENT_BUS.post(event);
                }
            } else if (e.entity instanceof EntityPlayerMP) {
                EntityPlayerMP player = (EntityPlayerMP) e.entity;
                if (player.getPersistentID().equals(Minecraft.getMinecraft().thePlayer.getUniqueID())) {
                    PlayerSwitchWorldEvent event = new PlayerSwitchWorldEvent(player, e.world);
                    MinecraftForge.EVENT_BUS.post(event);
                }
            }
        } catch (NullPointerException ignored) {
        }
    }
}