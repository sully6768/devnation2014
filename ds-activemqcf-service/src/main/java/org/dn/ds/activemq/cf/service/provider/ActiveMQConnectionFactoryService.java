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
package org.dn.ds.activemq.cf.service.provider;

import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.jms.ConnectionFactory;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 */
@Component(name="ActiveMQConnectionFactoryService",
           configurationPolicy = ConfigurationPolicy.REQUIRE, 
           servicefactory = true, 
           service={ConnectionFactory.class},
           property={"cfId=amqcf1"})
public class ActiveMQConnectionFactoryService extends ActiveMQConnectionFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActiveMQConnectionFactoryService.class);
    
    private ComponentContext componentContext;
    
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    @Activate
    public void start(final Map<String, ?> properties, ComponentContext componentContext) {
    	try {
    		rwl.writeLock().lock();
    		
        	this.componentContext = componentContext;
        	this.setExceptionListener(new InternalExceptionListener());
        	
            LOGGER.info("Starting the ConnectionFactoryService");
            if (properties.containsKey("brokerURL")) {
                String brokerURL = (String)properties.get("brokerURL");
                this.setBrokerURL(brokerURL);
            } else {
                LOGGER.error("No property found for brokerURL");
            }
            if (properties.containsKey("username")) {
                String username = (String)properties.get("username");
                this.setUserName(username);
            } else {
                LOGGER.error("No property found for username");
            }
            if (properties.containsKey("password")) {
                String password = (String)properties.get("password");
                this.setPassword(password);
            } else {
                LOGGER.error("No property found for password");
            }
            LOGGER.info("Starting the ConnectionFactoryService: SUCCESS");
    	} finally {
    		rwl.writeLock().unlock();
    	}
    	

    }

    @Deactivate
    public void stop() {
        LOGGER.info("Stopping the ConnectionFactoryService");
        LOGGER.info("Stopping the ConnectionFactoryService: SUCCESS");
    }
    
    private class InternalExceptionListener implements ExceptionListener {

		@Override
		public void onException(JMSException exception) {
			// TODO Auto-generated method stub
			if (componentContext != null) {
				componentContext.disableComponent("ActiveMQConnectionFactoryService");	
			}
			
		}
    	
    }

}
