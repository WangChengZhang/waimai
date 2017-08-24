package xyz.zwc.amap.entity;

import java.util.List;

public class PoiResults<POI> {
	private Integer status;// 值为0或1,0表示失败；1表示成功
	private Integer count;//结果数量
	private String info;// 返回状态说明，status为0时，info返回错误原因，否则返回“OK”。
	private Integer infocode;// 返回状态说明,1000代表正确,详情参阅info状态表
	private List<POI> pois;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Integer getInfocode() {
		return infocode;
	}

	public void setInfocode(Integer infocode) {
		this.infocode = infocode;
	}

	public List<POI> getPois() {
		return pois;
	}

	public void setPois(List<POI> pois) {
		this.pois = pois;
	}

	@Override
	public String toString() {
		return "PoiResults [status=" + status + ", count=" + count + ", info=" + info + ", infocode=" + infocode
				+ ", pois=" + pois + "]";
	}

}
