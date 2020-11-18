<template>
  <div>
    <md-dialog :md-active.sync="showDialog">
      <md-dialog-title>{{mealItem.name}}</md-dialog-title>
      <div class="wrapper">
        <div class="left_block">
           <img :src="mealItem.picture" class="picture" alt=""/>
        </div>
        <div class="right_block">
          {{mealItem.recipe}}
        </div>
      </div>
      
      <md-tabs >
        <md-tab md-label="КБЖУ и порции">
          <p> Количество порций: {{mealItem.portions}}</p>
          <p> Калории в одной порции: {{mealItem.calories}}</p>
          <p> Вес одной порции: {{mealItem.weight}}г</p>
          <table border=1>
            <thead>
              <td>Белки</td>
              <td>Жиры</td>
              <td>Углеводы</td>
            </thead>
            <tbody>
              <tr> 
                <td>{{mealItem.proteins}}</td> 
                <td>{{mealItem.fats}}</td>
                <td>{{mealItem.carbohydrates}}</td> 
              </tr>
            </tbody>
          </table>
        </md-tab>
        <md-tab md-label="Ингредиенты">
          Coming Soon
        </md-tab>
      </md-tabs>
     
      
      <md-dialog-actions>
        <md-button class="md-primary" @click="closeInfo">Close</md-button>
      </md-dialog-actions>
    </md-dialog>
  </div>
</template>

<script>

export default {
  name: "MealInfo",
  props:{
    mealItem: Object
  },
  data() {
    return {
      message: '',
      showDialog: true,
    };
  },
  mounted() {
    this.message = '';
    this.showDialog = true;
  },
  methods: {
    closeInfo: function(){
      this.$emit('closeInfo', {});
      this.showDialog = false;
    }
  }
};
</script>

<style lang="scss" scoped>
.md-dialog /deep/.md-dialog-container {
  max-width: 768px;
}
.picture{
  width: 100%;
}
 
.wrapper{
  width: 100%;
  padding: 5px;
}

.left_block { 
  float:left;
  width: 40%;
  padding: 5px;
}
.right_block { 
  float:right;
  width:60%;
  padding: 5px;
}
</style>