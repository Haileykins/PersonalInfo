package Utils;

import java.util.UUID;

class PlayerData {

    @SuppressWarnings("WeakerAccess")
    UUID playerId;
    String name;
    Integer age;
    String birthday;
    String location;
    String gender;
    String pronouns;
    String discord;
    String youtube;
    String twitch;
    String steam;

    PlayerData(UUID id) {
        playerId = id;
        name = "Not Set";
        age = 0;
        birthday = "Not Set";
        location = "Not Set";
        gender = "Not Set";
        pronouns = "Not Set";
        discord = "Not Set";
        youtube = "Not Set";
        twitch = "Not Set";
        steam = "Not Set";
    }
}
