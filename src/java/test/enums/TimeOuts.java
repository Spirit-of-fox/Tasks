package enums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TimeOuts {
    SMALL_TIMEOUT_SECONDS(5),
    TIMEOUT_FOR_CONDITION_SECONDS(30);

    private final long timeout;

}
