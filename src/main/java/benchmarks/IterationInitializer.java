package benchmarks;

import java.util.List;

public interface IterationInitializer {

    public List<Thread> initialize();
}
