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

import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.ser.GryoMessageSerializerV1d0;
import org.apache.tinkerpop.gremlin.structure.io.gryo.GryoMapper;
import org.janusgraph.core.JanusGraph;
import org.janusgraph.graphdb.tinkerpop.JanusGraphIoRegistry;

/**
 * Creates a {@link JanusGraph}.
 *
 * Right now {@link JanusGraph} is deprecated, but it's good enough to show that the
 * gremlin-driver-shaded bundle works within osgi.
 */
public class JanusgraphGraphFactory {

    public Client createGraphClient(String hostname) {
        GryoMapper mapper = GryoMapper.build()
                .addRegistry(JanusGraphIoRegistry.getInstance())
                .create();
        Cluster cluster = Cluster.build()
                .serializer(new GryoMessageSerializerV1d0(mapper))
                .addContactPoints(hostname)
                .maxConnectionPoolSize(2)
                .create();
        Client client = cluster.connect();
        return client;
    }

}
