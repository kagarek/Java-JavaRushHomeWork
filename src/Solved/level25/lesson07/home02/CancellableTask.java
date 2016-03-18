package solved.level25.lesson07.home02;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

public interface CancellableTask<T> extends Callable<T> {
    void cancel();

    RunnableFuture<T> newTask();
}