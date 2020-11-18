<template>
  <div class="container">
    <header class="jumbotron">{{ errorMsg }}</header>
      <div v-masonry transition-duration="0.15s" item-selector=".item">
        <MealCard v-masonry-tile class="item" v-for="mealItem in content" v-bind:key="mealItem.id" :parentData="mealItem" />
      </div>
    <div v-if="content.length" v-observe-visibility="handleBottom"></div>
  </div>
</template>

<script>
import MealService from '../services/meal.service';
import MealCard from "../components/MealCard";
export default {
  name: 'Home',
  components: {MealCard},
  data() {
    return {
      content: [],
      errorMsg: 'All is ok',
      hasNextPage: false,
      nextPage: 0,
    };
  }, methods: {
    handleBottom(isVisible){
      if(!isVisible) { return }
      if(!this.hasNextPage) { return }

      this.nextPage++;

      this.loadMore();
    },
    loadMore(){
          MealService.getAll(this.nextPage).then(
              response => {
                this.content.push(...response.data);
                this.hasNextPage = response.headers['x-has-next-page'];
              },
              error => {
                this.errorMsg = (error.response && error.response.data && error.response.data.message) ||
                    error.message ||
                    error.toString();
              }
          );
    }
  },
  mounted() {
    this.loadMore();
  }
};
</script>
