package org.coral.server.game.module.attribute.domain;

import java.util.List;

import org.coral.server.game.helper.log.NatureEnum;

/**
 * 属性节点
 * 
 * @author hdh
 *
 */
public interface IAttributeNode {

    /**
     * 获取该属性的路径
     * 
     * @return
     */
    String getPath();

    /**
     * 该属性节点名
     * 
     * @return
     */
    String getName();

    /**
     * 是否根节点
     * 
     * @return
     */
    boolean isRoot();

    /**
     * 是否叶子节点
     * 
     * @return
     */
    boolean isLeaf();

    /**
     * 获取根节点
     * 
     * @return
     */
    IAttributeNode getRoot();

    /**
     * 获取该节点的父节点
     * 
     * @return
     */
    IAttributeNode getParent();

    void setParent(IAttributeNode parent);

    /**
     * 获取该节点的子节点列表
     * 
     * @return
     */
    List<IAttributeNode> getChilds();

    void addChild(IAttributeNode child);

    /**
     * 获取该节点的属性字典<br>
     * 若有变化 则刷新
     * 
     * @return
     */
    AttributeDictionary getAttrDic();

    /**
     * 该节点的属性是否有变化<br>
     * 是否需要重新计算
     * 
     * @return
     */
    boolean isAttrChange();

    /**
     * 标记相关节点的属性发生了变化
     */
    void setAttrChange();

    /**
     * 刷新该节点属性
     * 
     * @param recursion 是否强行刷新所有子节点 若为false 则只刷新标记了变化的子节点
     * @param logCause  刷新原因
     */
    void refresh(boolean recursion, NatureEnum nEnum);

    /**
     * 刷新该节点属性并通知上方节点进行刷新
     *
     * @param logCause 刷新原因
     */
    void upRefresh(NatureEnum nEnum);

    /**
     * 返回该节点为根节点的属性树的字符串
     * 
     * @return
     */
    String treeToString();

    /**
     * 返回该节点自身属性的字符串
     * 
     * @return
     */
    String nodeToString();
}
