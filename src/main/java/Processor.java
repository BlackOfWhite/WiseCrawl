import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Processor {

    final private long depth;

    private static Set<String> visitedSet = new HashSet<>();
    private static Set<String> toVisit = new LinkedHashSet<>();

    public Processor(long depth, String origin) {
        this.depth = depth;
        this.toVisit.add(origin);
    }

    public void start() {
        process();
    }

    private void process() {
        int i = 0;
        long start = System.currentTimeMillis();
        System.out.println("Start: " + start);
        while (visitedSet.size() <= depth && toVisit.size() > 0) {
            final String next = toVisit.iterator().next();
            System.out.println(++i + "." + next);
            Set<String> newLinks = Visitor.visit(next);
            newLinks.removeAll(visitedSet);
            toVisit.addAll(newLinks);
            visitedSet.add(next);
            toVisit.remove(next);
            System.out.println(toVisit.size());
        }
        System.out.println("end: " + (System.currentTimeMillis() - start) + " millis");
    }


}
