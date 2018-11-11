package com.zhiyou.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zhiyou.model.City;
import com.zhiyou.service.CityService;

/**
 * 异步请求返回数据 同步请求返回视图
 * @author jack
 *
 */
@WebServlet("/ajaxweather")
public class AjaxWeatherController extends HttpServlet {

	private static final long serialVersionUID = 8110152927594343930L;
	private CityService cityService = new CityService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			List<City> list = cityService.getAllCity();
			// 将城市信息设置到request对象中
			req.setAttribute("cityList", list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("/ajaxweather.jsp").forward(req, resp);
	}
}
