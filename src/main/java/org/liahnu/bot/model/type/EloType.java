package org.liahnu.bot.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EloType {

    RCR("RCR"),

    MCR("MCR"),

    ;

    private final String type;

}
