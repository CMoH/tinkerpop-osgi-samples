/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheepee.tinkerpop.osgi.command;

import java.util.Collection;
import java.util.StringTokenizer;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.structure.Graph;

public abstract class AbstractGraphCommand implements Action {

    @Reference
    Graph graph;

    @Override
    public Object execute() throws Exception {
        if (graph == null) {
            System.out.println("no tinkerpop graph service available");
            return null;
        }
        doExecute();
        return null;
    }

    protected abstract void doExecute() throws Exception;

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
