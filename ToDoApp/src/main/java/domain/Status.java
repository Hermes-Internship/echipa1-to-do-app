package domain;

public enum Status {
    NEW, IN_PROGRESS, DONE;

    public static Status getStatus(String statusString) {
        return switch (statusString) {
            case "NEW" -> NEW;
            case "IN_PROGRESS" -> IN_PROGRESS;
            case "DONE" -> DONE;
            default -> null;
        };
    }
}
