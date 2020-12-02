<template>
  <div class="container">
    <div v-if="draw">
      <md-empty-state
          md-label="Добро пожаловать!"
          md-icon="receipt_long"
      >
        <md-subheader>
          Для того чтобы начать пользоваться меню, необходимо заполнить данные профиля
        </md-subheader>
        <md-button class="md-raised md-primary" @click="goToPage('/profile')">Заполнить профиль</md-button>
      </md-empty-state>
    </div>
    <div v-else>
      {{this.currentUser.details}}
    </div>
  </div>
</template>

<script>
import UserService from '../services/user.service';

export default {
  name: 'User',
  data() {
    return {
      content: '',
      draw: true
    };
  },computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    currentProfile(){
      return this.$store.state.profileModule.profile;
    }
  },
  methods:{
    goToPage: function(page){
      this.$router.push(page);
    },
    statsNotEmpty(){
      return this.currentProfile.dailyProteins <= 0 ||
          this.currentProfile.dailyFats <= 0 ||
          this.currentProfile.dailyCarbohydrates <= 0 ||
          this.currentProfile.dailyCalories <= 0;
    }
  },
  beforeMount() {
    this.draw = this.statsNotEmpty();
  },
  mounted() {
    if(!this.currentUser) {
      this.goToPage('/login');
    }

    // UserService.getUserBoard().then(
    //   response => {
    //     this.content = response.data;
    //   },
    //   error => {
    //     this.content =
    //       (error.response && error.response.data && error.response.data.message) ||
    //       error.message ||
    //       error.toString();
    //   }
    // );
  }
};
</script>
<style scoped>
.container{
  margin-top: 5%;
  align-content: center;
}
</style>