define('auth', function() {
	return {
		init: function() {
			
			$('#loginBtn2').click(function(event) {
				event.preventDefault();
				$.getJSON(contextRoot + '/json/auth/login.do', 
				{
					email: $('#email').val(),
					password: $('#password').val()
				},
					function(result) {
						if (result.data == 'yes') {
							$(document).trigger('login.success');
							// 도큐먼트 객체에 대해서 이벤트를 발생시킨다. 사용법을 잘 알아두도록.
						} else {
							// 로그인 실패 메세지 출력하기
							$('#message').text('이메일 또는 암호가 맞지 않습니다.')
						}
					});
			});
			
			$('input').focus(function(event){
				$('#message').text('');
			});
			
		}
		
	};
	
});