package st.more.condition;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface Handoff<T> {
    void put(@Nonnull T obj) throws InterruptedException;

    @Nonnull
    T take() throws InterruptedException;
}
