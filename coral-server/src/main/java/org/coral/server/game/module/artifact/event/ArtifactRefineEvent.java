package org.coral.server.game.module.artifact.event;

import org.coral.server.core.event.EventBase;
import org.coral.server.game.module.artifact.domain.Artifact;

public class ArtifactRefineEvent extends EventBase {
    private Artifact artifact;

    public ArtifactRefineEvent(long playerId, Artifact artifact) {
    	super(playerId);
        this.artifact = artifact;
    }

    public Artifact getArtifact() {
        return artifact;
    }
}
