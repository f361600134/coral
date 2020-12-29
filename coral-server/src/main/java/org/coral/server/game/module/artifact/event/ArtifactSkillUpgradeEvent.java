package org.coral.server.game.module.artifact.event;

import org.coral.server.core.event.PlayerEventBase;
import org.coral.server.game.module.artifact.domain.Artifact;

public class ArtifactSkillUpgradeEvent extends PlayerEventBase {
	
    private final Artifact artifact;

    public ArtifactSkillUpgradeEvent(long playerId, Artifact artifact) {
    	super(playerId);
        this.artifact = artifact;
    }

    public Artifact getArtifact() {
        return artifact;
    }
}
