package xyz.zwc.waimai.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Location {
	public static final BigDecimal EARTH_RADIUS = new BigDecimal("6378.137");// 赤道半径（千米）

	/**
	 * 
	 * @param location
	 *            经纬度字符串形式
	 * @return 将高德地图返回的字符串形式经纬度转化为bigdecimal数组
	 */
	public static List<BigDecimal> convert(String location) {
		List<BigDecimal> locate = new ArrayList<BigDecimal>();
		String[] locations = location.split(",");
		locate.add(0, new BigDecimal(locations[0]));
		locate.add(1, new BigDecimal(locations[1]));
		return locate;
	}

	/**
	 * 角度转弧度
	 * 
	 * @param angle
	 * @return
	 */
	private static BigDecimal rad(BigDecimal angle) {
		return angle.multiply((new BigDecimal(Math.PI)).divide(new BigDecimal(180), 6, BigDecimal.ROUND_HALF_UP));
	}

	/**
	 * 根据经纬度计算两点间的距离
	 * 
	 * @param lon1
	 *            第一点经度
	 * @param lat1
	 *            第一点纬度
	 * @param lon2
	 *            第二点经度
	 * @param lat2
	 *            第二点纬度
	 * @return 返回距离（千米）
	 */
	public static BigDecimal getDistance(BigDecimal lon1, BigDecimal lat1, BigDecimal lon2, BigDecimal lat2) {
		double resul = 2
				* Math.asin(
						Math.sqrt(Math.pow(
								Math.sin(rad(lat1).subtract(rad(lat2))
										.divide(new BigDecimal(2), 6, BigDecimal.ROUND_HALF_UP).doubleValue()),
						2) + Math.cos(rad(lat1).doubleValue()) * Math.cos(rad(lat2).doubleValue())
								* Math.pow(
										Math.sin(rad(lon1).subtract(rad(lon2))
												.divide(new BigDecimal(2), 6, BigDecimal.ROUND_HALF_UP).doubleValue()),
								2)));
		BigDecimal result = (new BigDecimal(resul)).multiply(EARTH_RADIUS).setScale(2, BigDecimal.ROUND_HALF_UP);
		return result;
	}

	/**
	 * 获取一个经纬坐标上下左右大概100km范围的矩形的边界经纬度,用于数据库粗略查询
	 * 
	 * @param lon
	 * @param lat
	 * @return 顺序为大经度，小经度，大纬度，小纬度
	 */
	public static List<BigDecimal> getRange(BigDecimal lon, BigDecimal lat) {
		List<BigDecimal> range = new ArrayList<BigDecimal>();
		BigDecimal i = new BigDecimal("1.000000");
		range.add(0, lon.add(i).setScale(6, BigDecimal.ROUND_HALF_UP));
		range.add(1, lon.subtract(i).setScale(6, BigDecimal.ROUND_HALF_UP));
		range.add(2, lat.add(i).setScale(6, BigDecimal.ROUND_HALF_UP));
		range.add(3, lat.subtract(i).setScale(6, BigDecimal.ROUND_HALF_UP));
		return range;
	}

	public static void main(String[] args) {
		System.out.println(getDistance(new BigDecimal(113.541382), new BigDecimal(23.09615), new BigDecimal(113.541382),
				new BigDecimal(23.19615)).doubleValue());
		System.out.println(getRange(new BigDecimal(113.541382), new BigDecimal(23.09615)));
	}
}
