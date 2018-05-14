function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    this.updateUser = updateUser;
    this.login = login;
    this.register=register;
    this.url =
        'http://localhost:8080/api/user';
    this.loginurl= "/api/login";
    this.registerurl = "/api/register";
    var self = this;

    function login(username, password) {

        var user = new User(username,password, null, null, null, null, null, null);

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

    function updateUser(userId, user) {
        return fetch(self.url + '/' + userId, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        })
        .then(function(response){
            if(response.bodyUsed) {
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