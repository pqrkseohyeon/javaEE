package com.board.model;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.book.model.BookImple;

public class BoardDAO {
		// DB연결
		private static BookImple instance = new BookImple();
		public static BookImple getInstance() {
			return instance;
		}
		private Connection getConnection() throws Exception {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/member");
			return ds.getConnection();
		}
		
		

}
