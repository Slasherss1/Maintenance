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
import org.jetbrains.annotations.Nullable;

public final class ServerConnectResult {
    private final boolean cancel;
    private final Server target;

    public ServerConnectResult(final boolean cancel) {
        this.cancel = cancel;
        this.target = null;
    }

    public ServerConnectResult(final Server target) {
        this.cancel = false;
        this.target = target;
    }

    public boolean isCancelled() {
        return cancel;
    }

    @Nullable
    public Server getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return "ServerConnectResult{" +
                "cancel=" + cancel +
                ", target=" + target +
                '}';
    }
}
