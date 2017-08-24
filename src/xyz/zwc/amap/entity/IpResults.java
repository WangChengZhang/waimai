package xyz.zwc.amap.entity;

public class IpResults {
	private Integer status;// 值为0或1,0表示失败；1表示成功
	private String info;// 返回状态说明，status为0时，info返回错误原因，否则返回“OK”。
	private Integer infocode;// 返回状态说明,1000代表正确,详情参阅info状态表
	private String city;// 若为直辖市则显示直辖市名称；如果为局域网网段内IP或者非法IP或国外IP，则返回空
	private Integer adcode;
	private String rectangle;// 所在城市范围的左下右上对标对

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getAdcode() {
		return adcode;
	}

	public void setAdcode(Integer adcode) {
		this.adcode = adcode;
	}

	public String getRectangle() {
		return rectangle;
	}

	public void setRectangle(String rectangle) {
		this.rectangle = rectangle;
	}

	@Override
	public String toString() {
		return "IpResults [status=" + status + ", info=" + info + ", infocode=" + infocode + ", city=" + city
				+ ", adcode=" + adcode + ", rectangle=" + rectangle + "]";
	}
}
