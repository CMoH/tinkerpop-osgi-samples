/*
 * Copyright 2017 apifocal LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cheepee.tinkerpop.osgi.factory;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection;
import org.apache.tinkerpop.gremlin.process.remote.RemoteGraph;

/**
 * Creates a {@link RemoteGraph}.
 *
 * Right now {@link RemoteGraph} is deprecated, but it's good enough to show that the
 * gremlin-driver-shaded bundle works within osgi.
 *
 * Tinkerpop relies on files that chain reference each other to configure the graph, which is hard to
 * configure in our environment. This minimal factory replaces and hardcodes the settings in the
 * tinkerpop.properties file, which would in turn reference the YAML graph definition file.
 */
public class GraphFactory {

    public RemoteGraph createGraph(String yamlFile) {
        Configuration graphConfig = new PropertiesConfiguration();
        graphConfig.addProperty(RemoteGraph.GREMLIN_REMOTE_GRAPH_REMOTE_CONNECTION_CLASS, "org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection");
        graphConfig.addProperty(DriverRemoteConnection.GREMLIN_REMOTE_DRIVER_CLUSTERFILE, yamlFile);
        //graphConfig.addProperty(DriverRemoteConnection.GREMLIN_REMOTE_DRIVER_SOURCENAME, "g");
        return RemoteGraph.open(graphConfig);
    }

}
