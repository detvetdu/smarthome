/**
 * Copyright (c) 2014-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.binding.tradfri.internal.model;

import static org.eclipse.smarthome.binding.tradfri.TradfriBindingConstants.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

/**
 * The {@link TradfriDeviceData} class is a Java wrapper for the raw JSON data about the device state.
 *
 * @author Kai Kreuzer - Initial contribution
 * @author Christoph Weitkamp - Restructuring and refactoring of the binding
 */
public abstract class TradfriDeviceData {

    private final Logger logger = LoggerFactory.getLogger(TradfriDeviceData.class);

    protected JsonObject root;
    protected JsonArray array;
    protected JsonObject attributes;
    protected JsonObject generalInfo;

    public TradfriDeviceData(String attributesNodeName) {
        root = new JsonObject();
        array = new JsonArray();
        attributes = new JsonObject();
        array.add(attributes);
        root.add(attributesNodeName, array);
        generalInfo = new JsonObject();
        root.add(DEVICE, generalInfo);
    }

    public TradfriDeviceData(String attributesNodeName, JsonElement json) {
        try {
            root = json.getAsJsonObject();
            array = root.getAsJsonArray(attributesNodeName);
            attributes = array.get(0).getAsJsonObject();
            generalInfo = root.getAsJsonObject(DEVICE);
        } catch (JsonSyntaxException e) {
            logger.error("JSON error: {}", e.getMessage(), e);
        }
    }

    public Integer getDeviceId() {
        return root.get(INSTANCE_ID).getAsInt();
    }

    public boolean getReachabilityStatus() {
        if (root.get(REACHABILITY_STATE) != null) {
            return root.get(REACHABILITY_STATE).getAsInt() == 1;
        } else {
            return false;
        }
    }

    public String getFirmwareVersion() {
        if (generalInfo.get(DEVICE_FIRMWARE) != null) {
            return generalInfo.get(DEVICE_FIRMWARE).getAsString();
        } else {
            return null;
        }
    }

    public String getModelId() {
        if (generalInfo.get(DEVICE_MODEL) != null) {
            return generalInfo.get(DEVICE_MODEL).getAsString();
        } else {
            return null;
        }
    }

    public String getVendor() {
        if (generalInfo.get(DEVICE_VENDOR) != null) {
            return generalInfo.get(DEVICE_VENDOR).getAsString();
        } else {
            return null;
        }
    }
}
