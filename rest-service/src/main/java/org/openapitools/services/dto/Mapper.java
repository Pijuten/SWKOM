package org.openapitools.services.dto;

public interface Mapper<S, T> {
    T mapToTarget(S source);
    S mapToSource(T target);
}