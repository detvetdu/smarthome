/**
 * Copyright (c) 2014-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.core.thing.profiles;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.core.types.Command;
import org.eclipse.smarthome.core.types.State;

/**
 * A {@link StateProfile} defined the communication for channels of STATE kind.
 *
 * @author Simon Kaufmann - initial contribution and API.
 *
 */
@NonNullByDefault
public interface StateProfile extends Profile {

    /**
     * Will be called if a command should be forwarded to the binding.
     *
     * @param command
     */
    void onCommandFromItem(Command command);

    /**
     * If a binding issued a command to a channel, this method will be called for each linked item.
     *
     * @param command
     */
    void onCommandFromHandler(Command command);

    /**
     * If the binding indicated a state update on a channel, then this method will be called for each linked item.
     *
     * @param state
     */
    void onStateUpdateFromHandler(State state);

}
