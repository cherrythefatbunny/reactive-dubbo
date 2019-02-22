import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface DemoInterface {
    String demo1();
    Mono<String> demo2();
    List<String> demo3();
    Flux<String> demo4();
}
