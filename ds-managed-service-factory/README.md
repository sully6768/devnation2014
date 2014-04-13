devnation2014
=============

DevNation 2014 - Simplifying OSGi Development

Project 5: Managed Service

From a command prompt:
cd ds-managed-service-factory
mvn clean install

In Karaf at the command line run:
install -s mvn:org.dn.ds/org.dn.ds.managed.service.factory/1.0.0-SNAPSHOT

Then enter:
scr:list

At the bottom of the list you will see:

[53  ] [UNSATISFIED     ] org.dn.ds.managed.service.factory.provider.ManagedServiceFactoryProvider
[54  ] [ACTIVE          ] org.dn.ds.managed.service.factory.ManagedServiceFactoryComponent

Only the service is unsatisfied as its missing a configuration.  The component is not dependent now on a config or service to be active.  It will only do something though if a service is present.

Now lets add a configuration:
 
config:edit org.dn.ds.managed.service.factory.provider.ManagedServiceFactoryProvider-scottes
config:propset name Scott
config:update


In the log file we now see:
Printing the current configuration list: 
Setting new configuration: Scott
Printing the updated configuration list: 
  Name: Scott

Lets add another configuration:

config:edit org.dn.ds.managed.service.factory.provider.ManagedServiceFactoryProvider-james
config:propset name James
config:update


Now in the log file we see:

Printing the current configuration list: 
  Name: Scott
Setting new configuration: James
Printing the updated configuration list: 
  Name: Scott
  Name: James

Now what happens when we delete a config or two?

config:delete org.dn.ds.managed.service.factory.provider.ManagedServiceFactoryProvider-scottes
config:delete org.dn.ds.managed.service.factory.provider.ManagedServiceFactoryProvider-james
