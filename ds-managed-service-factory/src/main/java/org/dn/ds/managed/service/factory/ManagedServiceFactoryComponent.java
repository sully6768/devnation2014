/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.dn.ds.managed.service.factory;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component()
public class ManagedServiceFactoryComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManagedServiceFactoryComponent.class);

    private List<IManagedServiceFactory> services = new ArrayList<IManagedServiceFactory>();

    @Activate
    public void start() {
        LOGGER.info("Starting the service");
    }

    @Deactivate
    public void stop() {
        LOGGER.info("Stopping the service");
    }

    private void printCurrentConfigList() {
        LOGGER.info("Printing the current configuration list: ");
        for (IManagedServiceFactory iManagedServiceFactory : services) {
            LOGGER.info("  Name: " + iManagedServiceFactory.getName());
        }
    }

    private void printUpdatedConfigList() {
        LOGGER.info("Printing the updated configuration list: ");
        for (IManagedServiceFactory iManagedServiceFactory : services) {
            LOGGER.info("  Name: " + iManagedServiceFactory.getName());
        }
    }

    @Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
    public synchronized void setManagedService(IManagedServiceFactory iManagedServiceFactory) {
        if (!services.contains(iManagedServiceFactory)) {
            printCurrentConfigList();
            LOGGER.info("Setting new configuration: " + iManagedServiceFactory.getName());
            services.add(iManagedServiceFactory);
            printUpdatedConfigList();
        } else {
            LOGGER.info("IManagedServiceFactory already exists: " + services.toString());
        }
    }

    public synchronized void addManagedService(IManagedServiceFactory iManagedServiceFactory) {
        if (!services.contains(iManagedServiceFactory)) {
            printCurrentConfigList();
            LOGGER.info("Adding new configuration: " + iManagedServiceFactory.getName());
            services.add(iManagedServiceFactory);
            printUpdatedConfigList();
        } else {
            LOGGER.info("IManagedServiceFactory already exists: " + services.toString());
        }
    }

    public synchronized void unsetManagedService(IManagedServiceFactory iManagedServiceFactory) {
        if (services.contains(iManagedServiceFactory)) {
            printCurrentConfigList();
            LOGGER.info("Removing configuration: " + iManagedServiceFactory.getName());
            services.remove(iManagedServiceFactory);
            printUpdatedConfigList();
        } else {
            LOGGER.info("No matching IManagedServiceFactory foung: " + services.toString());
        }
    }
}
