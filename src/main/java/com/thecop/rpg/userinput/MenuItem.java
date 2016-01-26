package com.thecop.rpg.userinput;

/**
 * Created by TheCop on 20.12.2015.
 */
//TODO may be make it an interface?
public interface MenuItem {

    String getControlString();

    String getDescription();

    void execute();
}
