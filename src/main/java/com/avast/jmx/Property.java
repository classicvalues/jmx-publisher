/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avast.jmx;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Tomas Rehak <rehak@avast.com>
 */
public class Property {

    private static final Logger LOGGER = LoggerFactory.getLogger(Property.class);
    private Field field;
    private String name;
    private String desc;
    private boolean readable;
    private boolean setable;
    private Method getter;
    private Method setter;
    private Object instance;
    private Object setterTarget;
    private Object getterTarget;
    private String type;

    public Property(Object instance, Field f, String name, String desc, boolean readable, boolean setable, Method getter, Method setter) {
        this.instance = instance;
        this.getter = getter;
        this.setter = setter;
        this.name = name;
        this.desc = desc;
        this.readable = readable;
        this.setable = setable;
        this.field = f;
        this.getterTarget = instance;
        this.setterTarget = instance;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Object getValue() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Preconditions.checkArgument(readable);
        Preconditions.checkNotNull(getter);
        Preconditions.checkNotNull(getterTarget);
        return getter.invoke(getterTarget);
    }

    public Object getSetterTarget() {
        return setterTarget;
    }

    public void setSetterTarget(Object setterTarget) {
        this.setterTarget = setterTarget;
    }

    public Object getGetterTarget() {
        return getterTarget;
    }

    public void setGetterTarget(Object getterTarget) {
        this.getterTarget = getterTarget;
    }

    public Object getInstance() {
        return instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isReadable() {
        return readable;
    }

    public void setReadable(boolean readable) {
        this.readable = readable;
    }

    public boolean isSetable() {
        return setable;
    }

    public void setSetable(boolean setable) {
        this.setable = setable;
    }

    public Method getGetter() {
        return getter;
    }

    public void setGetter(Method getter) {
        this.getter = getter;
    }

    public Method getSetter() {
        return setter;
    }

    public void setSetter(Method setter) {
        this.setter = setter;
    }

    public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Preconditions.checkArgument(setable);
        Preconditions.checkNotNull(setter);
        Preconditions.checkNotNull(setterTarget);
        setter.invoke(setterTarget, val);
    }

    @Override
    public String toString() {
        return "Property{" + "field=" + field + ", name=" + name + ", desc=" + desc + ", readable=" + readable + ", setable=" + setable + ", getter=" + getter + ", setter=" + setter + ", instance=" + instance + ", setterTarget=" + setterTarget + ", getterTarget=" + getterTarget + ", type=" + type + '}';
    }

}