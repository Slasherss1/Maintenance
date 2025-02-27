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
package eu.kennytv.maintenance.core.command.subcommand;

import eu.kennytv.maintenance.core.MaintenancePlugin;
import eu.kennytv.maintenance.core.command.CommandInfo;
import eu.kennytv.maintenance.core.util.SenderInfo;

import java.util.Collections;
import java.util.List;

public final class MotdCommand extends CommandInfo {

    public MotdCommand(final MaintenancePlugin plugin) {
        super(plugin, "motd");
    }

    @Override
    public void execute(final SenderInfo sender, final String[] args) {
        if (args.length == 1) {
            sendList(sender, getSettings().getPingMessages());
        } else if (args.length == 2 && args[1].equalsIgnoreCase("timer")) {
            if (!getSettings().hasTimerSpecificPingMessages()) {
                sender.sendMessage(getMessage("timerMotdDisabled"));
                return;
            }

            sendList(sender, getSettings().getTimerSpecificPingMessages());
        } else {
            sender.sendMessage(getHelpMessage());
        }
    }

    private void sendList(final SenderInfo sender, final List<String> list) {
        if (list == null || list.isEmpty()) {
            sender.sendMessage(getMessage("motdListEmpty"));
            return;
        }

        sender.sendMessage(getMessage("motdList"));
        for (int i = 0; i < list.size(); i++) {
            sender.sendMessage("§b" + (i + 1) + "§8§m---------");
            for (final String motd : getSettings().getColoredString(list.get(i)).split("%NEWLINE%")) {
                sender.sendMessage(motd);
            }
        }
        sender.sendMessage("§8§m----------");
    }

    @Override
    public List<String> getTabCompletion(final SenderInfo sender, final String[] args) {
        if (args.length != 2 || !getSettings().hasTimerSpecificPingMessages()) return Collections.emptyList();
        return Collections.singletonList("timer");
    }
}
