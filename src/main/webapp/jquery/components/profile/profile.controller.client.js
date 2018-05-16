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
        
        $updateBtn = $("#updateBtn")
        .click(updateUser);
        
        $logoutBtn = $("#logoutBtn")
        .click(logoutUser);
        
        userId = getUrlVars()['userId'];
        findUserById(userId);
        //findUserById(132);
        //findUserByUserName(userName);
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
    
    function updateUser() {
        var user = {
            firstName: $firstName.val(),
            lastName: $lastName.val(),
            password: $password.val(),
            email: $email.val(),
            phone: $phone.val(),
            dob: $dob.val()
        };

        userService
            .updateUser(userId, user)
            .then(success);
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