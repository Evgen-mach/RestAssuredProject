package consts;

import java.util.Map;

public class UrlParamValues {

    public static final String VALID_KEY = "6dfe2176e5f391f8414d73603b6a9f77";
    public static final String VALID_TOKEN = "ATTA6cd39dd9b6c03c1bdca8c6c0db90a355f3d5b25ae8fcb8a43833a512af78ea647CF220BF";

    public static final Map<String, String> AUTH_QUERY_PARAMS = Map.of(
            "key", VALID_KEY,
            "token", VALID_TOKEN
    );

    public static final Map<String, String> ANOTHER_USER_AUTH_QUERY_PARAMS = Map.of(
            "key", "6dfe2176e5f391f8414d73603b6a9f77",
            "token", "ATTA6cd39dd9b6c03c1bdca8c6c0db90a355f3d5b25ae8fcb8a43833a512af78ea647CF220BZ"
    );

    public static final String EXISTING_BOARD_ID = "675067bee6c044df8c6dadd3";
    public static final String BOARD_ID_TO_UPDATE = "67939c72708f51d91f3cd404";
    public static final String USER_NAME = "yauhenim3";

    public static final String EXISTING_CARD_ID = "67caf3c5bf552d13fd6e1d23";
    public static final String EXISTING_LIST_ID = "6758283447a428b4f2bc66b8";
}
