<template>
  <div v-if="currentTutorial" class="edit-form">
    <h4>Meal</h4>
    <form>
      <div class="form-group">
        <label for="title">Title</label>
        <input type="text" class="form-control" id="name"
          v-model="currentMeal.name"
        />
      </div>
      <div class="form-group">
        <label for="description">Description</label>
        <input type="text" class="form-control" id="recipe"
          v-model="currentMeal.recipe"
        />
      </div>

    </form>

    <p>{{ message }}</p>
  </div>

  <div v-else>
    <br />
   <p>Please click on a Meal...</p>
  </div>

</template>

<script>
import MealService from "../services/meal.service.js";

export default {
  name: "meal",
  data() {
    return {
      currentMeal: null,
      message: ''
    };
  },
  methods: {
    getMeal(id) {
      MealService.get(id)
        .then(response => {
          this.currentMeal = response.data;
        })
    }
  },
  mounted() {
    this.message = '';
    this.getMeal(this.$route.params.id);
  }
};
</script>

<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>