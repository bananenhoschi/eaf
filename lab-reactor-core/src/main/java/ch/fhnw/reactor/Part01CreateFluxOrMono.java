package ch.fhnw.reactor;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Learn about creating Mono or Flux publishers.
 * <p>
 * Mono: https://raw.githubusercontent.com/reactor/reactor-core/v3.1.3.RELEASE/src/docs/marble/mono.png
 * <p>
 * Flux: https://raw.githubusercontent.com/reactor/reactor-core/v3.1.3.RELEASE/src/docs/marble/flux.png
 */
public class Part01CreateFluxOrMono {

    /**
     * Create a sequence that emits a single value from the provided Optional<String> parameter.
     */
    Publisher<String> createFromOptional(Optional<String> foo) {
        return Mono.justOrEmpty(foo);
    }

    /**
     * Create a sequence that emits a single value from the provided potentially null value.
     */
    Publisher<String> createFromPotentiallyNull(String foo) {
        return Mono.justOrEmpty(foo);
    }

    /**
     * Create a sequence that emits a single value from the provided lazily captured value.
     */
    Publisher<String> createFromSupplier(Supplier<String> foo) {
        return Mono.fromSupplier(foo);
    }

    /**
     * Create a sequence producing its value using the provided CompletableFuture parameter.
     */
    Publisher<String> createFromFuture(CompletableFuture<String> future) {
        return Mono.fromFuture(future);
    }

    /**
     * Create a sequence producing its value using the provided Callable.
     */
    Publisher<String> createFromCallable(Callable<String> callable) {
        return Mono.fromCallable(callable);
    }

    /**
     * Create a sequence that emits "foo" and "bar" strings.
     */
    Publisher<String> createFooBar() {
        return Flux.just("foo", "bar");
    }

    /**
     * Create a sequence from the provided array.
     */
    Publisher<String> createFromArray(String[] array) {
        return Flux.just(array);
    }

    /**
     * Create a sequence from the provided list.
     */
    Publisher<String> createFromList(List<String> values) {
        return Flux.fromIterable(values);
    }

    /**
     * Create a sequence that emits the items contained in the provided stream.
     */
    Publisher<String> createFromStream(Stream<String> stream) {
        return Flux.fromStream(stream);
    }

    /**
     * Create a sequence that completes without emitting any item.
     */
    Publisher<Void> createEmpty() {
        return Flux.empty();
    }

    /**
     * Create a sequence that terminates with {@link IllegalStateException} error immediately after being subscribed to.
     */
    Publisher<Void> createError() {
        return Flux.error(new IllegalStateException());
    }

    /**
     * Create a sequence that will never signal any data, error or completion signal.
     */
    Publisher<Void> neverEmit() {
        return Flux.never();
    }

    /**
     * Lazily supply the provided publisher every time a subscription is made on the resulting Flux.
     */
    Flux<Integer> lazy(Supplier<Publisher<Integer>> supplier) {
        return Flux.defer(supplier);
    }

    /**
     * Create a sequence that emits increasing values from 0 to 5 each 100ms.
     */
    Publisher<Long> counter() {
        return Flux.interval(Duration.ofMillis(100)).take(5);
    }

    /**
     * Create a sequence that will emit integers from 5 till 10 inclusive.
     */
    Publisher<Integer> fromRange() {
        return Flux.range(5, 6);
    }

}
