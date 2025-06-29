package WeatherPick.weatherpick.domain.user.entity;

public enum UserRoleType {

    ADMIN("ADMIN"),
    USER("USER");

    private final String description;

    UserRoleType(String description){
        this.description = description;
    }
    @Override
    public String toString() {
        return this.description;  // description 반환
    }

}
