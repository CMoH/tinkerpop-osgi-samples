Sample project using Tinkerpop in OSGi
======================================

This project showcases minimal code for accessing a remote tinkerpop cluster from karaf.

Quick-start
===========

1. build this sample
    ```
    mvn clean install
    ```
2. download tinkerpop's gremlin-server and start it
    ```
    ~ $ cd tmp/apache-tinkerpop-gremlin-server-3.2.4/
    ~/tmp/apache-tinkerpop-gremlin-server-3.2.4 $ bin/gremlin-server.sh
    ```
3. start karaf-4.1.3
    ```
    bin/karaf
    ```
4. add the tinkerpop karaf features repository
    ```
    feature:repo-add mvn:org.cheepee.tinkerpop.osgi/tinkerpop-features/LATEST/xml/features
    ```
5. add the tinkerpop or janusgraph features
    ```
    feature:install janusgraph-service-sample tinkerpop-karaf-commands
    ```

or alternatively

    ```
    feature:install tinkerpop-service-sample tinkerpop-karaf-commands
    ```
6. use the supplied commands to look and manipulate the graph

    ```
    add-vertex label1
    add-vertex label2
    add-edge -p edgeProperty=myproperty edgeLabel 0 1
    list-vertices
    list-edges
    drop-vertex 0
    drop-vertex 1
    ```
