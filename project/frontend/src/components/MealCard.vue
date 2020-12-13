<template>
  <div class="item">
    <MealInfo v-if="showDialog" :mealItem="parentData" :additional="item" @closeInfo="showDialog = false"/>
    <md-card md-with-hover @click.native="openDialog">
      <md-ripple>
        <md-card-media>
          <img :src="parentData.picture">
        </md-card-media>
        <md-card-header>
          <md-toolbar class="md-transparent" md-elevation="0">
            <div class="md-title">{{parentData.name}} </div>
            <md-subheader>
              Тип: {{getType(parentData.type)}}
            </md-subheader>
          </md-toolbar>

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
    },
    getType(typeString){
      switch (typeString){
        case "TYPE_BREAKFAST": return "Завтрак";
        case "TYPE_DINNER": return "Обед";
        case "TYPE_SUPPER": return "Ужин";
        case "TYPE_DESSERT": return "Десерт";
        case "TYPE_ADDITIONAL": return "Дополнительно";
      }
    }
  }
}

</script>
<style lang="scss" scoped>
.md-card {
  margin: 5px 10px 15px;
  vertical-align: top;
}
.item{
  width: 270px;
}
</style>