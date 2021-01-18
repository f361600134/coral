package org.coral.server.game.module.battle.report;

import java.util.List;

import org.coral.server.game.module.battle.assist.BattleActionType;

/**
 * 回合数据
 */
public class BattleAction {

    private int id;

    private byte type;

    private int source;

    private int target;

    private List<Integer> args;

    private List<BattleAction> actions;

    public static final BattleAction NONE_ACTION = new BattleAction();

    private BattleAction() {

    }

//    private BattleAction(Builder builder) {
//        this.id = builder.id;
//        this.type = builder.type;
//        this.source = builder.source;
//        this.target = builder.target;
//        this.args = builder.args;
//        this.actions = builder.actions;
//    }
//    public static Builder newBuilder() {
//        return new Builder();
//    }
//    public static class Builder {
//
//        private int id;
//
//        private byte type;
//
//        private int source;
//
//        private int target;
//
//        private List<Integer> args = Lists.newArrayList();
//
//        private List<BattleAction> actions = Lists.newArrayList();
//
//        public Builder id(int id) {
//            this.id = id;
//            return this;
//        }
//
//        public Builder type(int type) {
//            this.type = (byte) type;
//            return this;
//        }
//
//        public Builder source(int source) {
//            this.source = source;
//            return this;
//        }
//
//        public Builder target(int target) {
//            this.target = target;
//            return this;
//        }
//
//        public Builder addArg(int arg) {
//            this.args.add(arg);
//            return this;
//        }
//
//        public Builder addAction(BattleAction action) {
//            this.actions.add(action);
//            return this;
//        }
//
//        public Builder addActions(List<BattleAction> actions) {
//            this.actions.addAll(actions);
//            return this;
//        }
//
//        public BattleAction build() {
//            return new BattleAction(this);
//        }
//
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (type == BattleActionType.USE_SKILL.getType()) {
            sb.append(source).append("使用技能：").append(id);
        } else if (type == BattleActionType.CHANGE_HP.getType()) {
            sb.append("，施法者：").append(source);
            sb.append("，技能效果ID：").append(id);
            sb.append("，目标：").append(target);
            sb.append("，伤害类型：").append(args.get(0));
            sb.append("，改变血量：").append(args.get(1));
            sb.append("，当前血量：").append(args.get(2));
        } else if (type == BattleActionType.ADD_BUFF.getType()) {
            sb.append("，添加BUFF");
            sb.append("，技能效果ID：").append(id);
            sb.append("，施法者：").append(source);
            sb.append("，目标：").append(target);
            sb.append("，BUFF列表：").append(args);
        } else if (type == BattleActionType.CLEAN_BUFF.getType()) {
            sb.append(target);
            sb.append("移除BUFF：").append(args);
        }
        for (BattleAction action : actions) {
            sb.append(action.toString());
        }
        return sb.toString();
    }

    public void addArg(int arg) {
        this.args.add(arg);
    }

    public void addAction(BattleAction action) {
        this.actions.add(action);
    }

    public void addActions(List<BattleAction> actions) {
        this.actions.addAll(actions);
    }

    public int getId() {
        return id;
    }

    public byte getType() {
        return type;
    }

    public int getSource() {
        return source;
    }

    public int getTarget() {
        return target;
    }

    public List<Integer> getArgs() {
        return args;
    }

    public List<BattleAction> getActions() {
        return actions;
    }

}