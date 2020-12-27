package org.coral.server.game.module.mission;

public class ArtifactMissionHelper extends MissionHelper {
	
	public ArtifactMissionHelper() {}
	
	public boolean progressMission(int progressDelta) {
		
//		if(playerId ==0) {
//			return false;
//		}
//		Collection<Artifact> artifacts = Context.getArtifactService().getArtifacts(playerId);
//		for (Artifact artifact : artifacts) {
//			if (!artifact.isUnlock()) {//未解锁
//				continue;
//			}
//			ConfigArtifact config = ConfigManager.get(ConfigArtifact.class,artifact.getConfigId());
//			if (!config.getMissionIds().contains(mission.getConfigId())) {
//				continue;
//			}
//			return super.progressMission(progressDelta, mission);
//		}
		return false;
	}

	
}
 