package org.coral.server.game.data.config.pojo.ext;

public class GoodCountData {
	
	private int id;
	//固定数量
	private int count;
	//数量范围
	private int minCount;
	private int maxCount;
	//权重或概率
	private int rate;
	
	public GoodCountData(){}
	
	public GoodCountData(GoodCountData data){
		this.id = data.getId();
		this.count = data.getCount();
		this.minCount = data.getMinCount();
		this.maxCount = data.getMaxCount();
		this.rate = data.getRate();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getMinCount() {
		return minCount;
	}
	public void setMinCount(int minCount) {
		this.minCount = minCount;
	}
	public int getMaxCount() {
		return maxCount;
	}
	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	
	@Override
	public String toString() {
		return "GoodCountData [id=" + id + ", count=" + count + ", minCount=" + minCount + ", maxCount=" + maxCount
				+ ", rate=" + rate + "]";
	}
	
}
