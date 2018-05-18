(function() {
    $(init);
    var $userName;
    var $firstName;
    var $lastName;
    var $password;
    var $role;
    var $phone;
    var $email;
    var $dob;
    var $updateBtn;
    var $logoutBtn;
    var userId;    
    
    var userService = new UserServiceClient();

    function init() {
    	$userName = $("#userName");
    	$password = $("#inputPassword");
        $firstName = $("#firstName");
        $lastName = $("#lastName");
        $role = $("#role");
        $phone = $("#phone");
        $email = $("#email");
        $dob = $("#dob");
        $invalidFirstName = $('#firstName-message');
        $invalidPassword = $('#password-message');
        $invalidLastName = $('#lastName-message');
        $invaliddob = $('#dob-message');
        $invalidEmail = $('#email-message');
        $invalidPhone = $('#phone-message');
        $invalidRole = $('#role-message');
        
        $updateBtn = $("#updateBtn")
        .click(updateUser);
        
        $logoutBtn = $("#logoutBtn")
        .click(logoutUser);
        
        userId = getUrlVars()['userId'];
        findUserById(userId);
        
    }
    
    
    function getUrlVars()
    {
        var vars = [], hash;
        var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');

        for(var i = 0; i < hashes.length; i++)
        {
            hash = hashes[i].split('=');
            vars.push(hash[0]);
            vars[hash[0]] = hash[1];
        }

        return vars;
    }

    
    function logoutUser()
    {
    	userService
    		.logoutUser();
    }
    
    function validateFirstName()
    {
    	if($firstName.val()=="" || $firstName.val()==null)
    		{
    		$invalidFirstName.css('color', 'red').css('visibility', 'visible');
    		return false;
    		}
    	else
    		{
    		
    	$invalidFirstName.css('visibility', 'hidden');
    	return true;
    		}
    }
    function validateLastName()
    {
    	if($lastName.val()=="" || $firstName.val()==null)
    		{
    		$invalidLastName.css('color', 'red').css('visibility', 'visible');
    		return false;
    		}
    	else
    		{
    		
    	$invalidLastName.css('visibility', 'hidden');
    	return true;
    		}
    }
    function validatedob()
    {
    	if($dob.val()=="")
    		{
    		$invaliddob.css('color', 'red').css('visibility', 'visible');
    		return false;
    		}
    	else
    		{
    		
    	$invaliddob.css('visibility', 'hidden');
    	return true;
    		}
    }
    function validatePassword()
    {
    	if($password.val()=="")
    		{
    		$invalidPassword.css('color', 'red').css('visibility', 'visible');
    		return false;
    		}
    	else
    		{
    		
    	$invalidPassword.css('visibility', 'hidden');
    	return true;
    		}
    }
    function validateEmail()
    {
    	var re = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
    	var is_email=re.test($email.val());
    	console.log(is_email);
    	if(!is_email)
    		{
    		$invalidEmail.css('color', 'red').css('visibility', 'visible');
    		return false;
    		}
    	else
    		{
    		
    	$invalidEmail.css('visibility', 'hidden');
    	return true;
    		}
    }
    function validatePhone()
    {
    	var phre = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
    	var is_phone=phre.test($phone.val());
    	if(!is_phone)
    		{
    		$invalidPhone.css('color', 'red').css('visibility', 'visible');
    		return false;
    		}
    	else
    		{
    		
    		$invalidPhone.css('visibility', 'hidden');
    		return true;
    		}
    }
    function validateRole()
    {
    	if(role=="")
    		{
    		$invalidRole.css('color', 'red').css('visibility', 'visible');
    		return false;
    		}
    	else
    		{
    		
    	$invalidRole.css('visibility', 'hidden');
    	return true;
    		}
    }
    function updateUser() {
    	if(validateFirstName()&&
    	validateLastName()&&
    	validatePassword()&&
    	validatedob()&&
    	validateEmail()&&
    	validatePhone()&&
    	validateRole())
    		{
        var user = {
        		firstName: $firstName.val(),
                lastName: $lastName.val(),
                password: $password.val(),
                email: $email.val(),
                phone: $phone.val(),
                dob: $dob.val(),
                role: $role.val()
        };
        
        
        userService
            .updateUser(userId, user)
            .then(success);
    }
    }
    
    function success(response) {
        if(response != null) {
            alert('Successfully updated')
        } else {
            alert('Unable to update');
        }
    }
    
    function findUserById(userId) {
        userService
            .findUserById(userId)
            .then(renderUser);
    }
    
    function findUserByUsername(userName) {
        userService
            .findUserByUsername(userName)
            .then(renderUser);
    }
    
    function renderUser(user) {
        console.log(user);
        $userName.val(user.username);
        $password.val(user.password);
        $firstName.val(user.firstName);
        $lastName.val(user.lastName);
        $role.val(user.role);
        $phone.val(user.phone);
        $email.val(user.email);
        $dob.val(user.dob);
    }
})();