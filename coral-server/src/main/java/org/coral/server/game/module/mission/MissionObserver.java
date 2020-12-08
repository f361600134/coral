package org.coral.server.game.module.mission;

import org.coral.server.core.event.EventSubscribe;
import org.coral.server.core.event.GameEventSubscribe;
import org.coral.server.game.module.player.event.PlayerAfterLoginEvent;

import com.google.common.eventbus.Subscribe;

@EventSubscribe
public class MissionObserver extends GameEventSubscribe {
	
	 @Subscribe
    public void onLogin(PlayerAfterLoginEvent event){
		 //TODO
    }

//    @Autowired
//    private DailyTaskService dailyTaskService;
//
//    @Subscribe
//    public void onHeroUpStar(HeroStarUpEvent event) {
//        Hero hero = event.getHero();
//        DailyMission mission = DailyMission.load(DailyMission.class, hero.getPlayerId());
//        Map<Integer, DailyMissionHelper> helpers = mission.getMissions();
//        for (Map.Entry<Integer, DailyMissionHelper> entry : helpers.entrySet()) {
//            DailyMissionHelper helper = entry.getValue();
//            helper.onHeroHave(hero.getPlayerId(), hero.getStar());
//        }
//    }
//    @Subscribe
//    public void onLogin(AfterLoginEvent event) {
//        Player player = event.getPlayer();
//        DailyMission mission = DailyMission.load(DailyMission.class, player.getPlayerId());
//        Map<Integer, DailyMissionHelper> helpers = mission.getMissions();
//        for (Map.Entry<Integer, DailyMissionHelper> entry : helpers.entrySet()) {
//            DailyMissionHelper helper = entry.getValue();
//            long time = ServerData.Today.getTime();
//            if(time < helper.getResetTime()){
//                helper.onLogin(player.getPlayerId(), 1);
//            }
//        }
//    }
//
//    @Subscribe
//    public void onFriendPoint(AfterLoginEvent event) {
//        Player player = event.getPlayer();
//        DailyMission mission = DailyMission.load(DailyMission.class, player.getPlayerId());
//        Map<Integer, DailyMissionHelper> helpers = mission.getMissions();
//        for (Map.Entry<Integer, DailyMissionHelper> entry : helpers.entrySet()) {
//            DailyMissionHelper helper = entry.getValue();
//            long time = ServerData.Today.getTime();
//            if(time < helper.getResetTime()){
//                helper.onLogin(player.getPlayerId(), 1);
//            }
//        }
//    }
//
//    @Subscribe
//    public void onCreateRole(AfterCreateRoleEvent event) {
//        Player player = event.getPlayer();
//        DailyMission mission = DailyMission.create(player.getId());;
//        mission.save();
//    }

}
