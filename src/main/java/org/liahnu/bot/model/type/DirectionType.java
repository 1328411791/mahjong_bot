package org.liahnu.bot.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lihanyu
 */

@Getter
@AllArgsConstructor
public enum DirectionType {

    EAST(1, "东"),

    SOUTH(2, "南"),

    WEST(3, "西"),

    NORTH(4, "北"),

    ;

    private final Integer direction;

    private final String name;

    public static DirectionType getDirectionType(Integer direction) {
        for (DirectionType directionType : values()) {
            if (directionType.getDirection().equals(direction)) {
                return directionType;
            }
        }
        return null;
    }

    public static DirectionType getDirectionType(String name) {
        for (DirectionType directionType : values()) {
            if (directionType.getName().equals(name) || directionType.getDirection().toString().equals(name)) {
                return directionType;
            }
        }
        return null;
    }

}
