define([
          'jquery',
          'handlebars',
          'bootstrap.min',
          'jquery.ui.widget',
          'jquery.iframe-transport',
          'jquery.fileupload',
          /*
          'canvas-to-blob.min',
          'load-image.all.min',
          'jquery.fileupload-process',
          'jquery.fileupload-image',
          'jquery.fileupload-audio',
          'jquery.fileupload-video',
          'jquery.fileupload-validate',
          */
          'app/common'
       ], function($, handlebars){
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
				  var template = handlebars.compile(source);
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
				$('#fPhoto').val(data.photo);
				$('#photoImg').attr('src', contextRoot + '/files/' + data.photo);
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
					password: $('#fPassword').val(),
					photo: $('#fPhoto').val()
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
					password: $('#fPassword').val(),
					photo: $('#fPhoto').val()
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
				$('#files').html('');
				$('#progress .progress-bar').css('width', '0%');
			});

			$('#fEmail').keyup(function(event) {
//				console.log(chkEmail);
				console.log($(this).val());
				$.getJSON(
//					contextRoot + '/json/member/existEmail.do', 
					'http://localhost:8888/member/existEmail.do',
					{
						email: $(this).val()
					},	
					function(result) {
						console.log(result.data);
						if (result.data == 'no') {
							$('#message').removeClass('my-message-warning')
													 .addClass('my-message-success')
													 .html('사용 가능합니다.');
							
						} else {
							$('#message').removeClass('my-message-success')
													 .addClass('my-message-warning')
													 .html('사용할 수 없는 이메일입니다.');
						}
				});
			});
			
			$('#fileupload').fileupload({
		  	url: contextRoot + '/json/file/upload.do',
		    dataType: 'json',
		    // autoUpload: false;
		    maxFileSize: 10000000,
		    disableImageResize: /Android(?!.*Chrome)|Opera/
		      .test(window.navigator.userAgent), 
		      previewMaxWidth: 100,
		      previewMaxHeight: 100, 
		      previewCrop: true
			}).on('fileuploadsubmit', function(e, data) {
        // 서버에 일반 폼 데이터도 보내고 싶으면, submit 하기 전에
        // 다음과 같이 formData 프로퍼티에 값을 설정하라!
        /*
        data.formData = {
          data1: 'okok',
          data2: 'nono'
        };
        */
      }).on('fileuploaddone', function(e, data) {
        console.log(data.result);
        $('#files').html('');
        $.each(data.result.data, function (index, file) {
            $('<img>')
              .attr('src', file.url)
              .css('width', '100px')
              .appendTo('#files');
            $('<span/>')
            .text(file.name 
                + '(' + file.originName + ')'
                + ', ' + file.size)
                .appendTo('#files');
            $('#fPhoto').val(file.name);
        });
      }).on('fileuploadprogressall', function (e, data) {
        var progress = parseInt(
            data.loaded / data.total * 100, 10);
        $('#progress .progress-bar').css(
            'width',
            progress + '%'
        );
      }); // 파일 업로드에 대한 옵션 지정
		} // 초기화 함수
		
	};
	
});













