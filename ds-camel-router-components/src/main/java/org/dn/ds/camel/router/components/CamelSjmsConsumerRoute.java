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
package org.dn.ds.camel.router.components;

import javax.jms.ConnectionFactory;

import org.apache.camel.RuntimeCamelException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.sjms.SjmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 */
@Component(name="CamelSjmsConsumerRoute")
public class CamelSjmsConsumerRoute  {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CamelSjmsConsumerRoute.class);
    
    private DefaultCamelContext context;
    private ConnectionFactory connectionFactory;

    @Activate
    public void activate() throws RuntimeException {
        LOGGER.info("Spinning up the Camel JMS Consumer Route");
        
        try {
            context = new DefaultCamelContext();
            context.setName("consumer-context");
            
            SjmsComponent sjms = new SjmsComponent();
            sjms.setConnectionFactory(connectionFactory);
            context.addComponent("sjms", sjms);
            
            context.addRoutes(new RouteBuilder() {
                
                @Override
                public void configure() throws Exception {
                    from("sjms:queue:test.queue")
                        .routeId("consumer-route-1")
                        .log("Consumer message received : ${body}");
                }
            });
            context.start();
        } catch (Exception e) {
            throw new RuntimeCamelException("Error Adding Route to the Camel Context", e);
        }
        LOGGER.info("Spinning up the Camel JMS Consumer Route: SUCCESS");
    }

    @Deactivate
    public void deactivate() {
        LOGGER.info("Shutting down the Camel JMS Consumer Route");
        try {
            context.stopRoute("consumer-route-1");
            context.stop();
            context = null;
        } catch (Exception e) {
            // e.printStackTrace();
            LOGGER.error("TODO Auto-generated catch block: " + e.getLocalizedMessage());
            throw new RuntimeCamelException("Error Adding Route to the Camel Context", e);
        }
        LOGGER.info("Shutting down the Camel JMS Consumer Route: SUCCESS");
    }

    @Reference(target="(cfId=amqcf1)")
    public synchronized void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
    
    public synchronized void unsetConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = null;
    }
}
