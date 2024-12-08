package at.fhtw.ocrservice.services.dto;

public interface Mapper<S, T> {
    T mapToTarget(S source);
    S mapToSource(T target);
}