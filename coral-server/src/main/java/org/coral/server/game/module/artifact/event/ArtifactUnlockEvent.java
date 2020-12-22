package org.coral.server.game.module.artifact.event;

import org.coral.server.core.event.EventBase;

public class ArtifactUnlockEvent extends EventBase {

    private final int configId; //神器配置id

    private ArtifactUnlockEvent(long playerId, int configId) {
        super(playerId);
        this.configId = configId;
    }

    public static ArtifactUnlockEvent create(long playerId, int configId) {
        return new ArtifactUnlockEvent(playerId, configId);
    }

    public int getConfigId() {
        return configId;
    }

}
