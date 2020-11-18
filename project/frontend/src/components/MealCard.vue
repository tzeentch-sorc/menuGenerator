<template>
  <div>
 
    <MealInfo v-if="showDialog" :mealItem="parentData" @closeInfo="showDialog = false"/>

    <md-card md-with-hover @click.native="showDialog = true">
      <md-ripple>
        <md-card-media>
          <img :src="parentData.picture" alt="meal">
        </md-card-media>
        <md-card-header>
          <div class="md-title">{{parentData.name}}</div>
        </md-card-header>
        <md-card-content>
          <p> Количество порций: {{parentData.portions}}</p>
          <p> Калории в одной порции: {{parentData.calories}}</p>
          <p> Вес одной порции: {{parentData.weight}}г</p>
          <table border=1>
            <thead>
              <td>Белки</td>
              <td>Жиры</td>
              <td>Углеводы</td>
            </thead>
            <tbody>
              <tr> 
                <td>{{parentData.proteins}}</td> 
                <td>{{parentData.fats}}</td>
                <td>{{parentData.carbohydrates}}</td> 
              </tr>
            </tbody>
           </table> 
        </md-card-content>
      </md-ripple>
    </md-card>

  </div>
</template>
<script>

//TODO decide on fields on card - implement them
import MealInfo from "../components/MealInfo";

export default {
  name: 'MealCard',
  components: {MealInfo},
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