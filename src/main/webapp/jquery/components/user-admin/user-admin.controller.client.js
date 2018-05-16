//IIFE
(function () {

    jQuery(main);
    
    var tbody;
    var template;
    var userService = new UserServiceClient();
    var redirecturl = '/jquery/components/profile/profile.template.client.html';
    
    function main() {
        tbody = $('tbody');
        template = $('.template');
        //$('#createUser').click(createUser);
        $('#wbdv-create').click(createUser);
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
            
            clone.attr('id', user.id);

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
        
        console.log(userId);
        $(location).attr('href', '/jquery/components/profile/profile.template.client.html?userId=' + userId);
        
    }
    
})();

