package org.coral.server.game.module.artifact.event;

import org.coral.server.core.event.PlayerEventBase;
import org.coral.server.game.module.artifact.domain.Artifact;

public class ArtifactHolySealEvent extends PlayerEventBase {
	
    private Artifact artifact;

    public ArtifactHolySealEvent(long playerId, Artifact artifact) {
    	super(playerId);
        this.artifact = artifact;
    }

    public Artifact getArtifact() {
        return artifact;
    }
}
