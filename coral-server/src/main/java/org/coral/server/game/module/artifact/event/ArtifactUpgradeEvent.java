package org.coral.server.game.module.artifact.event;

import org.coral.server.core.event.PlayerEventBase;

public class ArtifactUpgradeEvent extends PlayerEventBase {

    private final int configId; //神器配置id
    private final int level; //等级

    private ArtifactUpgradeEvent(long playerId, int configId, int level) {
    	super(playerId);
        this.configId = configId;
        this.level = level;
    }

    public static ArtifactUpgradeEvent create(long playerId, int configId, int level) {
        return new ArtifactUpgradeEvent(playerId, configId, level);
    }

    public int getConfigId() {
        return configId;
    }

    public int getLevel() {
        return level;
    }
}
