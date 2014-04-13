devnation2014
=============

DevNation 2014 - Simplifying OSGi Development

Project 2: Simple Service

From a command prompt:
cd ds-simple-service
mvn clean install

In Karaf at the command line run:
install -s mvn:org.dn.ds/org.dn.ds.simple.service/1.0.0-SNAPSHOT

Then type:
scr:list

There you will see:
[53  ] [REGISTERED      ] org.dn.ds.simple.service.provider.SimpleServiceProvider

No go to the Simple Service Component Project