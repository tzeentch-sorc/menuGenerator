<template>
  <div class="container">
    <header class="jumbotron">
      <MealCard :parent-data="this.content[0]"/>
    </header>
  </div>
</template>

<script>
import UserService from '../services/user.service';
import MealCard from "../components/MealCard";
export default {
  name: 'Home',
  components: {MealCard},
  data() {
    return {
      content: ''
    };
  },
  mounted() {
    UserService.getPublicContent().then(
      response => {
        this.content = response.data;
      },
      error => {
        this.content =
          (error.response && error.response.data && error.response.data.message) ||
          error.message ||
          error.toString();
      }
    );
  }
};
</script>
