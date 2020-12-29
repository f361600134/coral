package org.coral.server.game.module.artifact.event;

import org.coral.server.core.event.IObserver;
import org.coral.server.game.module.artifact.service.ArtifactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArtifactObserver implements IObserver{

    @Autowired private ArtifactService service;

//    @Subscribe
//    public void onLogin(AfterLoginEvent event){
//        service.onLogin(event.getPlayer().getId());
//    }
//
//    @Subscribe
//    public void onPlayerlevelUp(PlayerLevelUpEvent event){
//        Player player = event.getPlayer();
//        service.onPlayerlevelUp(player.getId(), player.getLevel());
//    }
//
//    @Subscribe
//    public void onHeroLevelUp(HeroUpgradeEvent event){
//        Hero hero = event.getHero();
//        service.onHeroLevelUp(hero.getPlayerId(), hero.getLevel());
//    }
//
//    /**
//     * 高级召唤事件
//     * @param event
//     */
//    @Subscribe
//    public void onCallHigh(SummonEvent event){
//        if(event.getType() == 3)
//            service.onCallHigh(event.getPlayer().getPlayerId(), event.getTimes());
//    }
//
//    /**
//     * 竞技场挑战事件
//     * TODO 未监听此事件
//     * @param event
//     */
//    @Subscribe
//    public void onArenaFight(ArenaChallengeEvent event){
//        service.onArenaFight(event.getPlayer().getPlayerId(), 1);
//    }
//
//    /**
//     * 远航任务完成事件
//     * TODO 未监听此事件
//     * @param event
//     */
//    @Subscribe
//    public void onSailMission(VoyageEvent event){
//        service.onSailMission(event.getPlayerId(), 1);
//    }
//
//    /**
//     * 主线副本快速作战事件
//     * @param event
//     */
//    @Subscribe
//    public void onChapterQuick(DungeonFightQuickEvent event){
//        service.onChapterQuick(event.getPlayerId(), event.getCount());
//    }
//
//    /**
//     * 精灵商店购买
//     * @param event
//     */
//    @Subscribe
//    public void onShopSpiritBuy(ShopBuyEvent event){
//        service.onShopSpiritBuy(event.getPlayerId(), event.getShopType());
//    }
//
//    /**
//     * 家族战事件
//     * TODO 未监听此事件
//     * @param event
//     */
//    @Subscribe
//    public void onFamilyChapter(ShopBuyEvent event){
//        service.onFamilyChapter(event.getPlayerId(), event.getShopType());
//    }
//
//    /**
//     *
//     * TODO 未监听此事件
//     * @param event 试练塔事件
//     */
//    @Subscribe
//    public void onTowerPass(ShopBuyEvent event){
//        service.onTowerPass(event.getPlayerId(), event.getShopType());
//    }
//
//    /**
//     * 战力达到x
//     * TODO 未监听此事件
//     * @param event 战力变动事件
//     */
//    @Subscribe
//    public void onPower(ShopBuyEvent event){
//        service.onPower(event.getPlayerId(), event.getShopType());
//    }
//
//    /**
//     * @param event 获得五星英雄事件
//     */
//    @Subscribe
//    public void onHeroCompose(AddHeroEvent event){
//        if(event.getHero().getStar() == 5)
//            service.onHeroCompose(event.getHero().getPlayerId(), event.getHero().getStar());
//    }
//
//    /**
//     * @param event 获得英雄事件
//     */
//    @Subscribe
//    public void onHeroHave(AddHeroEvent event){
//        service.onHeroHave(event.getHero().getPlayerId(), event.getHero().getStar());
//    }


}

