devnation2014
=============

DevNation 2014 - Simplifying OSGi Development

Project 3: Simple Service Component - Requires Project 2

From a command prompt:
cd ds-simple-service-component
mvn clean install

In Karaf at the command line run:
install -s mvn:org.dn.ds/org.dn.ds.simple.service.component/1.0.0-SNAPSHOT

Then type:
scr:list

There you will see:
[55  ] [ACTIVE          ] org.dn.ds.simple.service.provider.SimpleServiceProvider
[56  ] [ACTIVE          ] org.dn.ds.simple.service.component.SimpleServiceComponent

Notice both are active now.

Then type:
log:display

There you will see:
INFO  | {snip} | 155 - org.dn.ds.simple.service - 1.0.0.SNAPSHOT | Hello component

Now type:
scr:deactivate org.dn.ds.simple.service.component.SimpleServiceComponent

Then type:
log:display

There you will see:
INFO  | {snip} | 155 - org.dn.ds.simple.service - 1.0.0.SNAPSHOT | Goodbye component

