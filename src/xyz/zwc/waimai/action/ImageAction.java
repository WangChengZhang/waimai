package xyz.zwc.waimai.action;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.zwc.waimai.entity.File;
import xyz.zwc.waimai.service.FileService;

@Controller
@RequestMapping("/image")
public class ImageAction {

	@Resource
	private FileService fileService;

	@RequestMapping("")
	public void image(HttpServletResponse response, String fileid) {
		String type = "image/*";
		try {
			ServletOutputStream os = response.getOutputStream();
			File file = null;
			if (fileid != null && !fileid.isEmpty()
					&& (file = fileService.selectById(Integer.parseInt(fileid))) != null) {

			} else {
				file = fileService.selectById(1);
			}
			type = file.getType();
			response.setContentType("image/" + type);
			java.io.File realfile = new java.io.File(file.getPath());
			if (realfile.exists() && realfile.isFile()) {
				FileInputStream fis = new FileInputStream(realfile);
				int len = 0;
				byte[] buffer = new byte[1024];
				while ((len = fis.read(buffer)) > 0) {
					os.write(buffer, 0, len);
				}
				os.flush();
				fis.close();
				os.close();
			}
		} catch (Exception e) {
			Log log = LogFactory.getLog(HomeAction.class);
			e.printStackTrace();
			log.error("图片显示异常", e);
		}
	}

	@RequestMapping("/checkcode")
	public void checkCode(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// 设置不缓存图片
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		// 指定生成的响应图片,一定不能缺少这句话,否则错误.
		response.setContentType("image/jpeg");
		int width = 90, height = 30; // 指定生成验证码的宽度和高度
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // 创建BufferedImage对象,其作用相当于一图片
		Graphics g = image.getGraphics(); // 创建Graphics对象,其作用相当于画笔
		Graphics2D g2d = (Graphics2D) g; // 创建Grapchics2D对象
		Random random = new Random();
		Font mfont = new Font("楷体", Font.BOLD, 20); // 定义字体样式
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height); // 绘制背景
		g.setFont(mfont); // 设置字体
		g.setColor(getRandColor(180, 200));

		// 绘制100条颜色和位置全部为随机产生的线条,该线条为2f
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int x1 = random.nextInt(6) + 1;
			int y1 = random.nextInt(12) + 1;
			BasicStroke bs = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL); // 定制线条样式
			Line2D line = new Line2D.Double(x, y, x + x1, y + y1);
			g2d.setStroke(bs);
			g2d.draw(line); // 绘制直线
		}
		// 输出由英文，数字，随机组成的验证文字，具体的组合方式根据生成随机数确定。
		String sRand = "";
		String ctmp = "";
		int itmp = 0;
		// 制定输出的验证码为四位
		for (int i = 0; i < 4; i++) {
			switch (random.nextInt(2)) {
			case 1: // 生成A-Z的字母
				itmp = random.nextInt(26) + 65;
				ctmp = String.valueOf((char) itmp);
				break;
			default:
				itmp = random.nextInt(10) + 48;
				ctmp = String.valueOf((char) itmp);
				break;
			}
			sRand += ctmp;
			Color color = new Color(20 + random.nextInt(110), 20 + random.nextInt(110), random.nextInt(110));
			g.setColor(color);
			// 将生成的随机数进行随机缩放并旋转制定角度 PS.建议不要对文字进行缩放与旋转,因为这样图片可能不正常显示
			/* 将文字旋转制定角度 */
			Graphics2D g2d_word = (Graphics2D) g;
			AffineTransform trans = new AffineTransform();
			trans.rotate((random.nextInt(90) - 45) * 3.14 / 180, width / 8 + width * i / 4, height / 2);
			g2d_word.setTransform(trans);
			g.drawString(ctmp, width / 8 + width * i / 4, height * 3 / 4);
		}
		session.setAttribute("checkCode", sRand);
		session.setAttribute("checkCodeTime", (new Date()).getTime());// 存入校验码生产的时间戳/毫秒
		g.dispose(); // 释放g所占用的系统资源
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			Log log = LogFactory.getLog(HomeAction.class);
			e.printStackTrace();
			log.error("checkCode验证码异常", e);
		} // 输出图片
	}

	/* 该方法主要作用是获得随机生成的颜色 */
	public Color getRandColor(int s, int e) {
		Random random = new Random();
		if (s > 255)
			s = 255;
		if (e > 255)
			e = 255;
		int r, g, b;
		r = s + random.nextInt(e - s); // 随机生成RGB颜色中的r值
		g = s + random.nextInt(e - s); // 随机生成RGB颜色中的g值
		b = s + random.nextInt(e - s); // 随机生成RGB颜色中的b值
		return new Color(r, g, b);
	}
}
