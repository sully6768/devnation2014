devnation2014
=============

DevNation 2014 - Simplifying OSGi Development

Fuse 6.1-ea


install -s mvn:org.dn.ds/org.dn.ds.activemq.cf.service/1.0.0-SNAPSHOT

config:delete ActiveMQConnectionFactoryService
config:edit ActiveMQConnectionFactoryService
config:propset brokerURL tcp://localhost:61616
config:propset username admin
config:propset password admin
config:update
