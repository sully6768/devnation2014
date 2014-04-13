/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.devnation.scr.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.Session;

import static org.junit.Assert.assertNotNull;

import org.apache.activemq.broker.BrokerPlugin;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.security.AuthenticationUser;
import org.apache.activemq.security.SimpleAuthenticationPlugin;
import org.dn.ds.activemq.cf.service.provider.ActiveMQConnectionFactoryService;
import org.junit.Test;

public class ActiveMQConnectionFactoryServiceTest {

    @Test
    public void testConnectionFactory() throws Exception {
        BrokerService broker = new BrokerService();
        broker.setTransportConnectorURIs(new String[]{"tcp://127.0.0.1:61616"});
        broker.setDataDirectory("./target/activemq-data");
        SimpleAuthenticationPlugin sap = new SimpleAuthenticationPlugin();
        AuthenticationUser au = new AuthenticationUser("admin", "admin", "admin");
        List<AuthenticationUser> users = new ArrayList<AuthenticationUser>();
        users.add(au);
        sap.setUsers(users);
        broker.setPlugins(new BrokerPlugin[]{sap});
        
        broker.start();
        
        ActiveMQConnectionFactoryService acfs = new ActiveMQConnectionFactoryService();
        Map<String, String> map = new HashMap<String, String>();
        map.put("brokerURL", "tcp://127.0.0.1:61616");
        map.put("username", "admin");
        map.put("password", "admin");
        acfs.start(map);
        
        Connection conn = acfs.createConnection();
        assertNotNull(conn);
        conn.start();
        assertNotNull(conn.createSession(false, Session.AUTO_ACKNOWLEDGE));
        conn.stop();
        broker.stop();
    }
}
