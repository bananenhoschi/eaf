package ch.fhnw.reactor;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

@Slf4j
public class Part05Subscribe {

    /**
     * Return a Flux which emits: 1, 2, 3 integers and then completes.
     * Subscribe to this Flux without any Consumer. Inspect the logs by using the 'log' operator.
     */
    public Flux<Integer> subscribeEmpty() {
        Flux<Integer> flux = Flux.just(1, 2, 3); // TO BE REMOVED
        flux.log().subscribe();  // TO BE REMOVED
        return flux;
    }

    /**
     * Return a Flux which emits: 1, 2, 3 integers and then completes
     * Subscribe to this Flux using a Consumer, which prints out the elements. Inspect the logs by using the 'log' operator.
     */
    public Flux<Integer> subscribeWithConsumer() {
        Flux<Integer> flux = Flux.just(1, 2, 3);
        flux.log().subscribe(i -> log.info("{}", i));
        return flux;
    }

    /**
     * Return a Flux which emits: 1, 2, 3 integers and then completes
     * Subscribe to this Flux using a Consumer (which prints out the elements) and complete Consumer
     * (which prints out "completed"). Inspect the logs by using the 'log' operator.
     */
    public Flux<Integer> subscribeWithConsumerAndCompleteConsumer() {
        Flux<Integer> flux = Flux.just(1, 2, 3);
        flux.log().subscribe(i -> log.info("{}", i), null, () -> log.info("completed"));
        return flux;
    }

    /**
     * Return a Flux which emits: 1, 2, 3 integers and then signals error.
     * Subscribe to this Flux using a Consumer (which prints out the elements) and error Consumer
     * (which logs the error message).
     * Inspect the logs by using the 'log' operator.
     */
    public Flux<Integer> subscribeWithConsumerAndErrorConsumer() {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4).map(i -> {
            if (i != 4) {
                return i;
            } else {
                throw new IllegalStateException("error");
            }
        });
        flux.log().subscribe(integer -> log.info("{}", integer), throwable -> log.info("{}", throwable.getMessage()));
        return flux;
    }

    /**
     * Return a Flux which emits: 1, 2, 3 integers and then completes.
     * Subscribe to this Flux using a Subscription Consumer requesting only 2 elements.
     * Inspect the logs by using the 'log' operator.
     */
    public Flux<Integer> subscribeWithSubscriptionConsumer() {
        Flux<Integer> flux = Flux.just(1, 2, 3);
        flux.log().subscribe(null, null, null, new Consumer<Subscription>() {
            @Override
            public void accept(Subscription subscription) {
                subscription.request(2);
            }
        });
        return flux;
    }

    /**
     * Return a Flux which emits 1, 2, 3 and then completes.
     * Subscribe to this Flux using a Subscriber requesting in onSubscribe 1 element and then later as much as
     * the current element it was. Inspect the logs using the 'log' operator.
     */
    public Flux<Integer> subscribeWithSubscription() {
        Flux<Integer> flux = Flux.just(1, 2, 3);
        flux.log().subscribe(new Subscriber<Integer>() {

            Subscription s;

            @Override
            public void onSubscribe(Subscription s) {
                this.s = s;
                s.request(1);
            }

            @Override
            public void onNext(Integer integer) {
                s.request(integer);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
        return flux;
    }

}