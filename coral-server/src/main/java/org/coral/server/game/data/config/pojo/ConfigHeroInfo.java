package org.coral.server.game.data.config.pojo;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;


public class ConfigHeroInfo {

	private int ID;//武将编号
	private String heroname;//武将名字
	private int nature;//资质
	private int frameBg;//框的颜色
	private int quality;//品质
	private int position;//武将位置
	private int armType;//兵种
	private int posSort;//阵型位置
	private int damType;//伤害类型
	private int heroLabel;//职业定位
	private int barCard;//名将或良将
	private int changeJob;//转职
	private String icon;//图标
	private String draw;//立绘
	private List<Float> drawPosition;//立绘偏移
	private String resMoudel;//模型
	private float skillScale;//放技能时候缩放比
	private String uiSound;//ui音效
	private String warSound;//战斗音效
	private String warEffectAttack;//普攻特效
	private String warEffectAttacked;//受击特效
	private String audioAttack;//普攻命中音效
	private int moudelSize;//模型大小
	private List<Integer> talents;//普通武将天赋
	private List<Integer> godTalents;//神将天赋
	private List<Integer> starTalents;//将星天赋
	private List<Integer> relations;//羁绊
	private List<List<Integer>> attrs;//英雄初始属性
	private int skill;//武将技能
	private int godHeroSkill;//神将技能
	private int starHeroSkill;//将星技能
	private int maxRage;//技能怒气上限
	private List<List<Integer>> deCompose;//分解获得资源
	private int chipID;//武将碎片
	private int artifactId;//神兵ID
	private List<Integer> heroMission;//转职任务ID
	private List<List<Integer>> godHeroItemCost;//激活神将所需材料
	private int godHeroCardCost;//激活所需同名卡
	private int initialConfidId;//初始武将配置id
	private int inherit;//是否可以被传承

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public String getHeroname(){
        return heroname;
    }
    public void setHeroname(String heroname){
        this.heroname = heroname;
    }
    
	public int getNature(){
        return nature;
    }
    public void setNature(int nature){
        this.nature = nature;
    }
    
	public int getFrameBg(){
        return frameBg;
    }
    public void setFrameBg(int frameBg){
        this.frameBg = frameBg;
    }
    
	public int getQuality(){
        return quality;
    }
    public void setQuality(int quality){
        this.quality = quality;
    }
    
	public int getPosition(){
        return position;
    }
    public void setPosition(int position){
        this.position = position;
    }
    
	public int getArmType(){
        return armType;
    }
    public void setArmType(int armType){
        this.armType = armType;
    }
    
	public int getPosSort(){
        return posSort;
    }
    public void setPosSort(int posSort){
        this.posSort = posSort;
    }
    
	public int getDamType(){
        return damType;
    }
    public void setDamType(int damType){
        this.damType = damType;
    }
    
	public int getHeroLabel(){
        return heroLabel;
    }
    public void setHeroLabel(int heroLabel){
        this.heroLabel = heroLabel;
    }
    
	public int getBarCard(){
        return barCard;
    }
    public void setBarCard(int barCard){
        this.barCard = barCard;
    }
    
	public int getChangeJob(){
        return changeJob;
    }
    public void setChangeJob(int changeJob){
        this.changeJob = changeJob;
    }
    
	public String getIcon(){
        return icon;
    }
    public void setIcon(String icon){
        this.icon = icon;
    }
    
	public String getDraw(){
        return draw;
    }
    public void setDraw(String draw){
        this.draw = draw;
    }
    
	public List<Float> getDrawPosition(){
        return drawPosition;
    }
    public void setDrawPosition(List<Float> drawPosition){
        this.drawPosition = drawPosition;
    }
    
	public String getResMoudel(){
        return resMoudel;
    }
    public void setResMoudel(String resMoudel){
        this.resMoudel = resMoudel;
    }
    
	public float getSkillScale(){
        return skillScale;
    }
    public void setSkillScale(float skillScale){
        this.skillScale = skillScale;
    }
    
	public String getUiSound(){
        return uiSound;
    }
    public void setUiSound(String uiSound){
        this.uiSound = uiSound;
    }
    
	public String getWarSound(){
        return warSound;
    }
    public void setWarSound(String warSound){
        this.warSound = warSound;
    }
    
	public String getWarEffectAttack(){
        return warEffectAttack;
    }
    public void setWarEffectAttack(String warEffectAttack){
        this.warEffectAttack = warEffectAttack;
    }
    
	public String getWarEffectAttacked(){
        return warEffectAttacked;
    }
    public void setWarEffectAttacked(String warEffectAttacked){
        this.warEffectAttacked = warEffectAttacked;
    }
    
	public String getAudioAttack(){
        return audioAttack;
    }
    public void setAudioAttack(String audioAttack){
        this.audioAttack = audioAttack;
    }
    
	public int getMoudelSize(){
        return moudelSize;
    }
    public void setMoudelSize(int moudelSize){
        this.moudelSize = moudelSize;
    }
    
	public List<Integer> getTalents(){
        return talents;
    }
    public void setTalents(List<Integer> talents){
        this.talents = talents;
    }
    
	public List<Integer> getGodTalents(){
        return godTalents;
    }
    public void setGodTalents(List<Integer> godTalents){
        this.godTalents = godTalents;
    }
    
	public List<Integer> getStarTalents(){
        return starTalents;
    }
    public void setStarTalents(List<Integer> starTalents){
        this.starTalents = starTalents;
    }
    
	public List<Integer> getRelations(){
        return relations;
    }
    public void setRelations(List<Integer> relations){
        this.relations = relations;
    }
    
	public List<List<Integer>> getAttrs(){
        return attrs;
    }
    public void setAttrs(List<List<Integer>> attrs){
        this.attrs = attrs;
    }
    
	public int getSkill(){
        return skill;
    }
    public void setSkill(int skill){
        this.skill = skill;
    }
    
	public int getGodHeroSkill(){
        return godHeroSkill;
    }
    public void setGodHeroSkill(int godHeroSkill){
        this.godHeroSkill = godHeroSkill;
    }
    
	public int getStarHeroSkill(){
        return starHeroSkill;
    }
    public void setStarHeroSkill(int starHeroSkill){
        this.starHeroSkill = starHeroSkill;
    }
    
	public int getMaxRage(){
        return maxRage;
    }
    public void setMaxRage(int maxRage){
        this.maxRage = maxRage;
    }
    
	public List<List<Integer>> getDeCompose(){
        return deCompose;
    }
    public void setDeCompose(List<List<Integer>> deCompose){
        this.deCompose = deCompose;
    }
    
	public int getChipID(){
        return chipID;
    }
    public void setChipID(int chipID){
        this.chipID = chipID;
    }
    
	public int getArtifactId(){
        return artifactId;
    }
    public void setArtifactId(int artifactId){
        this.artifactId = artifactId;
    }
    
	public List<Integer> getHeroMission(){
        return heroMission;
    }
    public void setHeroMission(List<Integer> heroMission){
        this.heroMission = heroMission;
    }
    
	public List<List<Integer>> getGodHeroItemCost(){
        return godHeroItemCost;
    }
    public void setGodHeroItemCost(List<List<Integer>> godHeroItemCost){
        this.godHeroItemCost = godHeroItemCost;
    }
    
	public int getGodHeroCardCost(){
        return godHeroCardCost;
    }
    public void setGodHeroCardCost(int godHeroCardCost){
        this.godHeroCardCost = godHeroCardCost;
    }
    
	public int getInitialConfidId(){
        return initialConfidId;
    }
    public void setInitialConfidId(int initialConfidId){
        this.initialConfidId = initialConfidId;
    }
    
	public int getInherit(){
        return inherit;
    }
    public void setInherit(int inherit){
        this.inherit = inherit;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
		Map<Integer, Integer> attrsTemps = Maps.newHashMap();
    	for(List<Integer> item : this.attrs)
    	{
    		int c = attrsTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		attrsTemps.put(item.get(0), c);
    	}
    	attrsMap = attrsTemps;
			    
		Map<Integer, Integer> deComposeTemps = Maps.newHashMap();
    	for(List<Integer> item : this.deCompose)
    	{
    		int c = deComposeTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		deComposeTemps.put(item.get(0), c);
    	}
    	deComposeMap = deComposeTemps;
			    
		Map<Integer, Integer> godHeroItemCostTemps = Maps.newHashMap();
    	for(List<Integer> item : this.godHeroItemCost)
    	{
    		int c = godHeroItemCostTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		godHeroItemCostTemps.put(item.get(0), c);
    	}
    	godHeroItemCostMap = godHeroItemCostTemps;
			    
		
		
		this.parseExt();
    }
	
	//id_count ID数量
    private Map<Integer, Integer> attrsMap = Maps.newHashMap();
    public Map<Integer, Integer> getAttrsMap(){
    	return attrsMap;
    }
    
    private Map<Integer, Integer> deComposeMap = Maps.newHashMap();
    public Map<Integer, Integer> getDeComposeMap(){
    	return deComposeMap;
    }
    
    private Map<Integer, Integer> godHeroItemCostMap = Maps.newHashMap();
    public Map<Integer, Integer> getGodHeroItemCostMap(){
    	return godHeroItemCostMap;
    }
    
	
	/////////UserDefine Begin///////////
	private void parseExt(){
		this.talents.addAll(this.godTalents);
		//this.talents.addAll(this.starTalents);
	}
	
	/////////UserDefine End/////////////
	
}
