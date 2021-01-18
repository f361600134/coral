package org.coral.server.game.module.battle.attr;

import org.coral.server.game.module.attribute.domain.AbstractAttributeNode;
import org.coral.server.game.module.attribute.domain.AttributeDictionary;
import org.coral.server.game.module.battle.domain.BattleEntity;

public class BattleEntityAttrNode extends AbstractAttributeNode {

    private final BattleEntity entity;

    public BattleEntityAttrNode(BattleEntity entity) {
        super();
        this.entity = entity;
    }

    @Override
    protected AttributeDictionary calculateAttrDic() {
        // 初始属性
//        AttributeDictionary originalAttrDic = unit.getOriginalAttrDic();
//        // buff
//        BattleTeam team = unit.getTeam();
//        Map<Integer, BattleBuff> buffs = team.getBuffs();
//        if (buffs.isEmpty()) {
//            return originalAttrDic;
//        }
//        AttributeDictionary attrDic = null;
//        for (BattleBuff buff : buffs.values()) {
//            if (buff.getType() != BuffType.ATTR.getId()) {
//                continue;
//            }
//            AttributeDictionary buffAttrDic = buff.getAttrDic();
//            if (buffAttrDic == null || buffAttrDic.isEmpty()) {
//                continue;
//            }
//            if (attrDic == null) {
//                attrDic = new AttributeDictionary();
//            }
//            attrDic.addAttr(buffAttrDic);
//        }
//        if (attrDic == null) {
//            return originalAttrDic;
//        }
//        attrDic.addAttr(originalAttrDic);
        return attrDic;
    }

    @Override
    public String getName() {
        return "battle entity";
    }
}
