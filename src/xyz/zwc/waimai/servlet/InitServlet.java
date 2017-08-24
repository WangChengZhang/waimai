package xyz.zwc.waimai.servlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public class InitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1218886687923492795L;

	/**
	 * Constructor of the object.
	 */
	public InitServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init(ServletConfig config) throws ServletException{
		//读取properties中的数据初始化到context中
		ServletContext context = config.getServletContext();
		Properties init = new Properties();
		try {
			InputStream file = new FileInputStream(context.getRealPath("/")+"/WEB-INF/classes/init.properties");
			init.load(file);
			context.setAttribute("amapwebkey", init.getProperty("amapwebkey"));
			context.setAttribute("amapserverkey", init.getProperty("amapserverkey"));
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
