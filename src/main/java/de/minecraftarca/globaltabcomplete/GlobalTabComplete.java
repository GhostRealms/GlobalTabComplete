package de.minecraftarca.globaltabcomplete;

// Copyright (c) 2014 MinecraftArca.de. All Rights Reserved.

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.TabCompleteEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class GlobalTabComplete extends Plugin implements Listener {

    public ProxyServer getProxy() {
        return ProxyServer.getInstance();
    }

    public void onEnable() {
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onTab(TabCompleteEvent event) {
        if (!event.getSuggestions().isEmpty()) {
            return;
        }
        String[] args = event.getCursor().split(" ");

        final String start = (args.length > 0 ? args[args.length - 1] : event.getCursor()).toLowerCase();

        for (ProxiedPlayer player : getProxy().getPlayers()) {
            if (player.getName().toLowerCase().startsWith(start)) {
                event.getSuggestions().add(player.getName());
            }
        }
    }

}
