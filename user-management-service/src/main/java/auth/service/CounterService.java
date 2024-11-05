package auth.service;

import auth.entity.dataType.CounterType;

public interface CounterService {
    Integer getNextCounter(CounterType counterType);
}
