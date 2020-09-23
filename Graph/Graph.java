package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Graph {

    private class Vertex {
        HashMap<String, Integer> nbrs = new HashMap<>();
    }

    HashMap<String , Vertex> vtces;

    Graph() {
         vtces = new HashMap<>();
    }

    public int numVertex() {
        return this.vtces.size();
    }

    public boolean containsVertex(String Vname) {
        return this.vtces.containsKey(Vname);
    }

    public void addVertex(String Vname) {
        Vertex vtx = new Vertex();
        vtces.put(Vname,vtx);
    }

    public void removeVertex(String Vname) {
        Vertex vtx = vtces.get(Vname);
        ArrayList<String> keys = new ArrayList<>(vtx.nbrs.keySet());
        for(String key : keys) {
            Vertex nbrVtx = vtces.get(key);
            nbrVtx.nbrs.remove(Vname);
        }
        vtces.remove(Vname);
    }

    public int numEdges() {
        int count = 0;
        ArrayList<String> keys = new ArrayList<>(vtces.keySet());
        for(String key : keys) {
            Vertex vtx = vtces.get(key);
            count = count + vtx.nbrs.size();
        }
        return count / 2;
    }

    public boolean containsEdge(String Vname1,String Vname2) {
        Vertex vtx1 = vtces.get(Vname1);
        Vertex vtx2 = vtces.get(Vname2);

        if(vtx1 == null || vtx2 == null || !vtx1.nbrs.containsKey(Vname2)) {
            return false;
        }

        return true;
     }

    public void addEdge(String Vname1,String Vname2,int cost) {
        Vertex vtx1 = vtces.get(Vname1);
        Vertex vtx2 = vtces.get(Vname2);

        if(vtx1 == null || vtx2 == null || vtx1.nbrs.containsKey(Vname2)) {
            return;
        }
        vtx1.nbrs.put(Vname2,cost);
        vtx2.nbrs.put(Vname1,cost);
    }

    public void removeEdge(String Vname1,String Vname2) {
        Vertex vtx1 = vtces.get(Vname1);
        Vertex vtx2 = vtces.get(Vname2);

        if(vtx1 == null || vtx2 == null || !vtx1.nbrs.containsKey(Vname2)) {
            return;
        }

        vtx1.nbrs.remove(Vname2);
        vtx2.nbrs.remove(Vname1);
    }

    public void display() {
        System.out.println("__________________________________________________");
        ArrayList<String> keys = new ArrayList<>(vtces.keySet());
        for(String key : keys) {
            Vertex vtx = vtces.get(key);
            System.out.println(key + " : " + vtx.nbrs);
        }
        System.out.println("__________________________________________________");
    }

    public boolean hasPath(String Vname1,String Vname2,HashMap<String,Boolean> processed) {
        processed.put(Vname1,true);

        // Check Direct Edge is available Or Not
        if(containsEdge(Vname1,Vname2)) {
            return true;
        }

        // devote the Work to Nghbrs
        Vertex vtx = vtces.get(Vname1);
        ArrayList<String> nbrs = new ArrayList<>(vtx.nbrs.keySet());
        for(String nbr : nbrs) {
            if(!processed.containsKey(nbr) && hasPath(nbr,Vname2,processed)) {
                return true;
            }
        }
        return false;
    }

    private class Pair {
        String Vname;
        String pof;
    }

    // BFS
    public boolean bfs(String src,String des) {

        HashMap<String,Boolean> processed = new HashMap<>();
        LinkedList<Pair> queue = new LinkedList<>();

        // Create a New Pair
        Pair sp = new Pair();
        sp.Vname = src;
        sp.pof = src;

        // Put The New Pair in Queue
        queue.addLast(sp);

        // While Queue is Not Empty Keep On Doing the Work
        while(!queue.isEmpty()) {

            // Remove a Pair for From Queue
            Pair rp = queue.removeFirst();

            // If Processed Pair already Available then Continue
            if(processed.containsKey(rp.Vname)) {
                continue;
            }

            // Processed Put
            processed.put(rp.Vname, true);

            // If Direct Edge Available then return true
            if(containsEdge(rp.Vname,des)) {
                System.out.println(rp.pof + des);
                return true;
            }

            // Find Nghbrs
            Vertex rpvtx = vtces.get(rp.Vname);
            ArrayList<String> nbrs = new ArrayList<>(rpvtx.nbrs.keySet());

            // Loop on Nghbrs
            for(String nbr : nbrs) {
                // Process Only Unprocessed Nghbrs
                if(!processed.containsKey(nbr)) {
                    // Make a New Pair Of Nghbrs and Put in Queue
                    Pair np = new Pair();
                    np.Vname = nbr;
                    np.pof = rp.pof +  nbr;
                    queue.addLast(np);
                }
            }
        }
        return false;
    }

    // DFS
    public boolean dfs(String src,String des) {

        HashMap<String,Boolean> processed = new HashMap<>();
        LinkedList<Pair> stack = new LinkedList<>();

        // Create a New Pair
        Pair sp = new Pair();
        sp.Vname = src;
        sp.pof = src;

        // Put The New Pair in Queue
        stack.addFirst(sp);

        // While Queue is Not Empty Keep On Doing the Work
        while(!stack.isEmpty()) {

            // Remove a Pair for From Queue
            Pair rp = stack.removeFirst();

            // If Processed Pair already Available then Continue
            if(processed.containsKey(rp.Vname)) {
                continue;
            }

            // Processed Put
            processed.put(rp.Vname, true);

            // If Direct Edge Available then return true
            if(containsEdge(rp.Vname,des)) {
                System.out.println(rp.pof + des);
                return true;
            }

            // Find Nghbrs
            Vertex rpvtx = vtces.get(rp.Vname);
            ArrayList<String> nbrs = new ArrayList<>(rpvtx.nbrs.keySet());

            // Loop on Nghbrs
            for(String nbr : nbrs) {
                // Process Only Unprocessed Nghbrs
                if(!processed.containsKey(nbr)) {
                    // Make a New Pair Of Nghbrs and Put in Queue
                    Pair np = new Pair();
                    np.Vname = nbr;
                    np.pof = rp.pof +  nbr;
                    stack.addFirst(np);
                }
            }
        }
        return false;
    }

    // BF Traversal
    public void bft() {

        HashMap<String,Boolean> processed = new HashMap<>();
        LinkedList<Pair> queue = new LinkedList<>();

        ArrayList<String> keys = new ArrayList<>(vtces.keySet());

        // For Every Node(Vertexes) Repeat the Process
        for(String key : keys) {

            if(processed.containsKey(key)) {
                continue;
            }

            // Create a New Pair
            Pair sp = new Pair();
            sp.Vname = key;
            sp.pof = key;

            // Put The New Pair in Queue
            queue.addLast(sp);

            // While Queue is Not Empty Keep On Doing the Work
            while(!queue.isEmpty()) {

                // Remove a Pair for From Queue
                Pair rp = queue.removeFirst();

                // If Processed Pair already Available then Continue
                if(processed.containsKey(rp.Vname)) {
                    continue;
                }

                // Processed Put
                processed.put(rp.Vname, true);

                // If Direct Edge Available then return true
                System.out.println(rp.Vname + " " + rp.pof);

                // Find Nghbrs
                Vertex rpvtx = vtces.get(rp.Vname);
                ArrayList<String> nbrs = new ArrayList<>(rpvtx.nbrs.keySet());

                // Loop on Nghbrs
                for(String nbr : nbrs) {
                    // Process Only Unprocessed Nghbrs
                    if(!processed.containsKey(nbr)) {
                        // Make a New Pair Of Nghbrs and Put in Queue
                        Pair np = new Pair();
                        np.Vname = nbr;
                        np.pof = rp.pof +  nbr;
                        queue.addLast(np);
                    }
                }
            }
        }
    }

    // DF Traversal
    public void dft() {

        HashMap<String,Boolean> processed = new HashMap<>();
        LinkedList<Pair> stack = new LinkedList<>();

        ArrayList<String> keys = new ArrayList<>(vtces.keySet());

        // For Every Node(Vertexes) Repeat the Process
        for(String key : keys) {

            if(processed.containsKey(key)) {
                continue;
            }

            // Create a New Pair
            Pair sp = new Pair();
            sp.Vname = key;
            sp.pof = key;

            // Put The New Pair in Queue
            stack.addFirst(sp);

            // While Queue is Not Empty Keep On Doing the Work
            while(!stack.isEmpty()) {

                // Remove a Pair for From Queue
                Pair rp = stack.removeFirst();

                // If Processed Pair already Available then Continue
                if(processed.containsKey(rp.Vname)) {
                    continue;
                }

                // Processed Put
                processed.put(rp.Vname, true);

                // If Direct Edge Available then return true
                System.out.println(rp.Vname + " " + rp.pof);

                // Find Nghbrs
                Vertex rpvtx = vtces.get(rp.Vname);
                ArrayList<String> nbrs = new ArrayList<>(rpvtx.nbrs.keySet());

                // Loop on Nghbrs
                for(String nbr : nbrs) {
                    // Process Only Unprocessed Nghbrs
                    if(!processed.containsKey(nbr)) {
                        // Make a New Pair Of Nghbrs and Put in Queue
                        Pair np = new Pair();
                        np.Vname = nbr;
                        np.pof = rp.pof +  nbr;
                        stack.addFirst(np);
                    }
                }
            }
        }
    }

    // Check Cycle Available or Not
    public boolean isCycle() {

        HashMap<String,Boolean> processed = new HashMap<>();
        LinkedList<Pair> queue = new LinkedList<>();

        ArrayList<String> keys = new ArrayList<>(vtces.keySet());

        // For Every Node(Vertexes) Repeat the Process
        for(String key : keys) {

            if(processed.containsKey(key)) {
                continue;
            }

            // Create a New Pair
            Pair sp = new Pair();
            sp.Vname = key;
            sp.pof = key;

            // Put The New Pair in Queue
            queue.addLast(sp);

            // While Queue is Not Empty Keep On Doing the Work
            while(!queue.isEmpty()) {

                // Remove a Pair for From Queue
                Pair rp = queue.removeFirst();

                // If Processed Pair already Available then Continue
                if(processed.containsKey(rp.Vname)) {
                    return true;
                }

                // Processed Put
                processed.put(rp.Vname, true);

                // Find Nghbrs
                Vertex rpvtx = vtces.get(rp.Vname);
                ArrayList<String> nbrs = new ArrayList<>(rpvtx.nbrs.keySet());

                // Loop on Nghbrs
                for(String nbr : nbrs) {
                    // Process Only Unprocessed Nghbrs
                    if(!processed.containsKey(nbr)) {
                        // Make a New Pair Of Nghbrs and Put in Queue
                        Pair np = new Pair();
                        np.Vname = nbr;
                        np.pof = rp.pof +  nbr;
                        queue.addLast(np);
                    }
                }
            }
        }
        return false;
    }

    // Check Graph is Connected or Not
    public boolean isConnected() {

        int flag = 0;

        HashMap<String,Boolean> processed = new HashMap<>();
        LinkedList<Pair> queue = new LinkedList<>();

        ArrayList<String> keys = new ArrayList<>(vtces.keySet());

        // For Every Node(Vertexes) Repeat the Process
        for(String key : keys) {

            if(processed.containsKey(key)) {
                continue;
            }

            flag++;

            // Create a New Pair
            Pair sp = new Pair();
            sp.Vname = key;
            sp.pof = key;

            // Put The New Pair in Queue
            queue.addLast(sp);

            // While Queue is Not Empty Keep On Doing the Work
            while(!queue.isEmpty()) {

                // Remove a Pair for From Queue
                Pair rp = queue.removeFirst();

                // If Processed Pair already Available then Continue
                if(processed.containsKey(rp.Vname)) {
                    continue;
                }

                // Processed Put
                processed.put(rp.Vname, true);

                // Find Nghbrs
                Vertex rpvtx = vtces.get(rp.Vname);
                ArrayList<String> nbrs = new ArrayList<>(rpvtx.nbrs.keySet());

                // Loop on Nghbrs
                for(String nbr : nbrs) {
                    // Process Only Unprocessed Nghbrs
                    if(!processed.containsKey(nbr)) {
                        // Make a New Pair Of Nghbrs and Put in Queue
                        Pair np = new Pair();
                        np.Vname = nbr;
                        np.pof = rp.pof +  nbr;
                        queue.addLast(np);
                    }
                }
            }
        }

        if(flag >= 2) {
            return false;
        } else {
            return true;
        }
    }

    // Check Graph is Tree or Not
    public boolean isTree() {
        return !isCycle() && isConnected();
    }

    // BF Traversal
    public ArrayList<ArrayList<String>> getConnectedComponents() {

        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        HashMap<String,Boolean> processed = new HashMap<>();
        LinkedList<Pair> queue = new LinkedList<>();

        ArrayList<String> keys = new ArrayList<>(vtces.keySet());

        // For Every Node(Vertexes) Repeat the Process
        for(String key : keys) {

            if(processed.containsKey(key)) {
                continue;
            }

            // For New Component Create a New ArrayList
            ArrayList<String> subans = new ArrayList<>();

            // Create a New Pair
            Pair sp = new Pair();
            sp.Vname = key;
            sp.pof = key;

            // Put The New Pair in Queue
            queue.addLast(sp);

            // While Queue is Not Empty Keep On Doing the Work
            while(!queue.isEmpty()) {

                // Remove a Pair for From Queue
                Pair rp = queue.removeFirst();

                // If Processed Pair already Available then Continue
                if(processed.containsKey(rp.Vname)) {
                    continue;
                }

                // Processed Put
                processed.put(rp.Vname, true);

                // Put in Subans ArrayList
                subans.add(rp.Vname);

                // Find Nghbrs
                Vertex rpvtx = vtces.get(rp.Vname);
                ArrayList<String> nbrs = new ArrayList<>(rpvtx.nbrs.keySet());

                // Loop on Nghbrs
                for(String nbr : nbrs) {
                    // Process Only Unprocessed Nghbrs
                    if(!processed.containsKey(nbr)) {
                        // Make a New Pair Of Nghbrs and Put in Queue
                        Pair np = new Pair();
                        np.Vname = nbr;
                        np.pof = rp.pof +  nbr;
                        queue.addLast(np);
                    }
                }
            }

            // Put Subans in Final Answer
            ans.add(subans);
        }
        return ans;
    }

    // Prims Algorithm
    private class PrimsPair {
        String Vname;
        String acqVname;
        int cost;
    }

    public Graph prims() {

        Graph mst = new Graph();
        HashMap<String,PrimsPair> map = new HashMap<>();
        


        return mst;
    }

}
