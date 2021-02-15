package za.co.mamamoney.sbu.ussdGateway.model;

public enum SessionStatus {
    NEW,
    PENDING,
    TIMED_OUT,
    RETRIES_EXCEEDED,
    CANCELLED,
    COMPLETE;
}
