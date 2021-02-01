package org.coral.server.game.module.battle.domain;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.coral.server.game.module.battle.report.BattleAction;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Buff持有者
 *
 * @author Klass
 * @date 2020/8/13 15:16
 */
public class BattleBuffHolder {

    /**
     * 本回合生效的BUFF
     */
    private Set<BattleBuff> buffs = Sets.newHashSet();

    /**
     * 下一回合生效的BUFF
     */
    private Set<BattleBuff> nextRoundBuffs = Sets.newHashSet();

    private BattleEntity owner;

    public BattleBuffHolder(BattleEntity owner) {
        this.owner = owner;
    }

    /**
     * 增加Buff
     *
     * @param buff
     * @param
     */
    public void addBattleBuff(BattleBuff buff) {
//        if (buff.isAddCurrentRound()) {
//            this.buffs.add(buff);
//            buff.init();
//            return;
//        }
//        if (buff.isAddNextRound()) {
//            nextRoundBuffs.add(buff);
//        }
    }

    /**
     * 执行下一回合生效的BUFF
     */
    public void nextRoundBuffs() {
        for (BattleBuff buff : nextRoundBuffs) {
            buffs.add(buff);
            buff.init();
        }
        nextRoundBuffs.clear();
    }

    /**
     * 刷新BUFF的剩余回合数
     */
    public void refreshBattleBuffRounds() {
//        List<BattleBuff> removeBuffs = Lists.newArrayList();
//        for (Iterator<BattleBuff> iter = buffs.iterator(); iter.hasNext();) {
//            BattleBuff buff = iter.next();
//            buff.refreshRounds();
//            if (buff.isRemoved()) {
//                buff.release();
//                removeBuffs.add(buff);
//                EventPublisher.publishEvent0(new RemoveBattleBuffEvent(owner, buff));
//                iter.remove();
//            }
//        }
//        removeBattleBuffReport(removeBuffs);
    }

    public void refreshRemainBeAttackTimes() {
//        List<BattleBuff> removeBuffs = Lists.newArrayList();
//        for (Iterator<BattleBuff> iter = buffs.iterator(); iter.hasNext();) {
//            BattleBuff buff = iter.next();
//            buff.refreshRemainBeAttackTimes();
//            if (buff.isRemoved()) {
//                buff.release();
//                removeBuffs.add(buff);
//                EventPublisher.publishEvent0(new RemoveBattleBuffEvent(owner, buff));
//                iter.remove();
//            }
//        }
//        removeBattleBuffReport(removeBuffs);
    }

    /**
     * 根据TAG移除BUFF
     *
     * @param tags
     */
    public void removeBattleBuffsByTag(List<Integer> tags) {
//        List<BattleBuff> buffs = getBattleBuffsByTags(tags);
//        for (Iterator<BattleBuff> iter = buffs.iterator(); iter.hasNext();) {
//            BattleBuff buff = iter.next();
//            buff.release();
//            EventPublisher.publishEvent0(new RemoveBattleBuffEvent(owner, buff));
//            iter.remove();
//        }
//        removeBattleBuffReport(buffs);
    }

    public void removeBattleBuff(BattleBuff buff) {
//        buffs.remove(buff);
//        EventPublisher.publishEvent0(new RemoveBattleBuffEvent(owner, buff));
//        removeBattleBuffReport(Lists.newArrayList(buff));
    }

    private void removeBattleBuffReport(List<BattleBuff> buffs) {
//        if (buffs.isEmpty()) {
//            return;
//        }
//        BattleAction.Builder builder = BattleAction.newBuilder()
//                .type(BattleProtocol.BattleActionType.CLEAN_BUFF_VALUE)
//                .target(owner.getId());
//        for (BattleBuff buff : buffs) {
//            builder.addArg(buff.getId());
//        }
//        owner.addBattleAction(builder.build());
    }

    /**
     * 处理BUFF效果
     *
     * @param triggerType
     */
    public void handle(byte triggerType) {
//        for (BattleBuff buff : buffs) {
//            if (buff.isCanBeTriggered(triggerType)) {
//                buff.handle();
//            }
//        }
    }

    public void removeBattleBuffByType(byte type) {
//        buffs.stream().filter(e -> e.getType() == type).findAny().ifPresent(e -> removeBattleBuff(e));
    }

    public boolean hasStatus(byte type) {
//        return buffs.stream().filter(e -> e.getType() == type)
//                .findAny().isPresent();
    	return false;
    }

    public BattleBuff getBattleBuffByType(byte type) {
//        for (BattleBuff buff : buffs) {
//            if (buff.getType() == type) {
//                return buff;
//            }
//        }
        return null;
    }

    /**
     *  根据唯一ID获取BUFF
     *
     * @param id
     * @return
     */
    public BattleBuff get(int id) {
//        return buffs.stream().collect(Collectors.toMap(BattleBuff::getId, e -> e)).get(id);
    	return null;
    }

    public List<BattleBuff> getBattleBuffsByConfigId(int configId) {
//        return buffs.stream().filter(e -> e.getConfigId() == configId)
//                .collect(Collectors.toList());
    	return null;
    }
    
    public List<BattleBuff> getBattleBuffsByType(byte type) {
//    	return buffs.stream().filter(e -> e.getType() == type)
//    			.collect(Collectors.toList());
    	return null;
    }

    /**
     * 获取BUFF层数
     *
     * @param configId
     * @return
     */
    public int getStack(int configId) {
        return getBattleBuffsByConfigId(configId).size();
    }

    public List<BattleBuff> getBattleBuffsByTags(List<Integer> tags) {
        List<BattleBuff> result = Lists.newArrayList();
        for (int tag : tags) {
            BattleBuff buff = getBattleBuffByTag(tag);
            if (buff != null) {
                result.add(buff);
            }
        }
        return result;
    }

    public BattleBuff getBattleBuffByTag(int tag) {
//        for (BattleBuff buff : buffs) {
//            List<Integer> tags = buff.getTags();
//            if (tags.contains(tag)) {
//                return buff;
//            }
//        }
        return null;
    }

    public BattleEntity getOwner() {
        return owner;
    }

}
