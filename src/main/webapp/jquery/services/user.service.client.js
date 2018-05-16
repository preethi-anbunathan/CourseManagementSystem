function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    this.findUserByUsername = findUserByUsername;
    this.updateUser = updateUser;
    this.logoutUser = logoutUser;
    this.login = login;
    this.register=register;
    this.url =
        'http://localhost:8080/api/user';
    this.loginurl= "/api/login";
    this.registerurl = "/api/register";
    this.logouturl = '/jquery/components/login/login.template.client.html';
    var self = this;
    
    function login(username, password) {

        var user = new User(username,password, null, null, null, null, null, null);
//    	var promise = findUserByUsername(username);
//    	var user = promise.then(function (response) {
//            return response.json();
//        });
    	console.log(user);
        var loginResponse = fetch(self.loginurl, {
            method : 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function(response) {
            if(response.status==200){
                return response.json();
            }else if(response.status==403){
                return null;
            }


        });
        return loginResponse;
    }

    function logoutUser()
    {
    	$(location).attr('href', self.logouturl);
    }
    
    function updateUser(userId, user) {
        return fetch(self.url + '/' + userId, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
        .then(function(response){
            if(response.ok) {
                return response.json();
            } else {
                return null;
            }
        });
    }
    
    function findUserById(userId) {
        return fetch(self.url + '/' + userId)
            .then(function(response){
                return response.json();
            });
    }  
    
    function findUserByUsername(username) {
        return fetch(self.url + '/username/' + username, {
            method: 'get'
        }).then(function(response){
                if(response.status != 200){
                    return null;
                }
                else{
                    return response.json();
                }


            });
    }
    
    function deleteUser(userId) {
        return fetch(self.url + '/' + userId, {
            method: 'delete'
        })
    }
    
    function findAllUsers() {
        return fetch(self.url)
            .then(function (response) {
                return response.json();
            });
    }
    
    function createUser(user) {

        return fetch(self.url, {
            method : 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (reponse) {
            if(reponse.status==409){
                return null;
            }else{
                return reponse;
            }
        });

    }

    
    function register(user) {


        return fetch(self.registerurl, {
            method : 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function(response){
            if(response.status==409){
                return null;
            }else{
                return response.json();
            }
        });


    }
}