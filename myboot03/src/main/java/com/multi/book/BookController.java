package com.multi.book;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {

	@Autowired
	BookService service;

	@RequestMapping("/")
	public String viewIndex() {
		return "index";
	}

	@RequestMapping("/book/listAllBook")
	public String listAllBook(Model model) {
		ArrayList<BookVO> bookList = service.listAllBook();
		model.addAttribute("bookList", bookList);

		return "book/bookListView";
	}

	@RequestMapping("/book/newBookForm")
	public String newBookForm() {
		return "book/newBookForm";
	}

	@RequestMapping("/book/insertBook")
	public String insertBook(BookVO book) {
		System.out.println(book.getBookNo());
		service.insertBook(book);
		return "redirect:./listAllBook";
	}

	@RequestMapping("/book/detailViewBook/{bookNo}")
	public String detailViewBook(@PathVariable String bookNo, Model model) {
		BookVO book = service.detailViewBook(bookNo);
		model.addAttribute("book", book);
		return "book/bookDetailView";
	}


	@RequestMapping("/book/updateBookForm/{bookNo}")
	public String updateBookForm(@PathVariable String bookNo, Model model) {

		BookVO book = service.detailViewBook(bookNo);
		model.addAttribute("book", book);
		return "book/updateBookForm";
	}


	@RequestMapping("/book/updateBook")
	public String detailViewBook(BookVO book) {
		service.updateBook(book);
		return "redirect:./listAllBook";
	}


	@RequestMapping("/book/deleteBook/{bookNo}")
	public String deleteBook(@PathVariable String bookNo) {
		service.deleteBook(bookNo);
		return "redirect:../listAllBook"; 
	}
	

	@ResponseBody
	@RequestMapping("/book/bookNoCheck")
	public String bookNoCheck(@RequestParam("bookNo") String bookNo) {
		String bookNo_result = service.bookNoCheck(bookNo);
		
		String result = "use";
		if(bookNo_result != null)
			result = "no_use";
		
		return result;
	}
		
	// 상품 검색 폼으로 이동
			@RequestMapping("/book/bookSearchForm")
			public String bookSearchForm() {
				return "book/bookSearchForm";
			}
			// 상품 검색 
			@ResponseBody
			@RequestMapping("/book/bookSearch")
//			public ArrayList<ProductVO> productSearch(@RequestParam("type") String type, 
//																						 @RequestParam("keyword") String keyword) {
			public ArrayList<BookVO> productSearch(@RequestParam HashMap<String, Object> param, 
																						Model model){
				System.out.println("a");
				ArrayList<BookVO> bookList = service.bookSearch(param);
				model.addAttribute("prdList", bookList);
				
				// prdList로 제대로 반화되었는지 확인하기 위한 코드
				for(int i =0; i < bookList.size(); i++) {
					BookVO book = (BookVO) bookList.get(i);
					System.out.println(book.getBookNo());
				}
				
				return bookList;
			}
	
}
