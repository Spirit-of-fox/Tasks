package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PulldownMenu {
    CATEGORIES("Categories"),
    VIDEO_GAMES("Видеоигры");

    private final String menu;
}
