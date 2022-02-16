/**
 * productSearch.js
 	상품 검색
 */
 
 $(document).ready(function(){
	$('#boardSearchFrm').on('submit', function(){
		event.preventDefault();
		
		var formData = $(this).serialize();
		// serialize() : 폼에 입력한 값을 쿼리 스트링 방식의 데이터로 변환
		//type=prdName&keyword=노트북
		
		$.ajax({
			type:"post",
			url:"bookSearch",
			data:formData,  
			success:function(result){   // 컨트롤러에서 반환한 prdList를 result가 받음
				//alert(result.length);
				// 반환된 결과(ArrayList<ProductVO>)를  searchResultbox에 테이블 형태로 출력
				$('#box').empty();
				$('#box').append('<table id="resultTable" border="1" width="600">' +
																	'<tr><th>도서번호</th><th>도서명</th><th>저자</th>' + 
																	'<th>가격</th><th>출판일</th><th>재고</th></tr>');
				if(result == ""){
					$('#resultTable').append('<tr align="center"><td colspan="6">찾는 도서 없습니다 </td></tr>');
				}else{
					for(var i=0; i <result.length; i++){
						$('#resultTable').append('<tr><td>' + result[i].bookNo + '</td><td>' +
																			               result[i].bookName+ '</td><td>' +
																			               result[i].bookAuthor+ '</td><td>' +
																			               result[i].bookPrice + '</td><td>' +
																			               result[i].bookDate + '</td><td>' +
																			               result[i].qtyNo + '</td>' );
					}
				}
				
				$('#box').append('</table>');
			},
			error:function(data, textStatus){
				alert("전송 실패");
			}
		});
	});	
});
