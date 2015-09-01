var currPageNo = 1;
var pageSize = 3;

$('.my-view').css('display', 'none');
$('.my-new').css('display', '');

listMember(currPageNo, pageSize);

var prevBtn = $('#prevBtn');
prevBtn.click(function(event) {
	event.preventDefault();
	if ($(this).attr('disabled') == 'disabled') {
		return;
	}
	
	// 이전 페이지 목록 가져오기
	listMember(currPageNo - 1, pageSize)
});

var nextBtn = $('#nextBtn');
nextBtn.click(function(event) {
	event.preventDefault();
	if ($(this).attr('disabled') == 'disabled') {
		return;
	}
	// 다음 페이지 목록 가져오기
	listMember(currPageNo + 1, pageSize)
});

var deleteBtn = $('#deleteBtn');
deleteBtn.click(function(event) {
	event.preventDefault();
  deleteMember($('#fNo').val()); 
	// 파라미터 값을 안 주면 선택된 엘리먼트의 첫 번째에 해당하는 요소를 리턴함. 
});

var updateBtn = $('#updateBtn');
updateBtn.click(function(event) {
	event.preventDefault();
	updateMember(); 
});

var insertBtn = $('#insertBtn');
insertBtn.click(function(event) {
	event.preventDefault();
	insertMember(); 
});

var cancelBtn = $('#cancelBtn');
cancelBtn.click(function(event) {
	$('.my-view').css('display', 'none');
	$('.my-new').css('display', '');
});


function listMember(pageNo, pageSize) {
	$.getJSON('list.do', 
		{
			pageNo : pageNo, 
			pageSize : pageSize
		}, 
		function(result) {
			window.currPageNo = result.pageNo;
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
		  	prevBtn.removeAttr('disabled');
		  	prevBtn.attr('href', 'list.do?pageNo=' + (result.pageNo - 1)
		  		+ '&pageSize=' + result.pageSize);
		  } else {
		  	prevBtn.attr('disabled', 'disabled');
		  }
		  
		  if (result.isNextPage) {
		  	nextBtn.removeAttr('disabled');
		  	nextBtn.attr('href', 'list.do?pageNo=' + (result.pageNo + 1)
		  		+ '&pageSize=' + result.pageSize);
		  } else {
		  	nextBtn.attr('disabled', 'disabled');
		  }
		  
		  $('.nameLink').click(function(event){
		  	event.preventDefault();
		  	detailMember(this.getAttribute('mno'));
		  	console.log(this.getAttribute('mno'));
		  	$('.my-view').css('display', '');
		  	$('.my-new').css('display', 'none');
		  });
		  
		});
}

function detailMember(no) {
	$.getJSON('detail.do?no=' + no, function(result) {
		var data = result.data;
		$('#fNo').val(data.no);
		$('#fName').val(data.name);
		$('#fEmail').val(data.email);
		$('#fTel').val(data.tel);
		$('#fPassword').val(data.password);
		$('#fCreateDate').text(data.yyyyMMdd);
		$('#fPhoto').text(data.photo).attr('href', '../../files/' + data.photo);
	});
}


function deleteMember(no){
	$.getJSON('delete.do?no=' + no, function(result) {
		var data = result.data;
		if (result.data == 'success') {
			alert('삭제 성공입니다.');
			listMember(currPageNo, pageSize);
			
			$('#cancelBtn').click();
			
		} else {
			alert('삭제할 수 없습니다.');
		}
	});
}

function updateMember(){
	$.ajax('update.do', 
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
					var data = result.data;
					if (result.data == 'success') {
						alert('변경 성공입니다.');
						listMember(currPageNo, pageSize);
						
						$('#cancelBtn').click();
						
					} else {
						alert('변경할 수 없습니다.');
					}
				}
			});
			
}

function insertMember(){
	$.ajax('insert.do', 
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
				var data = result.data;
				if (result.data == 'success') {
					alert('등록 성공입니다.');
					listMember(1, pageSize);
	
					$('#cancelBtn').click();
					
				} else {
					alert('등록할 수 없습니다.');
				}
			}
		});
}

//로그인 HTML 가져오기
$('#header').load('../sub/login.html');

