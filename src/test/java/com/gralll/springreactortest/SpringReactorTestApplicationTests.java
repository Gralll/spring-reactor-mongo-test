package com.gralll.springreactortest;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class SpringReactorTestApplicationTests {

	@Test
	public void shouldHandleReactivelly() {
		Flux<String> ids = getIds();

		Flux<String> combinations =
				ids.take(3).log().flatMap(id -> {
					Mono<String> nameTask = getNameById(id);
					Mono<Integer> statTask = getStatsById(id);

					return nameTask.zipWith(statTask,
							(name, stat) -> "Name " + name + " has stats " + stat);
				});

		Mono<List<String>> result = combinations.collectList();

		List<String> results = result.block();
		assertThat(results).containsExactly(
				"Name 1 name has stats 2",
				"Name 2 name has stats 3",
				"Name 3 name has stats 4"
		);
	}

	private Flux<String> getIds() {
		String[] ids = IntStream.range(1, 1000).mapToObj(String::valueOf).toArray(String[]::new);
		return Flux.just(ids).delayElements(Duration.of(0, ChronoUnit.SECONDS));
	}

	private Mono<String> getNameById(String id) {
		return Mono.just(id + " name");
	}

	private Mono<Integer> getStatsById(String id) {
		return Mono.just(Integer.valueOf(id) + 1);
	}

}