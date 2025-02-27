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
package eu.kennytv.maintenance.core.proxy.util;

import eu.kennytv.maintenance.api.proxy.Server;

import java.util.UUID;

public final class ProxyOfflineSenderInfo implements ProxySenderInfo {
    private final UUID uuid;
    private final String name;

    public ProxyOfflineSenderInfo(final UUID uuid, final String name) {
        this.uuid = uuid;
        this.name = name;
    }

    @Override
    public boolean canAccess(final Server server) {
        return false;
    }

    @Override
    public void disconnect(final String message) {
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean hasPermission(final String permission) {
        return false;
    }

    @Override
    public void sendMessage(final String message) {
    }

    @Override
    public boolean isPlayer() {
        return true;
    }
}
