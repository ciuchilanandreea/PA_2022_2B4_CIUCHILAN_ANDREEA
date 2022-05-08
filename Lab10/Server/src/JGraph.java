import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.ArrayList;
import java.util.List;

public final class JGraph {
    private JGraph(){

    }
    public static void main(List<User> args)
    {
        Graph<String, DefaultEdge> stringGraph = createStringGraph(args);
        System.out.println("JGraph is:");
        System.out.println(stringGraph.toString());
        System.out.println();

    }
    private static Graph<String, DefaultEdge> createStringGraph(List<User> args)
    {
        Graph<String, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);

        for(User person: args){
            g.addVertex(person.getName());
            for(User friend:person.getFriends()){
                try{
                    g.addEdge(person.getName(),friend.getName());
                }catch(IllegalArgumentException e){
                }

            }

        }
        return g;
    }
}
