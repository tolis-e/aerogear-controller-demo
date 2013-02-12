
# AeroGear Controller Demo
This project show cases some of the functionality of [AeroGear Controller](https://github.com/aerogear/aerogear-controller).  
An instance of this demo is deployed on [OpenShift](https://controller-aerogear.rhcloud.com/aerogear-controller-demo/) and can 
be used to try it out. Please refer to the _Installation_ section for deploying locally.

## Demo Contents
This demo project has a number of [Routes](https://github.com/aerogear/aerogear-controller-demo/blob/master/src/main/java/org/jboss/aerogear/controller/demo/Routes.java#L45), 
some are used in a MVC fashion while others are expected to be called as RESTful resources.

### Model View Controller Routes
These routes are used for the web based interface which you can find on [OpenShift](https://controller-aerogear.rhcloud.com/aerogear-controller-demo/). 
Please refer to the [User Guide](http://aergear.org/docs/guides/aerogear-controller) for detailed information about configuring routes.

### RESTful Controller Routes
There are few routes that are intended to respond with JSON data. These routes deal with the ```/cars``` resource and demonstrate 
[Pagination](http://aergear.org/docs/guides/aerogear-controller/pagination.html).  
The basic idea is that a client wants to limit the number of _cars_ it receives per request to a certain number.

#### Requesting the first set of cars:

     curl -i --header "Accept: application/json" "https://controller-aerogear.rhcloud.com/aerogear-controller-demo/cars?offset=0&color=red&limit=5"

If you inspect the headers returned from the output of the above commad you'll find one named ```Link```. This header is defined
in the [Web Linking](http://tools.ietf.org/html/rfc5988) specification and contains ```next/previous``` URLs where applicable.  

#### Requesting the next set of cars:
To retrieve the next set of cars, use the URL for ```next```:

     curl -i --header "Accept: application/json" "http://controller-aerogear.rhcloud.com/aerogear-controller-demo/cars?offset=5&color=red&limit=5"

## Installation
Building the project is done using maven:
    
    mvn install
    
### Deploy
An AeroGear Controller applicaton can be deployed to any application server with a CDI container.  
However, this demo uses a datasource that by default exists in JBoss AS 7.x and it is the preferred deployment environment.

#### Manual deployment

    $ cp target/aerogear-controller-demo.war $AS7_HOME/standalone/deployments

#### CLI deployment

    $ cd $AS7_HOME
    $ ./jboss-cli.sh --connect
    $ [standalone@localhost:9999 /] deploy /path/to/aerogear-controller-demo/target/aerogear-controller-demo.war
     
## Documentation
* [User Guide](http://aergear.org/docs/guides/aerogear-controller)
* [API](http://aerogear.org/docs/specs/aerogear-controller)
* [REST API](http://aerogear.org/docs/specs/aerogear-rest-api)
* [JBosss CLI](https://community.jboss.org/wiki/CommandLineInterface)

## Community
* [User Forum](https://community.jboss.org/en/aerogear?view=discussions)
* [Developer Mailing List](http://aerogear-dev.1069024.n5.nabble.com)

## Issue Tracker
* [JIRA](https://issues.jboss.org/browse/AEROGEAR)