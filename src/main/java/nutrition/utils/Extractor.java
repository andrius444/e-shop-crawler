package nutrition.utils;

import nutrition.model.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by andrius on 3/28/17.
 */

public class Extractor {

    public static List<Long> getUsersIds(List<User> users) {
        return users.stream().mapToLong(User::getId).boxed().collect(Collectors.toList());
    }
}
