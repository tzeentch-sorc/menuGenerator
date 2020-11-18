<template>
  <div>
 
    <MealInfo v-if="showDialog" :mealItem="parentData" @closeInfo="showDialog = false"/>

    <md-card md-with-hover @click.native="showDialog = true">
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
export default {
  name: 'MealCard',
  components: {MealStatsTable, MealInfo},
  props:{
    parentData: Object
  },
  data: () => ({
    showDialog: false
  })
}

</script>
<style lang="scss" scoped>
.md-card {
  width: 250px;
  margin: 5px 10px 15px;
  vertical-align: top;
}
</style>