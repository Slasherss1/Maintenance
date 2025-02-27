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
package eu.kennytv.maintenance.sponge.util;

import eu.kennytv.maintenance.core.util.SenderInfo;
import org.spongepowered.api.entity.living.player.User;

import java.util.UUID;

public final class SpongeOfflinePlayerInfo implements SenderInfo {
    private final User user;

    public SpongeOfflinePlayerInfo(final User user) {
        this.user = user;
    }

    @Override
    public UUID getUuid() {
        return user.getUniqueId();
    }

    @Override
    public String getName() {
        return user.getName();
    }

    @Override
    public boolean hasPermission(final String permission) {
        return user.hasPermission(permission);
    }

    @Override
    public void sendMessage(final String message) {
    }

    @Override
    public boolean isPlayer() {
        return true;
    }
}
