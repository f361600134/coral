package org.coral.server.game.module.mission;

import org.coral.server.game.data.proto.PBBag;

public class EntityMission {

    public static final int STATE_NONE = 0;//未激活
    public static final int STATE_ACTIVED = 1;//已激活
    public static final int STATE_REWARDED = 2;//已領取

    private int configId; //任务id
    private int progress; //任务进度
    private int state;	  //任务状态

    private int completeType;//
    private int completeCondition;//
    private int completeValue;//

    public EntityMission(int configId, int progress, int state, int completeType, int completeCondition, int completeValue) {
        this.configId = configId;
        this.progress = progress;
        this.state = state;
        this.completeType = completeType;
        this.completeCondition = completeCondition;
        this.completeValue = completeValue;
    }

    public PBBag.PBMissionInfo toProto() {
        PBBag.PBMissionInfo.Builder builder = PBBag.PBMissionInfo.newBuilder();
        builder.setConfigId(getConfigId());
        builder.setProgress(getProgress());
        builder.setState(getState());
        return builder.build();
    }

    public boolean isNone() {
        return this.state==STATE_NONE;
    }
    public boolean isActived() {
        return this.state==STATE_ACTIVED;
    }

    public boolean isRewarded() {
        return this.state==STATE_REWARDED;
    }

    public int getConfigId() {
        return configId;
    }

    public void setConfigId(int configId) {
        this.configId = configId;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCompleteType() {
        return completeType;
    }

    public void setCompleteType(int completeType) {
        this.completeType = completeType;
    }

    public int getCompleteCondition() {
        return completeCondition;
    }

    public void setCompleteCondition(int completeCondition) {
        this.completeCondition = completeCondition;
    }

    public int getCompleteValue() {
        return completeValue;
    }

    public void setCompleteValue(int completeValue) {
        this.completeValue = completeValue;
    }

}
