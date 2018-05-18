//IIFE
(function () {

    jQuery(main);
    
    var tbody;
    var template;
    var userIdGlobal;
    var userService = new UserServiceClient();
    var redirecturl = '/jquery/components/profile/profile.template.client.html';
    
    function main() {
        tbody = $('tbody');
        template = $('.template');
        $('#wbdv-create').click(createUser);
        $('#wbdv-update').click(updateUser);
        findAllUsers();
    }

    
    function findAllUsers() {
        userService
            .findAllUsers()
            .then(renderUsers);
    }
    
    function createUser() {
        console.log('createUser');

        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var role = $('#roleFld').val();

        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role
        };
        
        userService
        .createUser(user)
        .then(findAllUsers);
    }

    function renderUsers(users) {
    	tbody.empty();
        for(var i=0; i<users.length; i++) {
            var user = users[i];
            var clone = template.clone();
            clone.attr('id', user.id);
            clone.find('.username')
                .html(user.username);
            clone.find('.password')
            .val(user.password);
            clone.find('.firstName')
            .html(user.firstName);
            clone.find('.lastName')
            .html(user.lastName);
            clone.find('.role')
            .html(user.role);
            
            

            clone.find('.delete').click(deleteUser);
            clone.find('.edit').click(editUser);

            tbody.append(clone);
        }
    }
    
        
    function deleteUser(event) {
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn
            .parent()
            .parent()
            .attr('id');

        userService
            .deleteUser(userId)
            .then(findAllUsers);
    }

    function editUser(event) {
        console.log('editUser');
        console.log(event);
        var editBtn = $(event.currentTarget);
        var userId = editBtn
            .parent()
            .parent()
            .attr('id');
        userIdGlobal = userId;
        findUserById(userId).then(function (user) {
            $('#usernameFld').val(user.username);
            $('#passwordFld').val(user.password);
            $('#firstNameFld').val(user.firstName);
            $('#lastNameFld').val(user.lastName);
            $('#roleFld').val(user.role);
        })
        
        console.log(userId);
      
    }
    
    function findUserById(userId) {
        return userService.findUserById(userId);
    }
    
    function updateUser() {
        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var role = $('#roleFld').val();
        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role
        };
        userService.updateUser(userIdGlobal,user).then(findAllUsers);
        userIdGlobal = null;
    }
    
})();

