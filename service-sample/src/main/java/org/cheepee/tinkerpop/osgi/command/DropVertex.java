/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheepee.tinkerpop.osgi.command;

import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Service;

@Command(scope = "tinkerpop", name = "drop-vertex", description = "Drops a graph vertex")
@Service
public class DropVertex extends AbstractGraphCommand {

    @Argument(required = true, multiValued = false, description = "Vertex ID")
    private Long id;

    @Override
    public void doExecute()  {
        graph.traversal().V(id).drop().iterate();
        System.out.println("Dropped vertex " + id);
    }

}
