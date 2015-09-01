define('member', function() {
	var currPageNo = 1;
	var pageSize = 3;
	
	return {
		listMember: function(pageNo, pageSize) {
			var moduleObj = this;
			$.getJSON(contextRoot + '/json/member/list.do', 
				{
					pageNo : pageNo, 
					pageSize : pageSize
				}, 
				function(result) {
					currPageNo = result.pageNo;
				  $('#pageNo').text(currPageNo);
				  
				  var tbody = $('#listTable tbody');
				  $('.data-row').remove();
				  
				  // HandlebarsJS 템플릿 사용
				  var source = $('#template1').html();
				  var template = Handlebars.compile(source);
				  var content = template(result);
				  $('#listTable tbody').html(content);
				  
				  // 이전, 다음 버튼 처리
				  if (result.pageNo > 1) {
				  	$('#prevBtn').removeAttr('disabled');
				  } else {
				  	$('#prevBtn').attr('disabled', 'disabled');
				  }
				  
				  if (result.isNextPage) {
				  	$('#nextBtn').removeAttr('disabled');
				  } else {
				  	$('#nextBtn').attr('disabled', 'disabled');
				  }
				  
				  $('.nameLink').click(function(event){
				  	event.preventDefault();
				  	moduleObj.detailMember(this.getAttribute('mno'));
				  	$('.my-view').css('display', '');
				  	$('.my-new').css('display', 'none');
				  });
				});
		}, // listMember
		detailMember: function(no) {
			$.getJSON(contextRoot + '/json/member/detail.do?no=' + no, function(result) {
				var data = result.data;
				$('#fNo').val(data.no);
				$('#fName').val(data.name);
				$('#fEmail').val(data.email);
				$('#fTel').val(data.tel);
				$('#fPassword').val(data.password);
				$('#fCreateDate').text(data.yyyyMMdd);
				$('#fPhoto').text(data.photo).attr('href', contextRoot 
						+ '/files/' + data.photo);
			});
		}, // detailMember
		deleteMember: function(no){
			var moduleObj = this;
			$.getJSON(contextRoot + '/json/member/delete.do?no=' + no, function(result) {
				var data = result.data;
				if (result.data == 'success') {
					alert('삭제 성공입니다.');
					moduleObj.listMember(currPageNo, pageSize);
					
					$('#cancelBtn').click();
					
				} else {
					alert('삭제할 수 없습니다.');
				}
			});
		}, // deleteMember
		updateMember: function(){
			var moduleObj = this;
			$.ajax(contextRoot + '/json/member/update.do', 
			{
				method: 'POST',
				dataType: 'json',
				data: {
					no: $('#fNo').val(),
					name: $('#fName').val(),
					email: $('#fEmail').val(),
					tel: $('#fTel').val(),
					password: $('#fPassword').val()
				},
				success: function(result) {
					if (result.data == 'success') {
						alert('변경 성공입니다.');
						moduleObj.listMember(currPageNo, pageSize);
						$('#cancelBtn').click();
					} else {
						alert('변경할 수 없습니다.');
					}
				}
			});
		}, // updateMember
		insertMember: function() {
			var moduleObj = this;
			$.ajax(contextRoot + '/json/member/insert.do', 
			{
				method: 'POST',
				dataType: 'json',
				data: {
					name: $('#fName').val(),
					email: $('#fEmail').val(),
					tel: $('#fTel').val(),
					password: $('#fPassword').val()
				},
				success: function(result) {
					if (result.data == 'success') {
						alert('등록 성공입니다.');
						moduleObj.listMember(1, pageSize);
						$('#cancelBtn').click();
					} else {
						alert('등록할 수 없습니다.');
					}
				}
			});
		}, // insertMember
		init: function() {
			var moduleObj = this;
			
			$('.my-view').css('display', 'none');
			$('.my-new').css('display', '');
			
			$('#prevBtn').click(function(event) {
				event.preventDefault();
				if ($(this).attr('disabled') == 'disabled') {
					return;
				}
				moduleObj.listMember(currPageNo - 1, pageSize)
			});
			
			$('#nextBtn').click(function(event) {
				event.preventDefault();
				if ($(this).attr('disabled') == 'disabled') {
					return;
				}
				moduleObj.listMember(currPageNo + 1, pageSize)
			});
			
			$('#deleteBtn').click(function(event) {
				event.preventDefault();
				moduleObj.deleteMember($('#fNo').val()); 
			});
			
			$('#updateBtn').click(function(event) {
				event.preventDefault();
				moduleObj.updateMember(); 
			});
			
			$('#insertBtn').click(function(event) {
				event.preventDefault();
				moduleObj.insertMember(); 
			});
			
			$('#cancelBtn').click(function(event) {
				$('.my-view').css('display', 'none');
				$('.my-new').css('display', '');
			});
			
		} // 초기화 함수
		
	};
	
});













