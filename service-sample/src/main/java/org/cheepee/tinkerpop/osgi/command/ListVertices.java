/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheepee.tinkerpop.osgi.command;

import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.apache.karaf.shell.support.table.ShellTable;
import org.apache.tinkerpop.gremlin.structure.Vertex;

/**
 *
 */
@Command(scope = "tinkerpop", name = "list-vertices", description = "List vertices in the graph")
@Service
public class ListVertices extends AbstractGraphCommand {

    @Override
    public void doExecute()  {
        ShellTable table = new ShellTable();
        table.emptyTableText("No vertices");
        table.column("Vertex id");
        table.column("Label");
        table.column("Properties");
        graph.traversal().V().toStream().forEach((Vertex vertex) -> {
            table.addRow().addContent(
                    vertex.id(), 
                    graph.traversal().V(vertex.id()).label().next(),
                    graph.traversal().V(vertex.id()).properties().key().toList()
            );
        });
        table.print(System.out);
    }

}
