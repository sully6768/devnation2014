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
package org.dn.ds.managed.service.provider;

import java.util.Map;

import org.dn.ds.managed.service.IManagedService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(configurationPolicy = ConfigurationPolicy.REQUIRE)
public class ManagedServiceProvider implements IManagedService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManagedServiceProvider.class);

    private String name;

    @Activate
    public void start(final Map<String, ?> properties) {
        if (properties.containsKey("name")) {
            name = (String)properties.get("name");
        } else {
            LOGGER.info("No property found for NAME");
        }
    }

    @Deactivate
    public void stop() {
        // Nothing to do here
    }

    @Override
    public void sayHello() {
        LOGGER.info("Hello " + name);
    }

    @Override
    public void sayGoodbye() {
        LOGGER.info("Goodbye " + name);
    }

}
