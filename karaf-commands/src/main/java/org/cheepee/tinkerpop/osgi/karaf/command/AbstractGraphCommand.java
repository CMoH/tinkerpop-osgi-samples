/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheepee.tinkerpop.osgi.karaf.command;

import java.util.Collection;
import java.util.StringTokenizer;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.util.empty.EmptyGraph;

public abstract class AbstractGraphCommand implements Action {

    private final Graph emptyGraph = EmptyGraph.instance();

    @Reference
    Client client;

    @Override
    public Object execute() throws Exception {
        if (client == null) {
            System.out.println("no tinkerpop graph service available");
            return null;
        }
        GraphTraversalSource traversal = emptyGraph.traversal().withRemote(
                DriverRemoteConnection.using(client.getCluster(), "g"));
        doExecute(traversal);
        return null;
    }

    protected abstract void doExecute(GraphTraversalSource g) throws Exception;

    protected static boolean addProperties(GraphTraversal<?, ?> traversal, Collection<String> properties) {
        if (properties != null) {
            for (String prop : properties) {
                StringTokenizer tk = new StringTokenizer(prop, "=");
                if (tk.countTokens() != 2) {
                    System.out.println("Invalid property assignment " + prop);
                    return false;
                }
                String key = tk.nextToken();
                String value = tk.nextToken();
                traversal.property(key, value);
            }
        }
        return true;
    }

}
