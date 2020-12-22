package org.coral.server.game.module.artifact.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * Artifact控制器
 */
@Controller
public class ArtifactController {
	
	private static final Logger log = LoggerFactory.getLogger(ArtifactController.class);
	
//	@Autowired
//	private ArtifactService artifactService;
//
//	@Request(PBDefine.PBProtocol.ReqArtifactOpt_VALUE)
//	public void ReqArtifactOpt(Session session, PBArtifact.ReqArtifactOpt req) {
//		long playerId = sessionManager.getPlayerId(session);
//
//		AckArtifactOptResp ack = AckArtifactOptResp.newInstance();
//		int code = artifactService.reqArtifactOpt(playerId, req, ack);
//		ack.setCode(code);
//		SendMessageUtil.sendResponse(playerId, ack);
//	}
//	
//	
//	@Request(PBDefine.PBProtocol.ReqArtifactReceiveTask_VALUE)
//	public void ReqArtifactReceiveTask(Session session, PBArtifact.ReqArtifactReceiveTask req) {
//		long playerId = sessionManager.getPlayerId(session);
//
//		AckArtifactReceiveTaskResp ack = AckArtifactReceiveTaskResp.newInstance();
//		
//		int code = artifactService.reqArtifactReceiveTask(playerId, req, ack);
//		ack.setCode(code);
//		SendMessageUtil.sendResponse(playerId, ack);
//	}
//	
//	
//	@Request(PBDefine.PBProtocol.ReqArtifactList_VALUE)
//	public void ReqArtifactList(Session session, PBArtifact.ReqArtifactList req) {
//		long playerId = sessionManager.getPlayerId(session);
//		artifactService.reqArtifactList(playerId, req);
//	}
//	
//	
//	@Request(PBDefine.PBProtocol.ReqArtifactHolySeal_VALUE)
//	public void ReqArtifactHolySeal(Session session, PBArtifact.ReqArtifactHolySeal req) {
//		long playerId = sessionManager.getPlayerId(session);
//
//		AckArtifactHolySealResp ack = AckArtifactHolySealResp.newInstance();
//		
//		int code = artifactService.reqArtifactHolySeal(playerId, req, ack);
//		ack.setCode(code);
//		SendMessageUtil.sendResponse(playerId, ack);
//	}

}
