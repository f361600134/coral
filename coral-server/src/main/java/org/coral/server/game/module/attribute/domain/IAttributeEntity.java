package org.coral.server.game.module.attribute.domain;

/**
 * 带有属性的实体对象
 *
 * @author hdh
 */
public interface IAttributeEntity extends IAttributeNodeHolder {

    /**
     * 获取该属性实体的属性字典
     * 
     * @return
     */
    default AttributeDictionary getAttrDic() {
        return getAttributeNode().getAttrDic();
    }

    /**
     * 该实体某一属性总值
     *
     * @param attributeType
     * @return
     */
    default long getAttributeValue(AttributeType attributeType) {
        return getAttributeValue(attributeType.getId());
    }

    /**
     * 该实体某一属性总值
     *
     * @param attributeType
     * @return
     */
    default long getAttributeValue(int attributeType) {
        AttributeDictionary dictionary = getAttrDic();
        if (dictionary == null) {
            return 0;
        }
        return dictionary.getAttr(attributeType);
    }
}
