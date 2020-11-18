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
      content: [{
        "id": 1,
        "name": "Нежный хек",
        "recipe": "1. Филе хека посолить, поперчить. 2. Взбить яйцо и поместить туда филе. 3. Обвалять в муке смоченное в яйце филе. 4. Положить на смазанную растительным маслом сковороду и потушить с каждой стороны по 5-7 минут. Подавать с гарниром или овощами. Блюдо очень простое, недорогое, получается вкусным и нежным.",
        "mealtype": null,
        "calories": 139,
        "portions": 2,
        "fats": 7.5,
        "carbohydrates": 2.9,
        "weight": 135,
        "proteins": 15.0,
        "picture":"https://daily-menu.ru/public/modules/dailymenu/dailymenurecipes/1001/587bb462663ae74eb69cad6cc265d971.jpg"
      }],
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


