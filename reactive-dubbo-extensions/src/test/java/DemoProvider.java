import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class DemoProvider implements DemoInterface {
    @Override
    public String demo1() {
        return null;
    }

    @Override
    public Mono<String> demo2() {
        return null;
    }

    @Override
    public List<String> demo3() {
        return null;
    }

    @Override
    public Flux<String> demo4() {
        return null;
    }
}
