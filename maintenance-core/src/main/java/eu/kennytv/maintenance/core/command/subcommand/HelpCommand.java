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

import com.google.common.base.Preconditions;
import eu.kennytv.maintenance.core.MaintenancePlugin;
import eu.kennytv.maintenance.core.command.CommandInfo;
import eu.kennytv.maintenance.core.util.SenderInfo;

import java.util.ArrayList;
import java.util.List;

public final class HelpCommand extends CommandInfo {
    private static final int COMMANDS_PER_PAGE = 8;

    public HelpCommand(final MaintenancePlugin plugin) {
        super(plugin, null);
    }

    @Override
    public void execute(final SenderInfo sender, final String[] args) {
        if (args.length > 2) {
            sender.sendMessage(getHelpMessage());
            return;
        }

        if (args.length == 1) {
            sendUsage(sender);
            return;
        }

        if (!plugin.isNumeric(args[1])) {
            sender.sendMessage(getHelpMessage());
            return;
        }

        final int page = Integer.parseInt(args[1]);
        sendUsage(sender, page == 0 ? 1 : page);
    }

    public void sendUsage(final SenderInfo sender) {
        sendUsage(sender, 1);
    }

    public void sendUsage(final SenderInfo sender, final int page) {
        Preconditions.checkArgument(page > 0);
        final List<String> commands = new ArrayList<>();
        for (final CommandInfo cmd : plugin.getCommandManager().getCommands()) {
            if (cmd.isVisible() && cmd.hasPermission(sender)) {
                commands.add(cmd.getHelpMessage());
            }
        }
        if ((page - 1) * COMMANDS_PER_PAGE >= commands.size()) {
            sender.sendMessage(getMessage("helpPageNotFound"));
            return;
        }

        final List<String> filteredCommands = commands.subList((page - 1) * COMMANDS_PER_PAGE, Math.min(page * COMMANDS_PER_PAGE, commands.size()));

        final String header = getMessage("helpHeader").replace("%NAME%", "Maintenance" + plugin.getServerType())
                .replace("%PAGE%", Integer.toString(page)).replace("%MAX%", Integer.toString((commands.size() + getDivide(commands.size())) / COMMANDS_PER_PAGE));
        sender.sendMessage("");
        sender.sendMessage(header);
        for (final String command : filteredCommands) {
            sender.sendMessage(command);
        }

        if (page * COMMANDS_PER_PAGE < commands.size()) {
            sender.sendMessage(getMessage("helpNextPage").replace("%PAGE%", Integer.toString(page + 1)));
        } else {
            sender.sendMessage("§8× §eVersion " + plugin.getVersion() + " §7by §bkennytv");
        }

        sender.sendMessage(header);
        sender.sendMessage("");
    }

    private int getDivide(final int size) {
        final int commandSize = size % COMMANDS_PER_PAGE;
        return commandSize > 0 ? COMMANDS_PER_PAGE - commandSize : 0;
    }
}
