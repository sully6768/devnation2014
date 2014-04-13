devnation2014
=============

DevNation 2014 - Simplifying OSGi Development

Project 4: Managed Service

From a command prompt:
cd ds-managed-service
mvn clean install

In Karaf at the command line run:
install -s mvn:org.dn.ds/org.dn.ds.managed.service/1.0.0-SNAPSHOT

Then enter:
scr:list

At the bottom of the list you will see:

[63  ] [UNSATISFIED     ] org.dn.ds.managed.service.provider.ManagedServiceProvider
[64  ] [UNSATISFIED     ] org.dn.ds.managed.service.ManagedServiceComponent

Now lets add a configuration:
 
config:edit org.dn.ds.managed.service.provider.ManagedServiceProvider
config:propset name Scott
config:update

In the log file we now see:
Hello Scott

And when we type scr:list we get:
[63  ] [ACTIVE          ] org.dn.ds.managed.service.provider.ManagedServiceProvider
[64  ] [ACTIVE          ] org.dn.ds.managed.service.ManagedServiceComponent

Because now both the dependency the service has on a configuration is satisfied and the service dependency the component has is now satisfied.
