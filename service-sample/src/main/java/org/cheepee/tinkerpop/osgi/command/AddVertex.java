/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheepee.tinkerpop.osgi.command;

import java.util.Collection;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Option;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.structure.Vertex;

/**
 *
 */
@Command(scope = "tinkerpop", name = "add-vertex", description = "Add a vertex to the graph")
@Service
public class AddVertex extends AbstractGraphCommand {

    @Argument(index = 0, required = true, multiValued = false, description = "Label")
    private String label;

    @Option(name = "-p", aliases = {"--property"}, required = false, multiValued = true, description = "Zero, one or more vertex properties in format key=value")
    private Collection<String> properties;

    @Override
    public void doExecute() {
        GraphTraversal<Vertex, Vertex> t = graph.traversal().addV(label);
        addProperties(t, properties);
        Vertex v = t.next(); // commits the create operation
        System.out.println("Added vertex " + v.id());
    }

}
