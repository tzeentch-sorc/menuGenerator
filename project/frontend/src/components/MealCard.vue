<template>
  <div>
    <MealInfo v-if="showDialog" :mealItem="parentData" :additional="item" @closeInfo="showDialog = false"/>
    <md-card md-with-hover @click.native="openDialog">
      <md-ripple>
        <md-card-media>
          <img :src="parentData.picture">
        </md-card-media>
        <md-card-header>
          <div class="md-title">{{parentData.name}} </div>
        </md-card-header>
        <md-card-content>
          <MealStatsTable :mealItem="parentData"/>
        </md-card-content>
      </md-ripple>
    </md-card>
  </div>
</template>
<script>
import MealInfo from "@/components/MealInfo";
import MealStatsTable from "@/components/MealStatsTable";
import MealService from "@/services/meal.service"
export default {
  name: 'MealCard',
  components: {MealStatsTable, MealInfo},
  props:{
    parentData: Object
  },
  data: () => ({
    showDialog: false,
    item: {},
    loadedItem: false
  }),
  methods: {
    openDialog(){
      if(!this.loadedItem)
        this.loadItem();
      this.showDialog = true;
    },
    loadItem(){
      MealService.getById(this.parentData.id).then(
          response => {
            this.item = response.data;
            this.item.recipe = this.item.recipe.replace(/([.?!])\s*(?=[0-9])/g, "$1|").split("|");
            this.loadedItem = true;
          }
      )
    }
  }
}

</script>
<style lang="scss" scoped>
.md-card {
  width: 250px;
  margin: 5px 10px 15px;
  vertical-align: top;
}
</style>