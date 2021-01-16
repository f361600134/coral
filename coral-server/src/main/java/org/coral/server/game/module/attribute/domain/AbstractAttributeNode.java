package org.coral.server.game.module.attribute.domain;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Predicate;

import org.coral.server.game.helper.log.NatureEnum;

public abstract class AbstractAttributeNode implements IAttributeNode {

    /**
     * 父节点
     */
    protected IAttributeNode parent;
    /**
     * 子节点
     */
    protected List<IAttributeNode> childs;
    /**
     * 该节点的总属性
     */
    protected AttributeDictionary attrDic;
    /**
     * 属性是否需要计算
     */
    protected volatile boolean change = true;

    @Override
    public boolean isRoot() {
        return false;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public void setParent(IAttributeNode parent) {
        this.parent = parent;
    }

    @Override
    public void addChild(IAttributeNode child) {
        if (isLeaf()) {
            throw new UnsupportedOperationException(getClass().getName() + " is leaf node.cant add child.");
        }
        if (childs == null) {
            childs = new ArrayList<>();
        }
        childs.add(child);
        child.setParent(this);
        setAttrChange();
    }

    @Override
    public IAttributeNode getRoot() {
        if (isRoot()) {
            return this;
        }
        if (parent != null) {
            return parent.getRoot();
        }
        return this;
    }

    @Override
    public IAttributeNode getParent() {
        return parent;
    }

    @Override
    public List<IAttributeNode> getChilds() {
        return childs;
    }

    @Override
    public AttributeDictionary getAttrDic() {
        if (change) {
            attrDic = calculateAttrDic();
            change = false;
        }
        return attrDic;
    }

    /**
     * 刷新该节点属性
     */
    protected void refreshAttrDic() {
        attrDic = calculateAttrDic();
        change = false;
    }

    /**
     * 计算该节点的总属性<br>
     * 叶子节点必须重写该节点
     * 
     * @return
     */
    protected AttributeDictionary calculateAttrDic() {
        AttributeDictionary dictionary = new AttributeDictionary();
        if (isLeaf()) {
            throw new UnsupportedOperationException(getClass().getName() + " is leaf node.need override calculateAttrDic.");
        }
        if (childs == null || childs.isEmpty()) {
            return dictionary;
        }
        for (IAttributeNode child : childs) {
            dictionary.addAttr(child.getAttrDic());
        }
        //refreshInfluenceAttr(dictionary);
        return dictionary;
    }

    protected AttributeDictionary calculateAttrDic(Predicate<IAttributeNode> predicate) {
        AttributeDictionary dictionary = new AttributeDictionary();
        if (isLeaf()) {
            throw new UnsupportedOperationException(getClass().getName() + " is leaf node.need override calculateAttrDic.");
        }
        if (childs == null || childs.isEmpty()) {
            return dictionary;
        }
        for (IAttributeNode child : childs) {
            if (predicate == null || predicate.test(child)) {
                dictionary.addAttr(child.getAttrDic());
            }
        }
        //refreshInfluenceAttr(dictionary);
        return dictionary;
    }

//    /**
//     * 刷新影响力属性
//     * 
//     * @param dictionary
//     */
//    protected void refreshInfluenceAttr(AttributeDictionary dictionary) {
//        long aura = dictionary.getAttr(AttributeType.AURA);
//        long wealth = dictionary.getAttr(AttributeType.WEALTH);
//        long fame = dictionary.getAttr(AttributeType.FAME);
//        long influence = aura + wealth + fame;
//        dictionary.setAttr(AttributeType.INFLUENCE, influence);
//    }

    @Override
    public boolean isAttrChange() {
        return change;
    }

    @Override
    public void setAttrChange() {
        this.change = true;
        if (parent != null) {
            parent.setAttrChange();
        }
    }

    @Override
    public void refresh(boolean recursion, NatureEnum nEnum) {
        if (childs != null && !childs.isEmpty()) {
            for (IAttributeNode child : childs) {
                if (child.isAttrChange() || recursion) {
                    child.refresh(recursion, nEnum);
                }
            }
        }
        setAttrChange();
        refreshAttrDic();
    }

    @Override
    public void upRefresh(NatureEnum nEnum) {
        // 从下往上标记发生变化
        setAttrChange();
        // 从上往下刷新
        IAttributeNode root = getRoot();
        root.refresh(false, nEnum);
    }

    @Override
    public String getPath() {
        if (parent == null) {
            return getName();
        }
        return parent.getPath() + File.separator + getName();
    }

    @Override
    public String nodeToString() {
        StringBuffer sb = new StringBuffer();
        String path = getPath();
        sb.append(path).append("==");
        AttributeDictionary curAttrDic = getAttrDic();
        boolean first = true;
        for (Entry<Integer, Long> entry : curAttrDic.getDictionary().entrySet()) {
            long value = entry.getValue();
            if (value == 0) {
                continue;
            }
            if (!first) {
                sb.append(',');
            }
            int type = entry.getKey();
            AttributeType attrType = AttributeType.valueOf(type);
            if (attrType != null) {
                sb.append(attrType.name());
            } else {
                sb.append("attr").append(type);
            }
            sb.append(':').append(value);
            first = false;
        }
        return sb.toString();
    }

    @Override
    public String treeToString() {
        StringBuffer sb = new StringBuffer();
        sb.append(nodeToString());
        if (childs != null && !childs.isEmpty()) {
            for (IAttributeNode child : childs) {
                sb.append('\n').append(child.treeToString());
            }
        }
        return sb.toString();
    }
}
