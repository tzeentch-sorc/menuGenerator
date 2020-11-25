<template>
  <div>
    <md-dialog :md-active.sync="showDialog">
      <div class="wrapper">
        <div class="left_block">
           <img :src="mealItem.picture" class="picture" alt=""/>
        </div>
        <div class="right_block">
          <md-dialog-title>
            {{mealItem.name}}
          </md-dialog-title>
          <md-dialog-content>
            <md-tabs>
              <md-tab md-label="БЖУ">
                <MealStatsTable :mealItem="mealItem"/>
              </md-tab>
              <md-tab md-label="Порции">
                <md-table>
                  <md-table-row>
                    <md-table-cell>Порций</md-table-cell>
                    <md-table-cell>{{additional.portions}} шт</md-table-cell>
                  </md-table-row>
                  <md-table-row>
                    <md-table-cell>Калорий в порции</md-table-cell>
                    <md-table-cell>{{additional.calories}} ккал</md-table-cell>
                  </md-table-row>
                  <md-table-row>
                    <md-table-cell>Вес порции</md-table-cell>
                    <md-table-cell>{{additional.weight}} г</md-table-cell>
                  </md-table-row>
                </md-table>
              </md-tab>
            </md-tabs>
          </md-dialog-content>
        </div>
      </div>
      <md-divider/>
      <md-dialog-content class="md-scrollbar">
        <md-tabs>
          <md-tab md-label="Рецепт">
            <div class="recipe" v-for="str in additional.recipe" v-bind:key="str"> {{str}} </div>
          </md-tab>
          <md-tab md-label="Ингредиенты">
            Coming Soon
          </md-tab>
        </md-tabs>
      </md-dialog-content>
      <md-dialog-actions>
        <md-button class="md-primary" @click="closeInfo">Закрыть</md-button>
      </md-dialog-actions>
    </md-dialog>
  </div>
</template>

<script>
import MealStatsTable from "@/components/MealStatsTable";
export default {
  name: "MealInfo",
  components: {MealStatsTable},
  props:{
    mealItem: Object,
    additional: Object
  },
  data() {
    return {
      showDialog: false,
    };
  },
  mounted() {
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
  max-width: 800px;
  min-width: 500px;
  padding: 10px;
  max-height: 700px;
  min-height: 300px;
}
.picture{
  width: 100%;
}

.wrapper{
  width: 100%;
}

.left_block { 
  float:left;
  width: 40%;
  padding: 10px;
  margin-top: 10px;
}
.right_block { 
  float:right;
  width:60%;
  padding: 10px;
}
.recipe{
  margin: 10px;
  max-height: 300px;
}

.md-dialog-title{
  margin-bottom: 10px;
}
</style>