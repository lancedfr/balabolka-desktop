package com.lance.balabolka.scope;

import com.lance.balabolka.controller.BaseScreenController;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ScreenScope implements Scope {
    private static final Map<String, BaseScreenController> screens = Collections.synchronizedMap(new HashMap<>());

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        if (!screens.containsKey(name)) {
            screens.put(name, (BaseScreenController) objectFactory.getObject());
        }
        return screens.get(name);
    }

    @Override
    public Object remove(String name) {
        return screens.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object resolveContextualObject(String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getConversationId() {
        throw new UnsupportedOperationException();
    }

}
