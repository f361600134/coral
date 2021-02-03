package org.coral.server.game.module.attribute.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 属性字典
 */
public class AttributeDictionary {

    private final Map<Integer, Long> dictionary;

    public AttributeDictionary() {
        this.dictionary = new HashMap<>();
    }

    public AttributeDictionary(Map<Integer, Long> dictionary) {
        if (dictionary == null) {
            throw new NullPointerException();
        }
        this.dictionary = dictionary;
    }

    /**
     * 	获取字典
     * @return
     */
    public Map<Integer, Long> getDictionary() {
        return dictionary;
    }
    
    /**
     * 	根据类型获取值
     * @param attrType
     * @param value
     */
    public long getAttr(int attrType) {
        return dictionary.getOrDefault(attrType, 0l);
    }

    /**
     * 	根据类型设置值
     * @param attrType
     * @param value
     */
    public void setAttr(int attrType, long value) {
        dictionary.put(attrType, value);
    }
    
    /**
     * 	根据枚举类型设置值
     * @param attrType
     * @return
     */
    public void setAttr(AttributeType attrType, long value) {
        int id = attrType.getId();
        setAttr(id, value);
    }
    
    /**
     * 	根据枚举类型获取值
     * @param attrType
     * @return
     */
    public long getAttr(AttributeType attrType) {
        int id = attrType.getId();
        return getAttr(id);
    }
    
    /**
     * 	根据给定的类型, 值增加属性
     * @param attrType
     * @param value
     */
    public <T extends Map<Integer, Integer>> void addAttr(T dictionary) {
        if (dictionary == null) {
            return;
        }
        dictionary.forEach((key, value)->{
        	addAttr(key, (long)value);
        });
    }
    
    /**
     * 	根据给定的类型, 值增加属性
     * @param attrType
     * @param value
     */
    public void addAttr(int attrType, long value) {
        if (value == 0) {
            return;
        }
        long oldValue = dictionary.getOrDefault(attrType, 0l);
        long newValue = oldValue + value;
        dictionary.put(attrType, newValue);
    }
    
    /**
     * 	其他字典合并到此字典
     * @param otherAttrDic
     */
    public void addAttr(AttributeDictionary otherAttrDic) {
        if (otherAttrDic == null || otherAttrDic.isEmpty()) {
            return;
        }
        Map<Integer, Long> otherDic = otherAttrDic.getDictionary();
        for (Entry<Integer, Long> entry : otherDic.entrySet()) {
            int attrType = entry.getKey();
            long value = entry.getValue();
            addAttr(attrType, value);
        }
    }
    
    /**
     *	根据枚举类型增加值
     * @param attrType
     * @param value
     */
    public void addAttr(AttributeType attrType, long value) {
        if (value == 0) {
            return;
        }
        int id = attrType.getId();
        addAttr(id, value);
    }
    
    /**
     * 	根据给定的类型, 值减少属性
     * @param attrType
     * @param value
     */
    public <T extends Map<Integer, Integer>> void subAttr(T dictionary) {
        if (dictionary == null) {
            return;
        }
        dictionary.forEach((key, value)->{
        	subAttr(key, (long)value);
        });
    }

    /**
     * 	根据基础类型减少值
     * @param attrType
     * @param value
     */
    public void subAttr(int attrType, long value) {
        if (value == 0) {
            return;
        }
        long oldValue = dictionary.getOrDefault(attrType, 0l);
        long newValue = oldValue - value;
        dictionary.put(attrType, newValue);
    }

    /**
     * 	根据枚举类型减少值
     * @param attrType
     * @param value
     */
    public void subAttr(AttributeType attrType, long value) {
        if (value == 0) {
            return;
        }
        int id = attrType.getId();
        subAttr(id, value);
    }

    /**
     * 	根据枚举类型获取百分比值
     * @param attrType
     * @param value
     */
    public double getRateAttr(AttributeType attrType) {
        int id = attrType.getId();
        return getRateAttr(id);
    }
    
    /**
     * 	百分比值计算
     * @param attrType
     * @param value
     */
    public double getRateAttr(int attrType) {
        return getAttr(attrType) / 10000d;
    }

    /**
     * 	判断字典是否为空
     * @return
     */
    public boolean isEmpty() {
        return dictionary.isEmpty();
    }

}
