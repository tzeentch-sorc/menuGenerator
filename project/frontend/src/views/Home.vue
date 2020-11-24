<template>
  <div class="container">
    <MealsSearch v-on:searchRequest="onRequestReceived"/>
    <md-divider/>
    <div v-masonry transition-duration="0.15s" item-selector=".item">
      <MealCard v-masonry-tile class="item" v-for="mealItem in content" v-bind:key="mealItem.id" :parentData="mealItem" />
    </div>
    <div v-if="content.length" v-observe-visibility="handleBottom">
      <md-progress-bar v-if="this.hasNextPage" md-mode="indeterminate"/>
    </div>
  </div>
</template>

<script>
import MealService from '../services/meal.service';
import MealCard from "../components/MealCard";
import MealsSearch from "@/components/MealsSearch";
export default {
  name: 'Home',
  components: {MealsSearch, MealCard},
  data() {
    return {
      content: [],
      errorMsg: 'All is ok',
      hasNextPage: false,
      nextPage: 0,
      filters: null
    };
  }, methods: {
    handleBottom: function (isVisible){
      if(!isVisible) { return; }
      if(!this.hasNextPage) return;
      this.nextPage++
      this.loadMore();
    },
    loadMore(){
          MealService.getAll(this.nextPage, this.filters).then(
              response => {
                this.content.push(...response.data);
                this.hasNextPage = response.headers['x-has-next-page'] ==='true';
              },
              error => {
                this.errorMsg = (error.response && error.response.data && error.response.data.message) ||
                    error.message ||
                    error.toString();
              }
          );
    },
    onRequestReceived(value){
      this.filters = value;
      this.content = [];
      this.loadMore();
    }
  },
  mounted() {
    this.loadMore();
  }
};
</script>

