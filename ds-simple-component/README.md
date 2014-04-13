devnation2014
=============

DevNation 2014 - Simplifying OSGi Development

Project 1: Simple Component

From a command prompt:
cd ds-simple-component
mvn clean install

In Karaf at the command line run:
install -s mvn:org.dn.ds/org.dn.ds.simple.component/1.0.0-SNAPSHOT

Then type:
log:display

There you will see:
INFO  | {snip} | 154 - org.dn.ds.simple.component - 1.0.0.SNAPSHOT | Stopping the component
INFO  | {snip} | 154 - org.dn.ds.simple.component - 1.0.0.SNAPSHOT | Starting the component