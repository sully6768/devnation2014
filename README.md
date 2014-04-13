devnation2014
=============

DevNation 2014 - Simplifying OSGi Development

Karaf 2.3 || Fuse 6.1-ea

org/apache/activemq/activemq-karaf/5.9.1/activemq-karaf-5.9.1-features.xml

Project 1: Simple Component

From a command prompt:
cd ds-simple-component
mvn clean install

In Karaf at the command line run:
install -s mvn:org.dn.ds/org.dn.ds.simple.component/1.0.0-SNAPSHOT

Then type:
log:display

There you will see:
2014-04-13 12:01:15,656 | INFO  | {snip} | 154 - org.dn.ds.simple.component - 1.0.0.SNAPSHOT | Stopping the component
2014-04-13 12:01:15,661 | INFO  | {snip} | 154 - org.dn.ds.simple.component - 1.0.0.SNAPSHOT | Starting the component




In a running instance of 

config:delete org.dn.ds.managed.service.provider.ManagedServiceProvider 
config:edit org.dn.ds.managed.service.provider.ManagedServiceProvider
config:propset name Scott
config:update

config:edit org.dn.ds.managed.service.provider.ManagedServiceProvider
config:propset name James
config:update

config:delete org.dn.ds.managed.service.provider.UpdateableManagedServiceProvider 

config:edit org.dn.ds.managed.service.provider.UpdateableManagedServiceProvider
config:propset name Scott
config:update

config:edit org.dn.ds.managed.service.provider.UpdateableManagedServiceProvider
config:propset name James
config:update

scr:deactivate org.dn.ds.managed.service.provider.UpdateableManagedServiceProvider


install -s mvn:org.dn.ds/org.dn.ds.activemq.cf.service/1.0.0-SNAPSHOT

config:delete ActiveMQConnectionFactoryService
config:edit ActiveMQConnectionFactoryService
config:propset brokerURL tcp://localhost:61616
config:propset username admin
config:propset password admin
config:update

features:install camel-sjms
install -s mvn:org.dn.ds/org.dn.ds.camel.router.factory/1.0.0-SNAPSHOT