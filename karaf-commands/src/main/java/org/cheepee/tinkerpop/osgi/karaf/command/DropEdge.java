/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheepee.tinkerpop.osgi.karaf.command;

import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;

@Command(scope = "tinkerpop", name = "drop-edge", description = "Drops a graph edge")
@Service
public class DropEdge extends AbstractGraphCommand {

    @Argument(required = true, multiValued = false, description = "Edge ID")
    private Long id;

    @Override
    public void doExecute(GraphTraversalSource g)  {
        g.E(id).drop().iterate();
        System.out.println("Dropped edge " + id);
    }

}
