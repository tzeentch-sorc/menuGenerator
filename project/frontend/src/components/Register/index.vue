<template>
  <div class="simple-login">
    <h1>Register</h1>
    <div>
      <form @submit="formSubmit">
        <strong>username:</strong>
        <input type="text" v-model="username"/>
        <strong>password:</strong>
        <input type="password" v-model="password"/>
        <strong>email:</strong>
        <input type="text" v-model="email"/>
        <button class="btn btn-success">Submit</button>
      </form>
      <strong>Output:</strong>
      <pre>
                        {{output}}
      </pre>
    </div>
  </div>
</template>

<script>
  export default {
    data(){
      return {
        name: 'register'
      }
    },
    methods: {
      formSubmit(e) {
        e.preventDefault();
        let currentObj = this;
        let params = new URLSearchParams();
        params.append('email', this.email);
        params.append('password', this.password);
        params.append('username', this.username);
        this.axios.post('http://localhost:80/api/addUser', params)
            .then(function (response) {
              currentObj.output = response.data;
            })
            .catch(function (error) {
              currentObj.output = error;
            });
      }
    }

  }
</script>

<style>
.simple-login {
  text-align: center;
}
</style>