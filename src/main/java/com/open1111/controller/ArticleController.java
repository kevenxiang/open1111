package com.open1111.controller;

import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.open1111.entity.ArcType;
import com.open1111.entity.Article;
import com.open1111.service.ArticleService;
import com.open1111.util.NavUtil;
import com.open1111.util.StringUtil;

/**
 * 帖子Controller层
 * @author xiang
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Resource
	private ArticleService articleService;
	
	@RequestMapping("/{id}")
	public ModelAndView details(@PathVariable("id") Integer id,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		Article article = articleService.findById(id);
		String keyWords = article.getKeyWords();
		if (StringUtil.isNotEmpty(keyWords)) {
			String arr[] = keyWords.split(" ");
			mav.addObject("keyWords", StringUtil.filterWhite(Arrays.asList(arr)));
		} else {
			mav.addObject("keyWords", null);
		}
		mav.addObject("article", article);
		article.setClick(article.getClick()+1);
		articleService.update(article);
		mav.addObject("pageCode", this.genUpAndDownPageCode(articleService.getLastArticle(id), articleService.getNextArticle(id), request.getServletContext().getContextPath()));
		String typeName="";
		Integer typeId=0;
		ArcType arcType = article.getArcType();
		if (arcType==null) {
			typeName="类型没有拿到";
			typeId=0;
		}
		mav.addObject("navCode", NavUtil.genArticleNavigation(typeName, typeId, article.getTitle()));
		mav.setViewName("article");
		return mav;
	}
	
	/**
	 * 获取下一篇帖子和上一篇帖子代码
	 * @param lastArticle
	 * @param nextArticle
	 * @param projectContext
	 * @return
	 */
	public String genUpAndDownPageCode(Article lastArticle,Article nextArticle, String projectContext) {
		StringBuffer pageCode = new StringBuffer();
		if (lastArticle==null || lastArticle.getId()==null) {
			pageCode.append("<p>上一篇: 没有了</p>");
		} else {
			pageCode.append("<p>上一篇: <a href='"+projectContext+"/article/"+lastArticle.getId()+".html'>"+lastArticle.getTitle()+"</a></p>");
		}
		
		if (nextArticle==null || nextArticle.getId()==null) {
			pageCode.append("<p>下一篇: 没有了</p>");
		} else {
			pageCode.append("<p>下一篇：<a href='"+projectContext+"/article/"+nextArticle.getId()+".html'>"+nextArticle.getTitle()+"</a></p>");
		}
		
		return pageCode.toString();
	}

}
























