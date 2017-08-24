package xyz.zwc.waimai.action;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import xyz.zwc.amap.entity.PoiParams;
import xyz.zwc.amap.entity.PoiResults;
import xyz.zwc.amap.entity.Pois;
import xyz.zwc.amap.service.AmapService;
import xyz.zwc.waimai.entity.File;
import xyz.zwc.waimai.entity.Poi;
import xyz.zwc.waimai.entity.Product;
import xyz.zwc.waimai.entity.ProductCat;
import xyz.zwc.waimai.entity.Shop;
import xyz.zwc.waimai.entity.User;
import xyz.zwc.waimai.service.FileService;
import xyz.zwc.waimai.service.PoiService;
import xyz.zwc.waimai.service.ProductCatService;
import xyz.zwc.waimai.service.ProductService;
import xyz.zwc.waimai.service.ShopService;
import xyz.zwc.waimai.util.Location;
import xyz.zwc.waimai.util.Text;

@Controller
@RequestMapping("/admin")
public class AdminAction {

	@Resource
	private ShopService shopService;
	@Resource
	private AmapService amapService;
	@Resource
	private PoiService poiService;
	@Resource
	private FileService fileService;
	@Resource
	private ProductCatService productCatService;
	@Resource
	private ProductService productService;

	@RequestMapping("")
	public ModelAndView admin(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		User user = (User) session.getAttribute("user");
		if (user != null && user.getUserid().equals(1)) {
			modelAndView.setViewName("admin");
		} else {
			modelAndView.setViewName("redirect:/login");
		}
		return modelAndView;
	}

	@RequestMapping("/addshop")
	public ModelAndView addshop(HttpSession session, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		User user = (User) session.getAttribute("user");
		String adminresult = "failed";
		if (user != null && user.getUserid().equals(1)) {
			Shop shop = new Shop();
			File file = new File();
			List<String> tagid = new ArrayList<String>();
			// 保存目录
			String savePath = request.getServletContext().getRealPath("/WEB-INF/file");
			// 临时目录
			String tempPath = request.getServletContext().getRealPath("/WEB-INF/temp");
			java.io.File tmpFile = new java.io.File(tempPath);
			if (!tmpFile.exists()) {
				tmpFile.mkdir();
			}
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置缓冲区大小
			factory.setSizeThreshold(1024 * 100);
			// 设置临时文件保存目录
			factory.setRepository(tmpFile);
			// 文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");

			upload.setFileSizeMax(1000 * 1024);
			upload.setSizeMax(2000 * 1024);
			// 解析上传数据
			try {
				List<FileItem> list = upload.parseRequest(request);
				for (FileItem item : list) {
					// 判断是否文件,及key是否为image
					if (!item.isFormField() && "image".equals(item.getFieldName())) {
						String filename = item.getName();
						// 设置文件名和文件id
						File lastfile = fileService.selectLast();
						if (lastfile == null) {
							file.setFileid(1);
						} else {
							file.setFileid(lastfile.getFileid() + 1);
						}
						// 真实路径与文件名
						String path = makePath(file.getFileid().toString(), savePath) + "/" + file.getFileid() + "."
								+ Text.getSuffix(filename);
						file.setPath(path);
						file.setSize((int) item.getSize());
						file.setType(Text.getSuffix(filename));
						file.setCreateTime(new Timestamp(System.currentTimeMillis()));
						file.setUpdateTime(file.getCreateTime());
						file.setUrl(path);
						// 保存文件
						FileOutputStream out = new FileOutputStream(path);
						byte[] buffer = new byte[1024];
						int len = 0;
						InputStream in = item.getInputStream();
						while ((len = in.read(buffer)) > 0) {
							out.write(buffer, 0, len);
						}
						in.close();
						out.close();
						if (fileService.insertone(file) > 0) {

							// todo
						}
					} else {
						if (item.getFieldName().equals("name")) {
							shop.setName(item.getString("UTF-8"));
						}
						if (item.getFieldName().equals("fkShopCatsShopcatid")) {
							shop.setFkShopCatsShopcatid(Integer.parseInt(item.getString("UTF-8")));
						}
						if (item.getFieldName().equals("tagid")) {
							tagid.add(item.getString("UTF-8"));
						}
						if (item.getFieldName().equals("fkPoisPoiid")) {
							shop.setFkPoisPoiid(item.getString("UTF-8"));
						}
						if (item.getFieldName().equals("fkShopStatesShopState")) {
							shop.setFkShopStatesShopState(Integer.parseInt(item.getString("UTF-8")));
						}
						if (item.getFieldName().equals("address")) {
							shop.setAddress(item.getString("UTF-8"));
						}
						if (item.getFieldName().equals("basePrice")) {
							shop.setBasePrice(new BigDecimal(item.getString("UTF-8")));
						}
						if (item.getFieldName().equals("deliveryPrice")) {
							shop.setDeliveryPrice(new BigDecimal(item.getString("UTF-8")));
						}
						if (item.getFieldName().equals("phone")) {
							shop.setPhone(item.getString("UTF-8"));
						}
						if (item.getFieldName().equals("announcement")) {
							shop.setAnnouncement(item.getString("UTF-8"));
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			PoiParams poiParam = new PoiParams();
			poiParam.setKey((String) request.getServletContext().getAttribute("amapserverkey"));
			poiParam.setId(shop.getFkPoisPoiid());
			try {
				PoiResults<Pois> poiResult = amapService.getPois(poiParam);
				System.out.println("位置2");
				if (poiResult.getCount() > 0) {
					Pois pois = poiResult.getPois().get(0);
					Poi poi = new Poi();
					poi.setAddress(pois.getAddress());
					poi.setFkAdRegionsAdcode(pois.getAdcode());
					List<BigDecimal> lonlat = Location.convert(pois.getLocation());
					poi.setLongitude(lonlat.get(0));
					poi.setLatitude(lonlat.get(1));
					poi.setName(pois.getName());
					poi.setPoiid(pois.getId());
					poiService.insert(poi);
					Shop lastshop = shopService.selectLast();
					if (lastshop == null) {
						shop.setShopid(1);
					} else {
						shop.setShopid(lastshop.getShopid() + 1);
					}
					shop.setAttitudeScore(new BigDecimal(0));
					shop.setAverageTime(0);
					shop.setCloseTime(Time.valueOf("22:00:00"));
					shop.setCreatTime(new Timestamp(System.currentTimeMillis()));
					shop.setDishScore(new BigDecimal(0));
					shop.setFkFilesIcon(file.getFileid());
					shop.setFkQualificationsQualificationid(1);
					shop.setFkDeliveryRulesDeliveryRule(1);
					shop.setFkUsersUserid(1);
					shop.setFlag(1);
					shop.setMonthlySale(0);
					shop.setOpenTime(Time.valueOf("22:00:00"));
					int re1 = shopService.insert(shop);
					int re2 = 0;
					for (String tag : tagid) {
						re2 = shopService.insertShopTag(shop.getShopid(), Integer.parseInt(tag));
					}
					System.out.println("位置4");
					if (re1 > 0 && re2 > 0) {
						adminresult = "shopid为" + shop.getShopid();
					}
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		modelAndView.addObject("adminresult", adminresult);
		modelAndView.setViewName("adminresult");
		return modelAndView;
	}

	@RequestMapping("/addproduct")
	public ModelAndView addproduct(HttpSession session, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		User user = (User) session.getAttribute("user");
		String adminresult = "failed";
		if (user != null && user.getUserid().equals(1)) {
			Product product = new Product();
			File file = new File();
			// 保存目录
			String savePath = request.getServletContext().getRealPath("/WEB-INF/file");
			// 临时目录
			String tempPath = request.getServletContext().getRealPath("/WEB-INF/temp");
			java.io.File tmpFile = new java.io.File(tempPath);
			if (!tmpFile.exists()) {
				tmpFile.mkdir();
			}
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置缓冲区大小
			factory.setSizeThreshold(1024 * 100);
			// 设置临时文件保存目录
			factory.setRepository(tmpFile);
			// 文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");

			upload.setFileSizeMax(1000 * 1024);
			upload.setSizeMax(2000 * 1024);
			// 解析上传数据
			try {
				List<FileItem> list = upload.parseRequest(request);
				for (FileItem item : list) {
					// 判断是否文件,及key是否为image
					if (!item.isFormField() && "image".equals(item.getFieldName())) {
						String filename = item.getName();
						// 设置文件名和文件id
						File lastfile = fileService.selectLast();
						if (lastfile == null) {
							file.setFileid(1);
						} else {
							file.setFileid(lastfile.getFileid() + 1);
						}
						// 真实路径与文件名
						String path = makePath(file.getFileid().toString(), savePath) + "/" + file.getFileid() + "."
								+ Text.getSuffix(filename);
						file.setPath(path);
						file.setSize((int) item.getSize());
						file.setType(Text.getSuffix(filename));
						file.setCreateTime(new Timestamp(System.currentTimeMillis()));
						file.setUpdateTime(file.getCreateTime());
						file.setUrl(path);
						// 保存文件
						FileOutputStream out = new FileOutputStream(path);
						byte[] buffer = new byte[1024];
						int len = 0;
						InputStream in = item.getInputStream();
						while ((len = in.read(buffer)) > 0) {
							out.write(buffer, 0, len);
						}
						in.close();
						out.close();
						if (fileService.insertone(file) > 0) {

							// todo
						}
					} else {
						if (item.getFieldName().equals("name")) {
							product.setName(item.getString("UTF-8"));
						}
						if (item.getFieldName().equals("fkShopsShopid")) {
							product.setFkShopsShopid(Integer.parseInt(item.getString("UTF-8")));
						}
						if (item.getFieldName().equals("fkProductCatsPdcatid")) {
							product.setFkProductCatsPdcatid(Integer.parseInt(item.getString("UTF-8")));
						}
						if (item.getFieldName().equals("price")) {
							product.setPrice(new BigDecimal(item.getString("UTF-8")));
						}
						if (item.getFieldName().equals("description")) {
							product.setDescription(item.getString("UTF-8"));
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			product.setAttitudeScore(new BigDecimal(0));
			product.setCreateTime(new Timestamp(System.currentTimeMillis()));
			product.setDiscount(new BigDecimal(0));
			product.setDishScore(new BigDecimal(0));
			product.setFkFilesIcon(file.getFileid());
			product.setFkFilesPicture(file.getFileid());
			product.setFlag(1);
			product.setMonthlySale(0);
			if (productService.insert(product) > 0) {
				adminresult = "productid为" + product.getProductid();
			}
		}
		modelAndView.addObject("adminresult", adminresult);
		modelAndView.setViewName("adminresult");
		return modelAndView;

	}

	@RequestMapping("/addpcat")
	public ModelAndView addpcat(HttpSession session, String fkShopsShopid, String name) {
		ModelAndView modelAndView = new ModelAndView();
		User user = (User) session.getAttribute("user");
		String adminresult = "failed";
		if (user != null && user.getUserid().equals(1)) {
			modelAndView.setViewName("adminfailed");
			ProductCat pc = new ProductCat();
			pc.setFlag(1);
			pc.setFkShopsShopid(Integer.parseInt(fkShopsShopid));
			pc.setName(name);
			if (productCatService.insert(pc) > 0) {
				adminresult = "pdcatid为" + pc.getPdcatid();
			}
		}
		modelAndView.addObject("adminresult", adminresult);
		modelAndView.setViewName("adminresult");
		return modelAndView;
	}

	private String makePath(String filename, String savePath) {
		// 得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
		int hashcode = filename.hashCode();
		int dir1 = hashcode & 0xf; // 0--15
		int dir2 = (hashcode & 0xf0) >> 4; // 0-15
		// 构造新的保存目录
		String dir = savePath + "/" + dir1 + "/" + dir2; // upload\2\3
															// upload\3\5
		// File既可以代表文件也可以代表目录
		java.io.File file = new java.io.File(dir);
		// 如果目录不存在
		if (!file.exists()) {
			// 创建目录
			file.mkdirs();
		}
		return dir;
	}

}
