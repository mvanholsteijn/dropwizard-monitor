dropwizard-monitor
==================
This is a monitor to deploy on PaaS platform using the Java Buildpack and monitor how the platform behaves, when pushing new versions, changing environment properties, 
scaling etc.  This implementation was written using dropwizard. Others are available that are written in node.js and go.

how to deploy
=============
you can deploy this application to Cloudfoundry/Stackato without any special measures: it should be detected by the Java distzip build pack.

$ mvn clean package
$ cd target
$ stackato push -n 
$ stackato open
