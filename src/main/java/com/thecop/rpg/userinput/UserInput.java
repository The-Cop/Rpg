package com.thecop.rpg.userinput;

import com.thecop.rpg.util.Util;

import java.util.List;
import java.util.Optional;

public final class UserInput {

    private UserInput() {
    }

    public static MenuItem chooseFromMenu(List<MenuItem> menu) {
        while (true) {
            String input = Util.inputSystem();
            Optional<MenuItem> chosen =
                    menu.stream().filter(m -> m.getControlString().equalsIgnoreCase(input)).findFirst();
            if (chosen.isPresent()) {
                return chosen.get();
            }
            //TODO may be print with Display class?
            System.out.println("Wrong input");
        }
    }
}
