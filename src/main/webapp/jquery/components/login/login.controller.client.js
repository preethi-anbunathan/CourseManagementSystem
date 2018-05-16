(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn, $signupLink;
    var redirecturl = '/jquery/components/profile/profile.template.client.html';
    var signupPage = '/jquery/components/register/register.template.client.html';

    var userService = new UserServiceClient();

    $(init);
    $(main);

    function init() {
        $usernameFld = $("#username");
        $passwordFld = $("#password");
        $loginBtn = $("#login");
        $signupLink= $("#signup");
    }

    function main() {
        $loginBtn.click(login);
        $signupLink.attr('href',signupPage);
        $usernameFld.change(closeAlert);
        $passwordFld.focusout(closeAlert());
    }
    function login() {
        userService.login($usernameFld.val(), $passwordFld.val()).then(success);
    }

    function success(response){
        if(response!=null){
        	var userId = response.valueOf();
            console.log(userId);
            window.location='/jquery/components/profile/profile.template.client.html?userId=' + userId.id;
//            if (window.sessionStorage) {
//                sessionStorage.setItem("userId", response.id.toString());
//            }
//            $(location).attr('href', redirecturl);
        }
        
        else{
            $('.alert').css('visibility','visible');
        }

    }

    function closeAlert(){
        $('.alert').css('visibility','hidden');
    }
})();