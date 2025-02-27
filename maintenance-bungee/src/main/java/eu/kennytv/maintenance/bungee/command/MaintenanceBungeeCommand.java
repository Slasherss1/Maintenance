/*
 * This file is part of Maintenance - https://github.com/kennytv/Maintenance
 * Copyright (C) 2018-2021 kennytv (https://github.com/kennytv)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package eu.kennytv.maintenance.bungee.command;

import eu.kennytv.maintenance.bungee.MaintenanceBungeePlugin;
import eu.kennytv.maintenance.bungee.util.BungeeSenderInfo;
import eu.kennytv.maintenance.core.proxy.SettingsProxy;
import eu.kennytv.maintenance.core.proxy.command.MaintenanceProxyCommand;
import eu.kennytv.maintenance.core.util.SenderInfo;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class MaintenanceBungeeCommand extends MaintenanceProxyCommand {
    private final MaintenanceBungeePlugin plugin;

    public MaintenanceBungeeCommand(final MaintenanceBungeePlugin plugin, final SettingsProxy settings) {
        super(plugin, settings);
        this.plugin = plugin;
        registerCommands();
    }

    @Override
    protected void sendUpdateMessage(final SenderInfo sender) {
        final TextComponent tc = new TextComponent("§6× §8[§aUpdate§8]");
        tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/maintenance forceupdate"));
        tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§aClick here to update the plugin")));
        tc.addExtra(" §8< §7Or use the command §c/maintenance forceupdate");
        ((BungeeSenderInfo) sender).sendMessage(tc);
    }

    @Override
    public List<String> getServersCompletion(final String s) {
        final List<String> list = new ArrayList<>();
        for (final Map.Entry<String, ServerInfo> entry : plugin.getProxy().getServers().entrySet()) {
            final String serverName = entry.getValue().getName();
            if (entry.getKey().toLowerCase().startsWith(s) && !plugin.getSettingsProxy().getMaintenanceServers().contains(serverName)) {
                list.add(serverName);
            }
        }
        return list;
    }

    @Override
    public void sendDumpMessage(final SenderInfo sender, final String url) {
        final BungeeSenderInfo bungeeSender = ((BungeeSenderInfo) sender);
        final TextComponent clickText = new TextComponent(plugin.getPrefix() + "§7Click here to copy the link");
        clickText.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, url));
        clickText.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText("§aClick here to copy the link")));
        bungeeSender.sendMessage(clickText);
    }

    @Override
    public List<String> getPlayersCompletion() {
        final List<String> list = new ArrayList<>();
        for (final ProxiedPlayer p : plugin.getProxy().getPlayers()) {
            list.add(p.getName());
        }
        return list;
    }
}
