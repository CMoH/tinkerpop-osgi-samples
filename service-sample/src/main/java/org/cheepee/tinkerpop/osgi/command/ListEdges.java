/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheepee.tinkerpop.osgi.command;

import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.apache.karaf.shell.support.table.ShellTable;

@Command(scope = "tinkerpop", name = "list-edges", description = "List edges in the graph")
@Service
public class ListEdges extends AbstractGraphCommand {

    @Override
    public void doExecute() {
        ShellTable table = new ShellTable();
        table.emptyTableText("No edges");
        table.column("Edge id");
        table.column("Label");
        table.column("From vertex");
        table.column("To vertex");
        table.column("Properties");

        graph.traversal().E().toStream()
                .forEach((edge) -> {
                    table.addRow().addContent(
                            edge.id(),
                            graph.traversal().E(edge.id()).label().next(),
                            edge.outVertex().id(),
                            edge.inVertex().id(),
                            graph.traversal().E(edge.id()).properties().key().toList());
                });

        table.print(System.out);
    }

}
