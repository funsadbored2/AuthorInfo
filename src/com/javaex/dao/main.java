package com.javaex.dao;

import java.util.List;

import com.javaex.vo.AuthorVp;

public class main {

	public static void main(String[] args) {

		// 작가 객체화
		AuthorDao authorDao = new AuthorDao();

		// selectEx
		List<AuthorVp> list = authorDao.Selectex();
		for (AuthorVp a : list) {
			System.out.println(a);
		}

		// update
		AuthorVp vp = new AuthorVp(1, "이문열", "강원 양양");
		authorDao.update(vp);

		// delete
		authorDao.delete(10);

		// insert
		AuthorVp vo = new AuthorVp("김훈", "칼의노래");
		authorDao.insert(vo);

		/*
		 * //select List<AuthorVp> authorList = authorDao.select();
		 * System.out.println(authorList.toString());
		 */

	}

}
