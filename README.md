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

maven 
=====
- appassembler is used to generate a distzip style java buildpack. A distzip style java buildpack is discovered by a bin/ directory and a lib/ directory with jar files. in src/assembly/bin.xml the base is removed:
```xml
	<includeBaseDirectory>false</includeBaseDirectory>
```
- the required parameters for dropwizard startup are specified in the appassembler configuration too.
```xml
            <program>
              <...>...</....>
              <commandLineArguments>
                <commandLineArgument>server</commandLineArgument>
                <commandLineArgument>$BASEDIR/etc/server-config.yaml</commandLineArgument>
              </commandLineArguments>
```
- appassembler generates a startup script and overrides the listen port with the Paas provided PORT environment variable. 
```xml
	<extraJvmArguments>-Ddw.server.applicationConnectors[0].port=${PORT:-8090} </extraJvmArguments>
```
- No memory settings are passed in (-Xms, -Xmx). they are  provided by the PaaS in JAVA_OPTS environment variable, and are calculated based on the the amount of 
memory assigned to the application.

