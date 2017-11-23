/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheepee.tinkerpop.osgi.karaf.command;

import java.util.Collection;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Option;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;

@Command(scope = "tinkerpop", name = "add-edge", description = "List edges in the graph")
@Service
public class AddEdge extends AbstractGraphCommand {

    @Argument(index = 0, required = true, multiValued = false, description = "Label")
    private String label;

    @Argument(index = 1, required = true, multiValued = false, description = "From vertex ID")
    private Long from;

    @Argument(index = 2, required = true, multiValued = false, description = "To vertex ID")
    private Long to;

    @Option(name = "-p", aliases = {"--property"}, required = false, multiValued = true, description = "Zero, one or more vertex properties in format key=value")
    private Collection<String> properties;

    @Override
    public void doExecute(GraphTraversalSource g)  {
        GraphTraversal<Vertex, Edge> t = g.V(from).addE(label).to(__.V(to));
        addProperties(t, properties);
        Edge e = t.next(); // commits the create operation
        System.out.println("Added edge " + e.id());
    }

}
