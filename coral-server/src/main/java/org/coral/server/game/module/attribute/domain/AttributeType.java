package org.coral.server.game.module.attribute.domain;


/**
 * 属性类型<br>
 * 对应属性表<br>
 * {@link AttributeConfig}
 */
public enum AttributeType {
    // -----基础属性---------
	IdPropertyHp 	(201), // 兵力
	IdPropertyAtk 	(202), // 攻击
	IdPropertySpeed (203), // 攻速
	IdPropertyRage 	(204), // 聚气
	IdPropertyAdDef (205), // 物防
	IdPropertyApDef (206), // 法防
	
	IdPropertyHit 		 (207), // 命中（率）
	IdPropertyDodge 	 (208), // 闪躲（率）
	IdPropertyCrit 		 (209), // 暴击（率）
	IdPropertyDeCrit 	 (210), // 韧性（率）
	IdPropertyParry 	 (211), // 格挡（率）
	IdPropertyDeParry 	 (212), // 破击（率）
	
	IdPropertyDeDam 		(213), // 减伤
	IdPropertyAddDam 		(214), // 必杀
	IdPropertyDeCritValue 	(215), // 强韧
	IdPropertyCare 			(216), // 疗效
	IdPropertyPlusDam 		(217), // 附伤
	IdPropertyPlusDamRate 	(218), // 附伤系数
	
	IdPropertyDeDamRate 	(219), // 减伤系数
	IdPropertyCareRate 		(220), // 疗效系数
	IdPropertyRageSpd 		(221), // 聚气速度
	IdPropertyHpPercent 	(231), // 兵力%
	IdPropertyAtkPercent 	(232), // 攻击%
	IdPropertySpeedPercent 	(233), // 攻速%
	
	IdPropertyRagePercent 	(234), // 聚气%
	IdPropertyAdDefPercent 	(235), // 物防%
	IdPropertyApDefPercent 	(236), // 法防%
	IdPropertyHitPercent 	(237), // 命中（率）%
	IdPropertyDodgePercent	(238), // 闪躲（率）%
	IdPropertyCritPercent 	(239), // 暴击（率）%
	
	IdPropertyDeCritPercent 	(240), // 韧性（率）%
	IdPropertyParryPercent 		(241), // 格挡（率）%
	IdPropertyDeParryPercent 	(242), // 破击（率）%
	IdPropertyDeDamPercent 		(243), // 减伤%
	IdPropertyAddDamPercent 	(244), // 必杀%
	IdPropertyDeCritValuePercent(245), // 强韧%
	
	IdPropertyCarePercent 			(246), // 疗效%
	IdPropertyPlusDamPercent 		(247), // 附伤%
	IdPropertyPlusDamRatePercent 	(248), // 附伤系数%
	IdPropertyDeDamRatePercent 		(249), // 减伤系数%
	IdPropertyCareRatePercent 		(250), // 疗效系数%
	IdPropertyRageSpdPercent 		(251), // 聚气速度%
    ;

    private final int id;

    private AttributeType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static AttributeType valueOf(int type) {
        for (AttributeType attrType : values()) {
            if (attrType.id == type) {
                return attrType;
            }
        }
        return null;
    }

}
