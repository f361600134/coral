package org.coral.server.game.module.chat.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.coral.net.core.annotation.Cmd;
import org.coral.net.core.base.GameSession;
import org.coral.net.core.base.IHandler;
import org.coral.server.game.data.proto.PBDefine.PBProtocol;
import org.coral.server.game.data.proto.PBPlayer.ReqChat;
import org.coral.server.game.module.chat.service.ChatServicePlus;
import org.coral.server.game.module.chat.service.CommandService;
import org.coral.server.game.module.player.helper.PlayerHelper;
import org.coral.server.game.module.player.proto.AckTipsResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 聊天控制器
 *
 * @author Jeremy
 */
@Controller
public class ChatHandler implements IHandler{


    private static final Log log = LogFactory.getLog(ChatHandler.class);

    @Autowired
    private ChatServicePlus chatServicePlus;
    
//    @Autowired
//    private PlayerService playerService;

    @Autowired
    private CommandService commandService;

    @Cmd(id = PBProtocol.ReqChat_VALUE, mustLogin = false)
    public void chat(GameSession session, ReqChat req) {
        long playerId = session.getPlayerId();
        
        boolean isGm = this.commandService.isGmCommand(req.getContent());
        if (isGm) {
            this.commandService.gmFromClient(session, req);
        } else {
            //聊天
            int code = this.chatServicePlus.chat(req, playerId);
            AckTipsResp ack = AckTipsResp.newInstance().setTipsId(code);
            PlayerHelper.sendMessage(playerId, ack);
        }
    }

}