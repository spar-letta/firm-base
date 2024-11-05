package auth.service;

import auth.entity.Counter;
import auth.entity.dataType.CounterType;
import auth.repository.CounterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CounterServiceImpl implements CounterService {

    private final CounterRepository counterRepository;

    public CounterServiceImpl(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @Transactional
    @Override
    public Integer getNextCounter(CounterType counterType) {
        Optional<Counter> counter = counterRepository.findByCounterType(counterType);

        if (counter.isPresent()) {
            return increment(counter.get()).getCurrent();
        }

        return create(counterType).getCurrent();
    }

    private Counter create(CounterType counterType) {
        return counterRepository.save(new Counter(counterType));
    }

    private Counter increment(Counter counter) {
        counter.setCurrent(counter.getCurrent() + 1);
        return counterRepository.save(counter);
    }
}
