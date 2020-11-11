<template>
  <div class="container">
    <header class="jumbotron">Header</header>
      <div>
        <MealCard :parent-data="this.content[0]"/>
        <MealCard :parent-data="this.content[1]"/>
      </div>
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
