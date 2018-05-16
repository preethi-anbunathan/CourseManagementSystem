(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var $registerBtn, $loginLink;
    var $signupForm, $invalidPasswordMsg, $validPasswordMsg;
    //var redirecturl = '/jquery/components/profile/profile.template.client.html';
    var loginPage='/jquery/components/login/login.template.client.html';

    var userService = new UserServiceClient();

    $(init);
    $(main);

    function init() {
        $usernameFld = $("#usernameFld");
        $passwordFld = $("#passwordFld");
        $verifyPasswordFld = $("#verifyPasswordFld");
        $registerBtn = $("#registerBtn");
        $loginLink = $("#loginBtn");
        $signupForm= $("#signupForm");
        $invalidPasswordMsg= $('#invalid-message');
        $validPasswordMsg=$('#valid-message');
    }

    function main() {
        
        $registerBtn.click(register);
        $loginLink.attr("href",loginPage);
        $usernameFld.change(closeAlert);
        $verifyPasswordFld.focusout(validatePassword);
        $verifyPasswordFld.change(emptyUserData())
        $usernameFld.change(closeAlert);
    }
    function register() {
    	if($passwordFld.val()!="" && $verifyPasswordFld.val()!="" &&
                ($passwordFld.val()==$verifyPasswordFld.val())){
        var user = new User($usernameFld.val(), $passwordFld.val(), null, null, null, null, null, null);
        userService.register(user).then(responseHandler);
    	}
    	else{
            $invalidPasswordMsg.css('color', 'red').css('visibility', 'visible');
        }
    	
    }

    function responseHandler(user){

        if(user!=null){
            if (window.sessionStorage) {
                sessionStorage.setItem("userId", user.id.toString());
            }
            console.log(user.id);
            window.location='/jquery/components/profile/profile.template.client.html?userId=' + user.id;
        }else if(user==null){
            $('.alert').css('visibility','visible');
        }
    }

    function closeAlert(){
        $('.alert').css('visibility','hidden');
    }
    
    function validatePassword() {
        if($passwordFld.val()!="" && $verifyPasswordFld.val()!="" &&
            ($passwordFld.val()==$verifyPasswordFld.val())){
            $validPasswordMsg.css('color', 'green').css('visibility', 'visible');
        }else{
            $invalidPasswordMsg.css('color', 'red').css('visibility', 'visible');
        }
    }
    
    function closeMessage() {   
    }
})();